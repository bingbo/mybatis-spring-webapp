package com.ibingbo.design.pattern.template_method;

/**
 * Template
 *
 * @author zhangbingbing
 * @date 17/12/6
 */
public abstract class Template {

    public void execute() {
        this.executeA();
        this.executeB();
    }

    protected abstract void executeB();

    protected abstract void executeA();

}
