package com.ibingbo.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhangbingbing
 * @title App
 * @date 17/10/10
 */
public class App {
    public static void main(String[] args) {

        try {
            MyRecursiveTask task = new MyRecursiveTask(1, 10);
            ForkJoinPool pool = new ForkJoinPool();
            pool.submit(task);
            System.out.println(task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class MyRecursiveTask extends RecursiveTask<Integer> {
        private int begin;
        private int end;

        public MyRecursiveTask(int begin, int end) {
            super();
            this.begin = begin;
            this.end = end;
            System.out.println("# " + (begin + " " + end));
        }

        protected Integer compute() {
            Integer sum=0;
            if ((end - begin) > 2) {
                int middle = (end - begin) / 2;
                MyRecursiveTask leftTask = new MyRecursiveTask(begin, middle);
                MyRecursiveTask rightTask = new MyRecursiveTask(middle + 1, end);
                this.invokeAll(leftTask, rightTask);
                return leftTask.join() + rightTask.join();
            } else {
                return begin + end;

            }
        }
    }

}
