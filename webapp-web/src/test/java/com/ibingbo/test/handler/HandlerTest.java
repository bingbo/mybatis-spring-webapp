package com.ibingbo.test.handler;

import java.util.ArrayList;
import java.util.List;

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
        List<AbstractModelHandler> handlers = new ArrayList<>();
        handlers.add(new StudentModelHandler());
        handlers.add(new UserModelHandler());
        UserModel model = new UserModel();
        AbstractModelHandler modelHandler = null;
        for (AbstractModelHandler handler : handlers) {
            if (handler.supportsModelType(model.getClass())) {
                modelHandler = handler;
            }
        }
        modelHandler.handle(model);
    }
}
