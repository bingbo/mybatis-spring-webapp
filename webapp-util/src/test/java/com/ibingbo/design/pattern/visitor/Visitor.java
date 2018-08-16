package com.ibingbo.design.pattern.visitor;

/**
 * Visitor
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public interface Visitor {

    public void visit(NodeA node);

    public void visit(NodeB node);
}
