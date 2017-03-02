package com.ibingbo.test.thread;

/**
 * Created by bing on 2017/3/1.
 */
public class WaitNotifyTest {
    public static void main(String[] args) {
        Object lock = new Object();
        new ThreadB(lock).start();
        new ThreadA(lock).start();

    }

    private static class ThreadA extends Thread {
        private Object lock;

        public ThreadA(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {

            System.out.println("A running ... ");
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    System.out.println(i);
                    if (i == 3) {
                        lock.notify();
                    }

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("A done ...");
        }
    }

    private static class ThreadB extends Thread {
        private Object lock;

        public ThreadB(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    System.out.println("B running ...");
                    lock.wait();
                    System.out.println("B done ....");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
