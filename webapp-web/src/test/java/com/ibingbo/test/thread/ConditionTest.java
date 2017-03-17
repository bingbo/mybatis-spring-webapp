package com.ibingbo.test.thread;

import com.kenai.jaffl.annotations.In;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bing on 2017/3/1.
 */
public class ConditionTest {
    /**
     * 模拟打印机服务
     */
    static class Service {
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        //模拟队列
        private List<String> queue = new ArrayList<>();

        /**
         * 输入处理
         * @param value
         */
        public void input(String value) {
            try {
                //先获取锁
                lock.lock();
                //判断队列不为空时等待打印完再输入
                while (!queue.isEmpty()) {
                    condition.await();

                }
                queue.add(value);
                System.out.println("input " + value);
                System.out.println("there are " + lock.getWaitQueueLength(condition) + " thread wait condition ..");
                //通知唤醒打印服务
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();

            }
        }

        /**
         * 输出打印处理
         */
        public void print() {
            try {
                //获取锁对象
                lock.lock();
                //判断队列为空时，等待输入
                while (queue.isEmpty()) {
                    condition.await();
                }
                String value = queue.remove(0);
                System.out.println("print " + value);
                System.out.println("there are " + lock.getWaitQueueLength(condition) + " thread wait condition ..");
                //打印完，通知唤醒输入线程输入
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 模拟用户输入操作线程
     */
    static class InputTask implements Runnable {
        private Service service;

        public InputTask(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                service.input("message " + new Random().nextInt());
                //模拟输入耗时
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 模拟打印机打印服务线程
     */
    static class PrintTask implements Runnable {
        private Service service;

        public PrintTask(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                service.print();
            }
        }
    }

    public static void main(String[] args) {
        /*Service service = new Service();
        new Thread(new InputTask(service)).start();
        new Thread(new PrintTask(service)).start();

        for (int i=0;i<10;i++) {
            new Thread(new InputTask(service)).start();
            new Thread(new PrintTask(service)).start();
        }*/

        ReentrantLock lock = new ReentrantLock();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            list.add(i);
        }
        for (int i = 0; i < 5; i++) {
            //new ConsumeThread(list).start();
            new Thread(new ConsumeThread2(list,lock)).start();
        }
    }

    /**
     * 继承thread的方式
     */
    static class ConsumeThread extends Thread {
        private List<Integer> list;

        public ConsumeThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            //循环处理数据
            while (true) {
                //先锁定list进行处理
                synchronized (list) {
                    if (!list.isEmpty()) {
                        int val = list.remove(0);
                        System.out.println("thread " + Thread.currentThread().getId() + " take number " + val);
                    } else {
                        break;
                    }
                }
                //模拟处理耗时
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }



    }

    /**
     * 通过实现runnable接口的方式
     */
    static class ConsumeThread2 implements Runnable {
        private List<Integer> linkedList;
        private ReentrantLock lock;

        ConsumeThread2(List linkedList, ReentrantLock lock) {
            this.linkedList = linkedList;
            this.lock = lock;
        }
        @Override
        public void run() {
            //循环处理list
            while (true) {
                //先获取锁，读写list数据
                try {
                    lock.lock();
                    if (!linkedList.isEmpty()) {
                        int val = linkedList.remove(0);
                        System.out.println("thread " + Thread.currentThread().getId() + " take number " + val);
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //最后释放锁
                    lock.unlock();
                }
                //模仿处理耗时
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
