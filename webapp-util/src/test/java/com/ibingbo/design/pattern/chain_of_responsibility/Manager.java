package com.ibingbo.design.pattern.chain_of_responsibility;

/**
 * Created by bing on 2017/5/16.
 */
public abstract class Manager {
    protected String name;
    protected Manager superior;

    public Manager(String name) {
        this.name = name;
    }

    public abstract void approve(int request);

    public Manager getSuperior() {
        return superior;
    }

    public void setSuperior(Manager superior) {
        this.superior = superior;
    }
}
