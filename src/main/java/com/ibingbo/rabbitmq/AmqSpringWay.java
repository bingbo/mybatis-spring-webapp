package com.ibingbo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bing on 2017/1/17.
 */
public class AmqSpringWay {

    public static void main(String[] args) throws Exception {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbitmq-conf.xml");
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        try {
            int i=0;
            while (true) {
                template.convertAndSend("Hello, spring amq " +i++);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            context.destroy();
        }
    }
}
