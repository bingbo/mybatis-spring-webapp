package com.ibingbo.design.pattern.bridge;

/**
 * LinuxImpl
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class LinuxImpl implements ImageImpl{
    public void doPaint() {
        System.out.println("in linux ");

    }
}
