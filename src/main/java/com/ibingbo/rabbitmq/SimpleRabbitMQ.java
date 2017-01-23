package com.ibingbo.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by bing on 2017/1/17.
 */
public class SimpleRabbitMQ {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //声明一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("username");
        factory.setPassword("password");
        factory.setVirtualHost("virtualHost");
        factory.setPort(5673);
        //打开自动恢复机制，如果连接失败时
        factory.setAutomaticRecoveryEnabled(true);
        //获取连接
        Connection connection = factory.newConnection();
        //Connection connection1 = factory.newConnection(new Address[]{new Address("192.168.1.4"), new Address("192.168.1.5")});
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
