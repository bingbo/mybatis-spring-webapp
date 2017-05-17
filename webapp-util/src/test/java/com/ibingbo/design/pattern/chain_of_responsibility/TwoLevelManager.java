package com.ibingbo.design.pattern.chain_of_responsibility;

/**
 * Created by bing on 2017/5/16.
 */
public class TwoLevelManager extends Manager{
    public TwoLevelManager(String name) {
        super(name);
    }

    public void approve(int request) {
        System.out.println("manager " + this.name + " approve request " + request);
        if (this.superior != null) {
            this.superior.approve(request);
        }
    }
}
