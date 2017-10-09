package com.ibingbo.design.pattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author zhangbingbing
 * @title Test
 * @date 17/10/9
 */
public class Test {

    // 立即加载方式==饿汉模式
    public static class AObject {
        // 立即加载方式==饿汉模式
        private static AObject instance = new AObject();

        private AObject() {

        }

        public static AObject getInstance() {
            return instance;
        }
    }

    /**
     * 懒汉模式
     */
    public static class BObject {
        private static BObject instance;

        private BObject() {

        }

        /**
         * 在整个方法上加锁
         *
         * @return
         */
        public static synchronized BObject getInstance() {
            try {
                if (instance == null) {
                    // 模拟在创建对象之前做一些准备工作
                    Thread.sleep(1000);
                    instance = new BObject();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return instance;
        }
    }

    /**
     * 使用静态内置类实现单例模式
     */
    public static class CObject {
        // 内部类方式
        private static class ObjectHandler {
            private static CObject instance = new CObject();
        }

        private CObject() {
        }

        public static CObject getInstance() {
            return ObjectHandler.instance;
        }
    }

    /**
     * 序列化与反序列化的单例模式实现
     * 静态内置类可以达到线程安全问题，但如果遇到序列化对象时，使用默认的方式运行得到的结棍还是多例的
     */
    public static class DObject implements Serializable {
        private static final long serialVersionUID = 888L;

        private static class ObjectHandler {
            private static final DObject instance = new DObject();
        }

        private DObject() {

        }

        public static DObject getInstance() {
            return ObjectHandler.instance;
        }

        // 在反序列化中使用该方法
        protected Object readResolve() throws ObjectStreamException {
            return ObjectHandler.instance;
        }
    }

    /**
     * 使用static代码块实现单例模式
     * 静态代码块中的代码在使用类的时候就已经执行了，所以可以应用静态代码块的这个特性来实现单例模式
     */
    public static class EObject {
        private static EObject instance = null;

        private EObject() {

        }
        static {
            instance = new EObject();
        }

        public static EObject getInstance() {
            return instance;
        }
    }

    /**
     * 使用enum枚举数据类型实现单例模式
     * 枚举enum和静态代码块的特性相似，在使用枚举类时，构造方法会被自动调用，也可以应用这个特性实现单例模式
     */
    public static class MyObject{
        public enum MyEnumSingleton{
            INSTANCE;
            private MyObject instance;

            MyEnumSingleton() {
                instance = new MyObject();
            }

            public MyObject getInstance() {
                return instance;
            }
        }

        public static MyObject getInstance() {
            return MyEnumSingleton.INSTANCE.getInstance();
        }
    }
}
