package com.ibingbo.design.pattern.state;

/**
 * @author zhangbingbing
 * @title StateC
 * @date 17/10/13
 */
public class StateC implements State{
    public void handle(Context context) {
        // 下一状态是StateA
        context.setState(new StateA());
        System.out.println("do state C operation");
    }
}
