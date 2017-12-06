package com.ibingbo.design.pattern.decorator;

/**
 * Decorator
 *
 * @author zhangbingbing
 * @date 17/12/1
 */
public abstract class Decorator implements Component{
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        if (this.component != null) {
            this.component.operation();
        }
    }
}
