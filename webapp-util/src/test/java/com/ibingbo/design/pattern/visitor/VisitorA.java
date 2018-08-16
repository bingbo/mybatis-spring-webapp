package com.ibingbo.design.pattern.visitor;

/**
 * VisitorA
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public class VisitorA implements Visitor{
    public void visit(NodeA node) {
        System.out.println(node.operation());
    }

    public void visit(NodeB node) {
        System.out.println(node.operation());
    }
}
