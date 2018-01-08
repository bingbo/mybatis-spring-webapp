package com.ibingbo.design.pattern.bridge;

/**
 * App
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class App {

    private static Image image;

    public static void main(String[] args) {
        paint();
    }

    public static void paint() {
        image = new JPGImage();
        image.setImageImpl(new LinuxImpl());
        image.parseFile("aa");
    }
}
