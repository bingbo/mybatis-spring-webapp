package com.ibingbo.test;

import com.ibingbo.models.User;
import com.ibingbo.test.thread.ReaderThread;
import com.ibingbo.test.thread.TestCallable;
import com.ibingbo.test.thread.WriterThread;
import com.ibingbo.thread.DateFormatTest;
import org.junit.Test;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by zhangbingbing on 2016/12/2.
 */
public class ThreadServiceTest {

    @Test
    public void testThread() throws InterruptedException {
        TestRunnable thread = new TestRunnable();
        new Thread(thread, "thread1").start();
        new Thread(thread, "thread2").start();
        new Thread(thread, "thread3").start();
    }

    @Test
    public void testPipe() {
        PipedReader pr = null;
        PipedWriter pw = null;
        try {
            pw = new PipedWriter();
            pr = new PipedReader();
            pw.connect(pr);
            new WriterThread(pw).start();
            new ReaderThread(pr).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCallable() {
        TestCallable callable = new TestCallable();
        FutureTask<Integer> task = new FutureTask<Integer>(callable);
        new Thread(task, "有返回值的线程1").start();
        new Thread(task, "有返回值的线程2").start();
        try {
            System.out.println("子线程的返回值：" + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFormat(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new DateFormatTest("A", "1991-09-13", true));
        executorService.execute(new DateFormatTest("B","2013-09-13",false));
        executorService.shutdown();
        ThreadLocal<User> threadLocal=new ThreadLocal<User>() {
            @Override
            protected User initialValue() {
                return new User();
            }
        };
    }

    @Test
    public void testExecutor() {

    }
}
