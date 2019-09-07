package com.as.test.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * desc:
 * author: as
 * date: 2019/8/3
 */
public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            String consumerTag = channel.basicConsume("test_queue_2", true, new com.rabbitmq.client.Consumer() {
                @Override
                public void handleConsumeOk(String consumerTag) {

                }

                @Override
                public void handleCancelOk(String consumerTag) {

                }

                @Override
                public void handleCancel(String consumerTag) throws IOException {

                }

                @Override
                public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

                }

                @Override
                public void handleRecoverOk(String consumerTag) {

                }

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(consumerTag);
                    System.out.println(envelope);
                    System.out.println(properties);
                    System.out.println(new String(body));
                }
            });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            return;
        }
    }
}
