package com.ibingbo.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bing on 2017/5/8.
 */
public class BeanLifeCycle {
    public static void main(final String[] args) throws Exception {
        //通过反射机制自动识别出配置文件中定义的相应的Processor
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
        User user = (User) ctx.getBean("user");
        System.out.println(user);
        ctx.registerShutdownHook();
    }
}
