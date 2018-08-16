package com.ibingbo.design.pattern.visitor;

/**
 * Node
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public interface Node {
    public void accept(Visitor visitor);
}
