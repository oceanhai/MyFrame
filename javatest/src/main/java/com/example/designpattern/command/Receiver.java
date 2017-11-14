package com.example.designpattern.command;

/**
 * Created by wuhai on 2017/11/14 11:44.
 * 描述：命令的实际执行者
 */

public class Receiver {

    public void doA(){
        System.out.println("做A事情");
    }

    public void doB(){
        System.out.println("做B事情");
    }
}
