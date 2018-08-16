package com.ibingbo.design.pattern.visitor;

/**
 * NodeA
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public class NodeA implements Node{
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operation() {
        return "NodeA";
    }
}
