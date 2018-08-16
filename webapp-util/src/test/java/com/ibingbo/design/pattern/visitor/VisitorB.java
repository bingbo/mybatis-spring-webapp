package com.ibingbo.design.pattern.visitor;

/**
 * VisitorB
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public class VisitorB implements Visitor{
    public void visit(NodeA node) {
        System.out.println(node.operation());
    }

    public void visit(NodeB node) {
        System.out.println(node.operation());
    }
}
