package com.ibingbo.design.pattern.command;

/**
 * Sender
 */
public class Sender {
    private Command command;

    public void execute() {
        this.command.execute();
    }

    /**
     * 或是相当于直接传一个命令callback类
     *
     * @param command
     */
    public void execute(Command command) {
        command.execute();
    }
}
