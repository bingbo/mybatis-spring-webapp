package com.ibingbo.design.pattern.chain_of_responsibility;

/**
 * Created by bing on 2017/5/16.
 */
public class Client {
    public static void main(String[] args) {
        Manager one = new OneLevelManager("A");
        Manager two = new TwoLevelManager("B");
        Manager three = new ThreeLevelManager("C");
        one.setSuperior(two);
        two.setSuperior(three);

        one.approve(1);
        one.approve(2);
        one.approve(3);

    }
}
