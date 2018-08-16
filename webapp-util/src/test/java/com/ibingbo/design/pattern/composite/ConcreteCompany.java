package com.ibingbo.design.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteCompany
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class ConcreteCompany extends Company {
    private List<Company> companies = new ArrayList<Company>();

    public ConcreteCompany(String name) {
        super(name);
    }

    public void add(Company company) {
        this.companies.add(company);
    }

    public void remove(Company company) {
        this.companies.remove(company);
    }

    public void display(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(sb.append(this.name).toString());
        for (Company company : companies) {
            company.display(depth + 2);
        }
    }

    public void doDuty() {
        for (Company company : companies) {
            company.doDuty();
        }
    }
}
