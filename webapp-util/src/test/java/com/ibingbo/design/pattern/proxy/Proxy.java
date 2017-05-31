package com.ibingbo.design.pattern.proxy;

/**
 * Created by zhangbingbing on 17/5/26.
 */
public class Proxy implements Subject{


    private Subject target;

    public Proxy(Subject target) {
        this.target = target;
    }

    public void request() {
        this.target.request();
    }
}
