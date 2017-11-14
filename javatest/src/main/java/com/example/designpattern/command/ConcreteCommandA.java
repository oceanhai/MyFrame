package com.example.designpattern.command;

/**
 * Created by wuhai on 2017/11/14 11:46.
 * 描述：命令A
 */

public class ConcreteCommandA implements ICommand {

    private Receiver receiver;

    public ConcreteCommandA(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doA();
    }
}
