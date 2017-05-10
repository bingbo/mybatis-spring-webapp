package com.ibingbo.boot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Created by bing on 2017/5/8.
 */
public class User implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean{
    private int id;
    private String name;
    private BeanFactory beanFactory;

    static {
        System.out.println("---------before create user bean-------");
    }
    public User() {
        System.out.println("---------user bean constructor-------");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware---------------------set bean name......");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware---------------------set bean factory......");
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean----------------after properties set......");
    }

    public void myInit() {
        System.out.println("------------------ myInit");
    }

    public void myDestroy() {
        System.out.println("------------------ myDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean------------- destroy......");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
