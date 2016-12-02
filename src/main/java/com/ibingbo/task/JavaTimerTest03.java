package com.ibingbo.task;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbingbing on 2016/12/1.
 * 用ScheduledExecutorService是从的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式，相比于上两个方法，它有以下好处:
 *     1.相比于Timer的单线程，它是通过线程池的方式来执行任务的
 *     2.可以很灵活的去设定第一次执行任务delay时间
 *     3.提供了良好的约定，以便设定执行的时间间隔
 */
public class JavaTimerTest03 {
    public static void main(String[] args){
        Runnable runnable=new Runnable() {
            public void run() {
                System.out.println("hello,task test3...");
            }
        };
        ScheduledExecutorService service= Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 2, TimeUnit.SECONDS);
    }
}
