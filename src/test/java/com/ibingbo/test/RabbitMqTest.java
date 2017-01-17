package com.ibingbo.test;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by bing on 2017/1/17.
 */
public class RabbitMqTest {

    private final static String QUEUE_NAME = "hello";

    @Test
    public void testMq() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] sent '" + message + "'");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
        channel.close();
        connection.close();
    }
}
