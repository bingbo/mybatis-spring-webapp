package com.ibingbo.task;

import org.quartz.JobExecutionException;
import org.springframework.scheduling.commonj.TimerManagerFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.TimerTask;

/**
 * Created by zhangbingbing on 2016/12/1.
 * 使用 Quartz
 * 在Spring中声明并且配置作业调度的触发方式
 */
public class SpringQuartzTimer extends QuartzJobBean{

    private int timeout;
    private static int i=0;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(Thread.currentThread().getId() + " - "+Thread.currentThread().getName() +" spring task run..." + i++);
    }



}
