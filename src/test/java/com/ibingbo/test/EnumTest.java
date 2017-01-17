package com.ibingbo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangbingbing on 2016/11/30.
 */
public class EnumTest {

    @Test
    public void testGender(){
        Gender f=Gender.FAMALE;
        System.out.println(f.getName());
        f.info();
        System.out.println(Gender.FAMALE);
        switch (f){
            case FAMALE:
                f.info();
                break;
            case MALE:
                f.info();
                break;
            default:
        }

        for (Gender gender : Gender.values()) {
            System.out.println(gender.ordinal());
            System.out.println(gender.getName());
            System.out.println(gender.name());
        }
    }
}
