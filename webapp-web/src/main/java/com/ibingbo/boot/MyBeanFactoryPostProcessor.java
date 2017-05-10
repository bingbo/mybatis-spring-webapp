package com.ibingbo.boot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by bing on 2017/5/8.
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor-----------postProcessBeanFactory");
    }
}
