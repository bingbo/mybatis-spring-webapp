package com.ibingbo.design.pattern.factory_method;

/**
 * Created by bing on 17/5/27.
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new FactoryA();
        Product product = factory.createProduct();
        product.update();

        Factory factory1 = new FactoryB();
        Product product1 = factory1.createProduct();
        product1.update();
    }
}
