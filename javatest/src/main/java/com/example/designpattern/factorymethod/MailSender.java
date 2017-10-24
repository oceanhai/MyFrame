package com.example.designpattern.factorymethod;

/**
 * Created by wuhai on 2017/10/19 15:39.
 * 描述：
 */

public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mailsender!");
    }
}
