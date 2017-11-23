package com.ibingbo.test;

/**
 * APIField
 *
 * @author zhangbingbing
 * @date 17/11/21
 */
public interface APIField {
    String name();

    int ordinal();

    APIField of(String name);
}
