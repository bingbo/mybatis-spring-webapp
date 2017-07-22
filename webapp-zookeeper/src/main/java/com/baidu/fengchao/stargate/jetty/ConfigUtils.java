/*
 * Baidu.com Inc.
 * Copyright (c) 2000-2012 All Rights Reserved.
 */
package com.baidu.fengchao.stargate.jetty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    static Properties properties;

    static {
        InputStream in = JettyAware.class.getClassLoader().getResourceAsStream("jetty.properties");
        properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static Integer getJettyPort() {
        String portStr = properties.getProperty("jetty.port");
        return Integer.parseInt(portStr);
    }

}
