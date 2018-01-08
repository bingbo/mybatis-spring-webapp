package com.ibingbo.design.pattern.bridge;

/**
 * PNGImage
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class PNGImage extends Image{
    public void parseFile(String fileName) {
        this.imageImpl.doPaint();
        System.out.println(" png image");
    }
}
