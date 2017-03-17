package com.ibingbo.proxy;

/**
 * Created by zhangbingbing on 2016/12/12.
 */
public class CTest implements ITest{
    public void say() {
        System.out.println("just say ...");
    }

    public void sayHi(String name) {
        System.out.println("just say to " + name);

    }
}
