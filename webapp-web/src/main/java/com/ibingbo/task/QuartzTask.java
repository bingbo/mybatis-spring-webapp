package com.ibingbo.task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhangbingbing on 2016/12/1.
 */
public class QuartzTask {
    private static int i;
    //    private Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static {
        i=3;
    }
    public void  work(){
        ReentrantReadWriteLock.WriteLock writeLock=lock.writeLock();
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + " quartz task runing .. " + i++);
        }finally {
            writeLock.unlock();
        }
    }
}
