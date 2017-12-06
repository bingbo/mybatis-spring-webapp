package com.ibingbo.design.pattern.template_method;

/**
 * ConcreteTemplateOne
 *
 * @author zhangbingbing
 * @date 17/12/6
 */
public class ConcreteTemplateOne extends Template{
    protected void executeB() {
        System.out.println("execute template one b method");
    }

    protected void executeA() {
        System.out.println("execute template one a method");
    }
}
