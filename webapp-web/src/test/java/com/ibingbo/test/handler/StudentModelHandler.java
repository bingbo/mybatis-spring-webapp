package com.ibingbo.test.handler;

/**
 * StudentModelHandler
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public class StudentModelHandler extends AbstractModelHandler<StudentModel>{

    @Override
    public void handle(StudentModel model) {
        System.out.println("student model handler");
    }
}
