package com.ibingbo.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * 通过Enhancer创建代理类的子类，并重写相应的方法加入方法拦截器后再调用父类即代理类的方法
 * Created by bing on 2016/12/30.
 */
public class MyApp {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyService.class);
        enhancer.setCallback(new MyMethodInterceptor());
        MyService service = (MyService)enhancer.create();
        service.add();
        service.delete();
    }
}
