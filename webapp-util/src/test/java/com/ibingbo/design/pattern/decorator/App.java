package com.ibingbo.design.pattern.decorator;

/**
 * App
 *
 * @author zhangbingbing
 * @date 17/12/1
 */
public class App {

    public static void main(String[] args) {
        Component component = new ComponentA();
        Decorator decorator = new DecoratorA(component);
        decorator.operation();
    }
}
