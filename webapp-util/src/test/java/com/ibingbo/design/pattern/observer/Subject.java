package com.ibingbo.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing on 2017/5/16.
 */
public abstract class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    //增加观察者
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    //移除观察者
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    //通知
    public void doNotify() {
        for (Observer o : this.observers) {
            o.update();
        }
    }
}
