package com.ibingbo.design.pattern.state;

/**
 * @author zhangbingbing
 * @title StateB
 * @date 17/10/13
 */
public class StateB implements State{
    public void handle(Context context) {

        // 下一状态是StateC
        context.setState(new StateC());
        System.out.println("do state B operation");
    }
}
