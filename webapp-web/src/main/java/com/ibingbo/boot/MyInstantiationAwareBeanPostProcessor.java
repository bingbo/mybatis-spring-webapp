package com.ibingbo.boot;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * Created by bing on 2017/5/8.
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter{
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter-----------------postProcessBeforeInstantiation");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter----------------postProcessAfterInstantiation");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter---------------postProcessPropertyValues");
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter-------------------postProcessBeforeInitialization");
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter-------------------postProcessAfterInitialization");
        return super.postProcessAfterInitialization(bean, beanName);
    }
}
