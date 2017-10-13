package com.ibingbo.design.pattern.state;

/**
 * @author zhangbingbing
 * @title App
 * @date 17/10/13
 */
public class App {
    public static void main(String[] args) {
        Context context = new Context(new StateA());
        context.request();
        context.request();
        context.request();
        context.request();
        context.request();

    }

}
