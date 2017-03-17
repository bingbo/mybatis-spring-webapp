package com.ibingbo.test;

import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by zhangbingbing on 2016/12/10.
 */
public class Test {
    static {
        flag=2;
    }
    private static Integer flag=0;

    public static void main(String[] args) {
        System.out.println(flag);
        URL[] ruls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : ruls) {
            System.out.println(url.toExternalForm());
        }
    }

}
