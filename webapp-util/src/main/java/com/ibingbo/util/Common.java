package com.ibingbo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bing on 2017/3/17.
 */
public class Common {

    public static void sayHello(String name) {
        System.out.println("hello, " + name);
    }

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Common.class);
        logger.info("hello,log4j");

    }
}
