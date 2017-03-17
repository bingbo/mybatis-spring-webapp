package com.ibingbo.annotation.example;

import com.ibingbo.annotation.Testable;

/**
 * Created by zhangbingbing on 2016/12/6.
 */
public class MyTestable {

    @Testable
    public static void m1() {
        System.out.println("@m1");
    }

    public static void m2() {
        System.out.println("m2");
    }

    @Testable
    public static void m3() {
        System.out.println("@m3");
    }

    public static void m4() {
        System.out.println("m4");
    }
}
