package com.ibingbo.design.pattern.state;

/**
 * @author zhangbingbing
 * @title StateA
 * @date 17/10/13
 */
public class StateA implements State{
    public void handle(Context context) {
        // 下一状态是StateB
        context.setState(new StateB());
        System.out.println("do state A operation");
    }
}
