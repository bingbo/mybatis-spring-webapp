package com.ibingbo.design.pattern.observer;

/**
 * Created by bing on 2017/5/16.
 */
public class ConcreteObserver implements Observer{
    private String name;
    private String observerState;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    public void update() {
        this.observerState=this.subject.getSubjectState();
        System.out.println("观察者" + this.name + "的新状态是" + this.observerState);
    }

    public ConcreteSubject getSubject() {
        return subject;
    }

    public void setSubject(ConcreteSubject subject) {
        this.subject = subject;
    }
}
