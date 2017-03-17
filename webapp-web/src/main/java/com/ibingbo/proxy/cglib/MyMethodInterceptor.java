package com.ibingbo.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 定义方法拦截器
 * Created by bing on 2016/12/30.
 */
public class MyMethodInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before: " + method);
        Object object = methodProxy.invokeSuper(target, args);
        System.out.println("after: " + method);
        return object;
    }
}
