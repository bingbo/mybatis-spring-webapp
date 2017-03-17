package com.ibingbo.test;

/**
 * Created by zhangbingbing on 2016/11/30.
 */
public enum Gender implements GenderDesc{
    MALE("男") {
        public void info() {
            System.out.println("this is a man...");
        }
    },
    FAMALE("女") {
        public void info() {
            System.out.println("this is a woman");
        }
    };

    private String name;

    private Gender(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
