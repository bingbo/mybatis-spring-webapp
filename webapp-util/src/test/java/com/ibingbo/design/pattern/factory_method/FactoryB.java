package com.ibingbo.design.pattern.factory_method;

/**
 * Created by bing on 17/5/27.
 */
public class FactoryB implements Factory{
    public Product createProduct() {
        return new ProductB();
    }
}
