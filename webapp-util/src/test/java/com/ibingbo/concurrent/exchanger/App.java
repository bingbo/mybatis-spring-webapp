package com.ibingbo.concurrent.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author zhangbingbing
 * @title App
 * @date 17/10/10
 */
public class App {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        new ThreadA(exchanger).start();
//        new ThreadB(exchanger).start();
    }

    public static class ThreadA extends Thread {
        private Exchanger<String> exchanger;

        public ThreadA(Exchanger<String> exchanger) {
            super();
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println("在线程A中得到线程B的值=" + exchanger.exchange("中国人A"));
                System.out.println("A end ...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadB extends Thread {
        private Exchanger<String> exchanger;

        public ThreadB(Exchanger<String> exchanger) {
            super();
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println("在线程B中得到线程A的值=" + exchanger.exchange("中国人B"));
                System.out.println("B end ...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
