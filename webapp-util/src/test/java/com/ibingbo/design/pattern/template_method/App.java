package com.ibingbo.design.pattern.template_method;

/**
 * App
 *
 * @author zhangbingbing
 * @date 17/12/6
 */
public class App {
    private static Template template = null;

    public static void main(String[] args) {
        template = new ConcreteTemplateOne();
        template.execute();

        template = new ConcreteTemplateTwo();
        template.execute();
    }
}
