package com.ibingbo.thread;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangbingbing on 2016/12/9.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        {
            list = Collections.synchronizedList(list);
            for(int i=0;i<100;i++){
                list.add(i);
            }
        }
        ExecutorService pool = Executors.newFixedThreadPool(6);
        pool.submit(new TestThread(list));
        pool.submit(new TestThread(list));

    }
}

class TestThread implements Runnable{

    private List<Integer> list;

    public TestThread(List<Integer> list) {
        this.list=list;
    }
    public void run() {
        for(Integer i:list) {
            System.out.println(Thread.currentThread().getName() + " i=" + i);
        }
    }
}
