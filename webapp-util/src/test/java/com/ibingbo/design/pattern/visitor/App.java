package com.ibingbo.design.pattern.visitor;

/**
 * App
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public class App {

    public static void main(String[] args) {
        NodeContext context = new NodeContext();
        context.add(new NodeA());
        context.add(new NodeB());
        Visitor visitor = new VisitorA();
        context.accept(visitor);

        visitor = new VisitorB();
        context.accept(visitor);

    }
}
