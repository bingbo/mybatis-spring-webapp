package com.ibingbo.test.thread;

import java.util.concurrent.Callable;

/**
 * Created by zhangbingbing on 2016/12/9.
 */
public class TestCallable implements Callable<Integer>{
    public Integer call() throws Exception {
        int i=0;
        for(;i<10000;i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值：" + i);
        }
        return i;
    }
}
