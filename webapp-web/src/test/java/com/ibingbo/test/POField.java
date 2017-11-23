package com.ibingbo.test;

/**
 * POField
 *
 * @author zhangbingbing
 * @date 17/11/21
 */
public enum POField implements APIField{
    test
    ;

    @Override
    public APIField of(String name) {
        return POField.valueOf(name);
    }
}
