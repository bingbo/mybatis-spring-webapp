package com.ibingbo.task;

/**
 * 创建一个thread，然后让它在while循环里一直运行着，通过sleep方法来达到定时任务的效果
 */
public class JavaTimerTest {
    public static void main(String[] args){
        final long timeInterval = 1000;
        Runnable runnable=new Runnable() {
            public void run() {
                while (true){
                    System.out.println("hello,task test...");
                    try {
                        Thread.sleep(timeInterval);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
