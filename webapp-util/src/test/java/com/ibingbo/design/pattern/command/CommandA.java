package com.ibingbo.design.pattern.command;

/**
 * CommandA
 *
 * @author zhangbingbing
 * @date 17/12/6
 */
public class CommandA implements Command{

    private Receiver receiver;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        this.receiver.action();
    }
}
