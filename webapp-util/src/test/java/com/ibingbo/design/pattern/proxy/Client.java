package com.ibingbo.design.pattern.proxy;

/**
 * Created by zhangbingbing on 17/5/26.
 */
public class Client {
    public static void main(String[] args) {
        Subject target = new RealSubject();
        Proxy proxy = new Proxy(target);
        proxy.request();
    }
}
