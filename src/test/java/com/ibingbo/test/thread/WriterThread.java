package com.ibingbo.test.thread;

import java.io.IOException;
import java.io.PipedWriter;

/**
 * Created by zhangbingbing on 2016/12/9.
 */
public class WriterThread extends Thread{
    String[] books = new String[]{"struts1....","struts2...","spring3...","spring4..."};

    private PipedWriter pw;
    public WriterThread(){}

    public WriterThread(PipedWriter pw) {
        this.pw=pw;
    }

    public void run(){
        try {
            for (int i = 0; i < 100; i++) {
                pw.write(books[i % 4] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
