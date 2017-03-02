package com.ibingbo.thread.singleton;

/**
 * Created by bing on 2017/3/2.
 */

/**
 * 在使用枚举类时，构造方法会被自动调用，可以应用其特性实现线程安全的单例模式
 */
public enum MyServiceEnum {
    myServiceFactory;
    private Object myObject;

    MyServiceEnum() {
        myObject = new Object();
    }

    public Object getObject() {
        return myObject;
    }
}
