package com.ibingbo.design.pattern.observer;

/**
 * Created by bing on 2017/5/16.
 */
public class Client {
    public static void main(String[] args) {
        ConcreteSubject s = new ConcreteSubject();
        s.attach(new ConcreteObserver("X", s));
        s.attach(new ConcreteObserver("Y", s));
        s.attach(new ConcreteObserver("Z", s));

        s.setSubjectState("abc");
        s.doNotify();
    }
}
