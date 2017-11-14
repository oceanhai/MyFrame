package com.example.designpattern.command;

/**
 * Created by wuhai on 2017/11/14 11:48.
 * 描述：
 */

public class Invoker {

    private ICommand command;

    /**
     * 设置命令
     * @param command
     */
    public void setCommand(ICommand command) {
        this.command = command;
    }

    /**
     * 执行命令
     */
    public void runCommand(){
        command.execute();
    }

}
