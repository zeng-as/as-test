package com.as.test.queue;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import static com.rabbitmq.client.AMQP.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * desc:
 * author: as
 * date: 2019/8/3
 */
public class Queue {
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
            channel.exchangeDeclare("test_exchange_1", BuiltinExchangeType.DIRECT);
            channel.queueDeclare("test_queue_1", false, false, false, null);
            channel.queueDeclare("test_queue_2", false, false, false, null);
            channel.queueBind("test_queue_1", "test_exchange_1", "1");
            channel.queueBind("test_queue_2", "test_exchange_1", "1");

            String msg = "test";
            channel.basicPublish("test_exchange_1", "1", null, msg.getBytes());
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            return;
        }
    }
}
