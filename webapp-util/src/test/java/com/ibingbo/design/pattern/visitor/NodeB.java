package com.ibingbo.design.pattern.visitor;

/**
 * NodeB
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public class NodeB implements Node{
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operation() {
        return "NodeB";
    }
}
