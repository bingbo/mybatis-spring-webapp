package com.ibingbo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhangbingbing on 2016/12/12.
 */
public class MyInvokationHandler implements InvocationHandler{
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("starting call " + method);
        method.invoke(target, args);
        System.out.println("ending call " + method);
        return null;
    }
}
