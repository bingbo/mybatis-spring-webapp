package com.ibingbo.design.pattern.template_method;

/**
 * ConcreteTemplateTwo
 *
 * @author zhangbingbing
 * @date 17/12/6
 */
public class ConcreteTemplateTwo extends Template{
    protected void executeB() {
        System.out.println("execute template two b method");
    }

    protected void executeA() {
        System.out.println("execute template two a method");
    }
}
