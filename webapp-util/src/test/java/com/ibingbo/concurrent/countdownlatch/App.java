package com.ibingbo.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangbingbing
 * @title App
 * @date 17/10/10
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new MyThread(latch, i).start();
        }
        latch.await();
        System.out.println("all are here...");

        ShaoBing shaoBing = new ShaoBing();
        for (int i=0;i<10;i++) {
            Runner runner = new Runner(shaoBing);
            runner.setName("runner " + i);
            runner.start();
        }
        Thread.sleep(2000);
        shaoBing.doRun();
    }

    public static class MyThread extends Thread {
        private CountDownLatch latch;
        private int i;

        public MyThread(CountDownLatch latch, int i) {
            this.latch = latch;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("number " + i + " is coming...");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ShaoBing{
        private CountDownLatch latch = new CountDownLatch(1);

        public void ready() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " ready ");
            latch.await();
            System.out.println(Thread.currentThread().getName() + " end ");
        }

        public void doRun() {
            System.out.println(" run ....");
            latch.countDown();
        }
    }

    public static class Runner extends Thread {
        private ShaoBing shaoBing;

        public Runner(ShaoBing shaoBing) {
            this.shaoBing = shaoBing;
        }

        @Override
        public void run() {
            try {
                shaoBing.ready();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
