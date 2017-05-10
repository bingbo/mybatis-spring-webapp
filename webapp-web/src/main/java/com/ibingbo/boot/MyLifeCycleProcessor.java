package com.ibingbo.boot;

import org.springframework.context.LifecycleProcessor;

/**
 * Created by bing on 2017/5/8.
 */
public class MyLifeCycleProcessor implements LifecycleProcessor {
    @Override
    public void onRefresh() {
        System.out.println("LifecycleProcessor--------onRefresh");
    }

    @Override
    public void onClose() {
        System.out.println("LifecycleProcessor--------onClose");

    }

    @Override
    public void start() {

        System.out.println("LifecycleProcessor--------start");
    }

    @Override
    public void stop() {

        System.out.println("LifecycleProcessor--------stop");
    }

    @Override
    public boolean isRunning() {
        System.out.println("LifecycleProcessor--------onRefresh");
        return false;
    }
}
