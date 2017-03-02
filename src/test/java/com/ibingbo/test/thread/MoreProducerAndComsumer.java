package com.ibingbo.test.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bing on 2017/3/1.
 */
public class MoreProducerAndComsumer {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Object lock = new Object();
        //先初始化多个消费者线程
        for (int i=0;i<10;i++) {
            new Thread(new TConsumer(lock,list, i)).start();
        }

        //开始发送生产消息
        for (int j=0;j<100;j++) {
            new Thread(new TProducer(lock, list,j)).start();
        }

        Thread ft = new Thread(new FirstThread());
        Thread st = new Thread(new SecondThread());
        ft.start();
        ft.join(); //两个线程顺序执行，第一个执行完才执行第二个,join()有使线程排队运行的作用
        st.start();

    }

    /**
     * 模拟生产者
     */
    private static class TProducer implements Runnable {
        private Object lock;
        private int i;
        private List<String> list;

        public TProducer(Object lock,List<String> list,int i) {
            super();
            this.lock = lock;
            this.i=i;
            this.list = list;
        }

        @Override
        public void run() {
            System.out.println(this.i+" produced ...");
            //获取对象锁，添加消息，并通知
            synchronized (lock) {
                list.add(i + " message ");
                lock.notify();
            }

        }
    }

    /**
     * 模拟消费者
     */
    private static class TConsumer implements Runnable {
        private Object lock;
        private int i;
        private List<String> list;

        public TConsumer(Object lock, List<String> list, int i) {
            super();
            this.lock = lock;
            this.i = i;
            this.list = list;
        }

        @Override
        public void run() {
            try {
                //循环获取锁并进行消费消息
                while (true) {
                    synchronized (lock) {
                        //如果没有消息，则等待
                        if (list.isEmpty()) {
                            lock.wait();
                        }
                        String msg = list.remove(0);
                        System.out.println(this.i + " comsume .. " + msg);
                    }
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class FirstThread implements Runnable {
        @Override
        public void run() {

            System.out.println("****************");
            for (int i=0;i<10;i++) {
                System.out.println("first running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("****************");

        }
    }
    static class SecondThread implements Runnable {
        @Override
        public void run() {
            System.out.println("---------------");
            for (int i=0;i<10;i++) {
                System.out.println("second running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("---------------");

        }
    }
}
