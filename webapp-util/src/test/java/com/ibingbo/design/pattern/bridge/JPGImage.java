package com.ibingbo.design.pattern.bridge;

/**
 * JPGImage
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class JPGImage extends Image{
    public void parseFile(String fileName) {
        this.imageImpl.doPaint();
        System.out.println(" jpg image");
    }
}
