package com.ibingbo.annotation;

import java.lang.reflect.Method;

/**
 * Created by zhangbingbing on 2016/12/6.
 */
public class TestableProcessor {

    public static void process(String clazz) throws ClassNotFoundException {
        int passed=0;
        int failed=0;
        for(Method m:Class.forName(clazz).getMethods()){
            if(m.isAnnotationPresent(Testable.class)){
                try {
                    m.invoke(null);
                    passed++;
                }catch (Exception e){
                    failed++;
                }
            }
        }
        System.out.println("run some methods"+(passed+failed)+" pass:"+passed+" fail: "+failed);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        process("com.ibingbo.annotation.example.MyTestable");
    }
}
