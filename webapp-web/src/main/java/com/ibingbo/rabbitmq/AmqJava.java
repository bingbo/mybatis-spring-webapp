package com.ibingbo.rabbitmq;

import org.jruby.RubyProcess;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * Created by bing on 2017/1/17.
 */
public class AmqJava {

    public static void main(String[] args) throws Exception {
        //定义一个连接工厂
        ConnectionFactory factory = new CachingConnectionFactory("localhost");
        //基于该连接的管理操作类,用于声明队列、交换和绑定
        RabbitAdmin admin = new RabbitAdmin(factory);
        //定义一个消息队列
        Queue queue = new Queue("myqueue");
        //声明绑定一个队列
        admin.declareQueue(queue);
        //定义一个交换主题
        TopicExchange topicExchange = new TopicExchange("myExchange");
        //声明绑定一个主题
        admin.declareExchange(topicExchange);
        //具体的绑定操作
        admin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with("foo.*"));
        //监听器容器
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(factory);
        Object listener=new Object(){
            public void handleMessage(String foo){
                System.out.println(foo);
            }
        };
        //消息监听适配器，通过反射机制调用目标监听器的方法来处理消息，并进行灵活的消息类型转换
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        listenerContainer.setMessageListener(adapter);
        listenerContainer.setQueueNames("myqueue");
        listenerContainer.start();

        //辅助类，简化同步RabbitMQ访问（发送和接收消息）
        RabbitTemplate template = new RabbitTemplate(factory);
        template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
        template.convertAndSend("myExchange", "foo.say", "Hi, world!");
        Thread.sleep(1000);
        listenerContainer.stop();
    }
}
