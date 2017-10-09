package com.ibingbo.design.pattern.builder;

/**
 * AbstractBuilder
 *
 * @author zhangbingbing
 * @date 2017/9/27
 */
public abstract class AbstractBuilder<T> {

    protected T data;

    public AbstractBuilder(T data) {
        this.data = data;
    }

    public T build() {
        return this.data;
    }

}
