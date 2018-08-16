package com.ibingbo.design.pattern.composite;

/**
 * HRDepartment
 *
 * @author zhangbingbing
 * @date 17/12/13
 */
public class HRDepartment extends Company{
    public HRDepartment(String name) {
        super(name);
    }

    public void add(Company company) {

    }

    public void remove(Company company) {

    }

    public void display(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(sb.append(this.name).toString());
    }

    public void doDuty() {
        System.out.println(this.name);
    }
}
