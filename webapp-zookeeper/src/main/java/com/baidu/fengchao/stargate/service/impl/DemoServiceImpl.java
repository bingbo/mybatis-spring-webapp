package com.baidu.fengchao.stargate.service.impl;

import com.baidu.fengchao.stargate.common.utils.ConfigUtils;
import com.baidu.fengchao.stargate.remoting.RpcContext;
import com.baidu.fengchao.stargate.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoServiceImpl implements DemoService {

    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceImpl.class);

    public String echo(String msg) {
        String echo = RpcContext.getContext().getRemoteAddressString() + " Hello" + msg;
        System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()) + echo);
        if ("timeout".equals(ConfigUtils.getSysConfig().getString("instance.tag"))) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if ("error".equals(ConfigUtils.getSysConfig().getString("instance.tag"))) {
            Object a = 1L;
            Integer b = (Integer) a;
        }
        return echo;
    }
}
