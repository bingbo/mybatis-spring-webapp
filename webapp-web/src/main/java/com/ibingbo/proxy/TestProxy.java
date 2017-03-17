package com.ibingbo.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by zhangbingbing on 2016/12/12.
 */
public class TestProxy {
    public static void main(String[] args) {
        CTest cTest=new CTest();
        MyInvokationHandler handler=new MyInvokationHandler();
        handler.setTarget(cTest);
        ITest test = (ITest)Proxy.newProxyInstance(cTest.getClass().getClassLoader(), cTest.getClass().getInterfaces(), handler);
        test.say();
        test.sayHi("bill");
    }
}
