package com.ibingbo.design.pattern.composite;

/**
 * App
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class App {

    public static void main(String[] args) {
        ConcreteCompany concreteCompany = new ConcreteCompany("beijing");
        concreteCompany.add(new HRDepartment("hr"));
        concreteCompany.add(new FinanceDepartment("finance"));

        ConcreteCompany concreteCompany1 = new ConcreteCompany("shanghai");
        concreteCompany.add(new HRDepartment("hr"));
        concreteCompany.add(new FinanceDepartment("finance"));

        concreteCompany.add(concreteCompany1);

        concreteCompany.display(1);

//        concreteCompany.doDuty();
    }
}
