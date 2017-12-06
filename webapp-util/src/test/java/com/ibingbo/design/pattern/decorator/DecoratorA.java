package com.ibingbo.design.pattern.decorator;

/**
 * DecoratorA
 *
 * @author zhangbingbing
 * @date 17/12/1
 */
public class DecoratorA extends Decorator {

    public DecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        this.operationInternal();
    }

    private void operationInternal() {
        System.out.println("my self operation");
    }
}
