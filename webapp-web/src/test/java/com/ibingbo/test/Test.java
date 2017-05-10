package com.ibingbo.test;

import sun.misc.Launcher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

/**
 * Created by zhangbingbing on 2016/12/10.
 */
public class Test implements Serializable{
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
    static class A implements Serializable{
        public static String staticStr = "A:staticStr";

        public void say() {
            System.out.println("say A");
        }

        public static void staticMethod() {
            System.out.println("A:staticMethod");
        }
    }

    abstract class B extends A implements Serializable{
        public void say() {
            super.say();
            System.out.println("say B");
        }

    }

    static class C extends A implements Serializable{
        char b='中';
        private String name = "aaa";
        public static String staticStr = "aaa";

        public static void staticMethod() {
            System.out.println("static method");
        }
        private static final long serialVersionUID = -6849794470754667710L;
        public void hi(String a) {
            switch (a) {
                case "A":
                    System.out.println("A");
                    break;
                case "B":
                    System.out.println("B");
                    break;
                default:
                    break;
            }
        }

    }

    static class D extends A{}

    @org.junit.Test
    public void testSay() {
        C c=new C();
        c.say();
        c.hi("A");
        System.out.println(C.staticStr);
        C.staticMethod();
        ObjectOutputStream objectOutputStream=null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("tmp"));
            objectOutputStream.writeObject(c);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(D.staticStr);
        D.staticMethod();

    }

}
