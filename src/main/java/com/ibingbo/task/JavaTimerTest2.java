package com.ibingbo.task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhangbingbing on 2016/12/1.
 * 用Timer和TimerTask与第一种方法相比有如下好处：
 *  java.util.Timer;
 *  java.util.TimerTask;

        1. 当启动和去取消任务时可以控制

        2. 第一次执行任务时可以指定你想要的delay时间
 */
public class JavaTimerTest2 {
    public static void main(String[] args){
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello, task test2...");
            }
        };

        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 1*1000;
        timer.scheduleAtFixedRate(timerTask,delay,intevalPeriod);
    }
}
