package com.ibingbo.test;

import org.junit.*;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by bing on 2017/2/8.
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i=0;i<10;i++) {
            new Thread(new Worker(latch,8000-i*100,i)).start();
        }
        latch.await();
        System.out.println("all done");
    }

    static class Worker implements Runnable {
        private CountDownLatch latch;
        private int workTime;
        private int num;

        public Worker(CountDownLatch latch, int workTime,int num) {
            this.latch = latch;
            this.workTime = workTime;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.num+" done");
            this.latch.countDown();
        }
    }




}
