package com.ibingbo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbingbing on 2016/12/13.
 */
public class DateFormatTest extends Thread{

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

    private String name;
    private String dateStr;
    private boolean sleep;

    public DateFormatTest(String name, String dateStr, boolean sleep) {
        this.name=name;
        this.dateStr=dateStr;
        this.sleep=sleep;
    }

    @Override
    public void run() {
        Date date=null;
        if (sleep) {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(name + ": date: " + date);
    }
}
