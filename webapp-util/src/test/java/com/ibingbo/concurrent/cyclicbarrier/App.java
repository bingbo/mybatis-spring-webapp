package com.ibingbo.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhangbingbing
 * @title App
 * @date 17/10/10
 */
public class App {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println("execute one time ...");
            }
        });
        for (int i=0;i<30;i++) {
            System.out.println("number of waiting thread is " +barrier.getNumberWaiting());
            System.out.println("number of partner is " + barrier.getParties());
            new ThreadA(barrier).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadA extends Thread {
        private CyclicBarrier barrier;

        public ThreadA(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                this.barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
