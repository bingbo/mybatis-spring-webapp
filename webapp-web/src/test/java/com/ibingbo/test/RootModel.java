package com.ibingbo.test;

import java.util.List;

/**
 * RootModel
 *
 * @author zhangbingbing
 * @date 17/12/5
 */
public class RootModel {
    private List<Demo> demos;
    private Demo demo;
    private Demo[] demosArr;

    public List<Demo> getDemos() {
        return demos;
    }

    public void setDemos(List<Demo> demos) {
        this.demos = demos;
    }

    public Demo getDemo() {
        return demo;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    public Demo[] getDemosArr() {
        return demosArr;
    }

    public void setDemosArr(Demo[] demosArr) {
        this.demosArr = demosArr;
    }
}
