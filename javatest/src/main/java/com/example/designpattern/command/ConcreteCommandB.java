package com.example.designpattern.command;

/**
 * Created by wuhai on 2017/11/14 11:46.
 * 描述：命令B
 */

public class ConcreteCommandB implements ICommand {

    private Receiver receiver;

    public ConcreteCommandB(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doB();
    }
}
