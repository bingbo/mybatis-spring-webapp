package com.ibingbo.design.pattern.bridge;

/**
 * Image
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public abstract class Image {

    protected ImageImpl imageImpl;

    public void setImageImpl(ImageImpl impl) {
        this.imageImpl = impl;
    }

    public abstract void parseFile(String fileName);
}
