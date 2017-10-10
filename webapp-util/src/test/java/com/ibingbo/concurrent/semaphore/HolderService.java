package com.ibingbo.concurrent.semaphore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangbingbing
 * @title HolderService
 * @date 17/10/9
 */
public class HolderService {
    private volatile Semaphore getSemaphore = new Semaphore(16);
    private volatile Semaphore setSemaphore = new Semaphore(10);
    private volatile ReentrantLock lock = new ReentrantLock();
    private volatile Condition setCondition = lock.newCondition();
    private volatile Condition getCondition = lock.newCondition();
    private volatile LinkedList<String> data = new LinkedList<String>();
    private volatile int count = 5;

    private boolean isFull() {
        return this.data.size() == this.count;
    }

    private boolean isEmpty() {
        return this.data.isEmpty();
    }

    public void get() {
        try {
            getSemaphore.acquire();
            lock.lock();
            while (isEmpty()) {
                getCondition.await();
            }

            System.out.println(Thread.currentThread().getName() + " get " + this.data.remove());
            Thread.sleep(1000);
            setCondition.signalAll();
            lock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getSemaphore.release();
        }
    }

    public void set() {
        try {
            setSemaphore.acquire();
            lock.lock();
            while (isFull()) {
                setCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + " set test " + this.data.add("test"));
            Thread.sleep(1000);
            getCondition.signalAll();
            lock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            setSemaphore.release();
        }
    }
}
