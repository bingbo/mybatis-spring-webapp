package com.ibingbo.design.pattern.bridge;

/**
 * WindowsImpl
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class WindowsImpl implements ImageImpl{
    public void doPaint() {
        System.out.println("in windows ");
    }
}
