package com.ibingbo.test;

import com.ibingbo.test.thread.ReaderThread;
import com.ibingbo.test.thread.WriterThread;
import org.junit.Test;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

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
    public void testPipe(){
        PipedReader pr=null;
        PipedWriter pw=null;
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
}
