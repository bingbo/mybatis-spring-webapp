package com.ibingbo.design.pattern.factory_method;

/**
 * Created by bing on 17/5/27.
 */
public class FactoryA implements Factory{
    public Product createProduct() {
        return new ProductA();
    }
}
