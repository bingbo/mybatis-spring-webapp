package com.ibingbo.test.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

/**
 * Created by zhangbingbing on 2016/12/9.
 */
public class ReaderThread extends Thread{

    private PipedReader pr;
    private BufferedReader br;

    public ReaderThread(){}

    public ReaderThread(PipedReader pr) {
        this.pr=pr;
        this.br = new BufferedReader(pr);
    }

    public void run(){
        String buf = null;
        try {
            while ((buf = br.readLine()) != null) {
                System.out.println(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
