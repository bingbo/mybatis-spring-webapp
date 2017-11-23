package com.ibingbo.test.handler;

/**
 * UserModelHandler
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public class UserModelHandler extends AbstractModelHandler<UserModel>{
    @Override
    public void handle(UserModel model) {
        System.out.println("student model handler");
    }
}
