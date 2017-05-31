package com.ibingbo.design.pattern.proxy;

/**
 * Created by zhangbingbing on 17/5/26.
 */
public class RealSubject implements Subject {

    public void request() {
        System.out.println("real request ...");
    }
}
