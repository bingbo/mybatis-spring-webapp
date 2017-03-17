package com.ibingbo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听器主要做 一些初始化工作
 * 做一些初始化的内容添加工作、设置一些基本的内容、比如一些参数或者是一些固定的对象等等
 * Created by bing on 2016/12/29.
 */
public class LogListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("this is the listener doing....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("this is the listener done ...");
    }
}
