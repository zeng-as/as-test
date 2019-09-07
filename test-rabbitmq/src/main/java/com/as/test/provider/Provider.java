package com.as.test.provider;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * desc:
 * author: as
 * date: 2019/8/2
 */
public class Provider {

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
            String msg = "test";
            channel.basicPublish("test_exchange_1", "1", null, msg.getBytes());
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            return;
        }
    }
}
