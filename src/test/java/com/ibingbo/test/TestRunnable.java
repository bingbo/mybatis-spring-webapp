package com.ibingbo.test;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangbingbing on 2016/12/2.
 */
public class TestRunnable implements Runnable {
    private int i;
    private LinkedList<Integer> list = new LinkedList<Integer>();

    {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
    }

    public void run() {
        while (!list.isEmpty()) {
            System.out.println(Thread.currentThread().getId() + " " + Thread.currentThread().getName() + " " + list.poll());
        }
    }
}
