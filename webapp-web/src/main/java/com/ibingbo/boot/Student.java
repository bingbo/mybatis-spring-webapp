package com.ibingbo.boot;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by bing on 2017/5/8.
 */
public class Student implements InitializingBean{

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean------afterPropertiesSet");
    }
}
