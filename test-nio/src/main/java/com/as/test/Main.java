package com.as.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            Selector selector = Selector.open();
            ssc.bind(new InetSocketAddress(8080)).
                    configureBlocking(false).
                    register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int select = selector.select();
                if (0 == select) {
                    continue;
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
//                    int i = key.interestOps();
//                    switch (i) {
//                        case SelectionKey.OP_ACCEPT:
//                            if (key.isAcceptable())
//                                handleAccept(ssc, selector);
//                            break;
//                        case SelectionKey.OP_READ:
//                            if (key.isReadable())
//                                handleRead(key);
//                            break;
//                        default:
//                            break;
//                    }
                    if (key.isAcceptable()) {
                        handleAccept(ssc, selector);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }

                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void handleAccept(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Client connected: " + socketChannel.getRemoteAddress());
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            String message = new String(data);
            System.out.println("Received message: " + message);

            // Echo the message back to the client
            socketChannel.write(ByteBuffer.wrap(data));
        } else {
            // If bytesRead is -1, the connection is closed by the client
            key.cancel();
            socketChannel.close();
            System.out.println("Client disconnected");
        }
    }
}