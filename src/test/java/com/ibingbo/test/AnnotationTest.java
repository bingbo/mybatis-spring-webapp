package com.ibingbo.test;

import com.ibingbo.annotation.TestableProcessor;
import com.ibingbo.annotation.example.MyTestable;
import org.junit.Test;

/**
 * Created by zhangbingbing on 2016/12/6.
 */
public class AnnotationTest {

    @Test
    public void testTestableProcessor() throws ClassNotFoundException {
        TestableProcessor.process("com.ibingbo.annotation.example.MyTestable");
    }
}
