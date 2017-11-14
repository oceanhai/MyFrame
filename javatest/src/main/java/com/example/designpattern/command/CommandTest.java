package com.example.designpattern.command;

/**
 * Created by wuhai on 2017/11/14 11:51.
 * 描述：命令模式
 * 命令模式是为了解决命令的请求者和命令的实现者之间的耦合关系
 */

public class CommandTest {

    public static void main(String[] args){
        Invoker invoker = new Invoker();
        Receiver receiver = new Receiver();
        invoker.setCommand(new ConcreteCommandA(receiver));
        invoker.runCommand();
        invoker.setCommand(new ConcreteCommandB(receiver));
        invoker.runCommand();
    }
}
