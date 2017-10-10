package com.ibingbo.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author zhangbingbing
 * @title Service
 * @date 17/10/9
 */
public class Service {
    private Semaphore semaphore;

    public Service() {
        semaphore = new Semaphore(1, true);
    }

    public Service(boolean fair) {
        semaphore = new Semaphore(1, fair);
    }

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println("threadName=" + Thread.currentThread().getName() + " get in ");
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " print " + (i + 1));
            }
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
