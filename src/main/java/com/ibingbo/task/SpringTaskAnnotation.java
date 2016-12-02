package com.ibingbo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhangbingbing on 2016/12/1.
 * spring task 注解方式
 * 需要在xml里开启注解<task:annotation-driven scheduler="qbScheduler" mode="proxy"/>
 */
@Component("springTaskAnnotation")
public class SpringTaskAnnotation {
    private static Integer i;
    private static Boolean loaded = false;
    private static LinkedList list = new LinkedList();

    private final static ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private static LinkedList list2 = new LinkedList();
    private static Boolean loaded2 = false;

    static {
        i = 3;

    }

    //@Scheduled(cron = "*/5 * * * * ?")
    public void work() {
        synchronized (i) {
            System.out.println(Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + " spring task annotation runing .. " + i++);
        }
    }

    {

    }

    //@Scheduled(cron = "0 0/1 * * * ?")
    public void work2() {

        synchronized (loaded) {
            if (!loaded) {
                for (int i = 0; i < 1000; i++) {
                    list.add(i);
                }
                loaded = true;
            }
        }
        while (true) {
            synchronized (list) {
                Object o = list.poll();
                if (o == null) {
                    loaded = false;
                    break;

                }
                System.out.println(Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + " spring task annotation runing .. " + o.toString() + " size:" + list.size());
            }
        }
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    public void work3() {
        rw.writeLock().lock();
        try {
            if(!loaded2){
                for(int i=0;i<1000;i++){
                    list2.add(1000


                            + i);
                }
                loaded2=true;
            }
        }finally {
            rw.writeLock().unlock();
        }

        while (true){
            rw.writeLock().lock();
            try {
                Object o=list2.poll();
                if(o==null){
                    loaded2=false;
                    break;
                }
                System.out.println(Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + "  work 2.. " + o.toString() + " size:" + list2.size());

            }finally {
                rw.writeLock().unlock();
            }
        }
    }
}
