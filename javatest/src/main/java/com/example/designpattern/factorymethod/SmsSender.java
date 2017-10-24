package com.example.designpattern.factorymethod;

/**
 * Created by wuhai on 2017/10/20 16:32.
 * 描述：
 */

public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
