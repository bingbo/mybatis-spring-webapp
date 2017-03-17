package com.ibingbo.test;

import java.util.List;

/**
 * Created by zhangbingbing on 2016/12/5.
 */
public class Canvas {
    public void drawAll(List<?> shapes){
        for(Object shape:shapes){

            Shape a=(Shape)shape;
            a.draw();
        }
    }
}
