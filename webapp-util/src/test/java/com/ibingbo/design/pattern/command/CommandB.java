package com.ibingbo.design.pattern.command;

/**
 * CommandB
 *
 * @author zhangbingbing
 * @date 17/12/6
 */
public class CommandB implements Command{
    private Receiver receiver;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        this.receiver.action();
    }
}
