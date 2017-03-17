package com.ibingbo.task;

import org.springframework.stereotype.Service;

/**
 * Created by zhangbingbing on 2016/12/1.
 */
@Service
public class SpringTask {

    public void job1(){
        System.out.println(Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + " spring task job1 runing .. ");
    }
}
