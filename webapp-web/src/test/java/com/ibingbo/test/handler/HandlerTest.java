package com.ibingbo.test.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

/**
 * HandlerTest
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public class HandlerTest {

    @Test
    public void testHandler() {
//        List<AbstractModelHandler> handlers = new ArrayList<>();
//        handlers.add(new StudentModelHandler());
//        handlers.add(new UserModelHandler());
//        UserModel model = new UserModel();
//        AbstractModelHandler modelHandler = null;
//        for (AbstractModelHandler handler : handlers) {
//            if (handler.supportsModelType(model.getClass())) {
//                modelHandler = handler;
//            }
//        }
//        modelHandler.handle(model);
    }

    @Test
    public void testA() {
        List<Model> models = new ArrayList<>();
        List<Model> models1 = new ArrayList<>();
        for (int i=0;i<5;i++) {
            UserModel userModel = new UserModel(i);
            StudentModel studentModel = new StudentModel(i);
            models.add(userModel);
            models.add(studentModel);
        }
        for (int i=0;i<5;i++) {
            UserModel userModel = new UserModel(i);
            StudentModel studentModel = new StudentModel(i);
            models1.add(userModel);
            models1.add(studentModel);
        }
        models.add(new UserModel(6));
        models.add(new StudentModel(6));

        Collection<Model> newModels = CollectionUtils.subtract(models, models1);
        System.out.println("aa");
    }



}
