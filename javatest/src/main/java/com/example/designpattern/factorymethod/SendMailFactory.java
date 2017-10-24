package com.example.designpattern.factorymethod;

/**
 * Created by wuhai on 2017/10/24 10:30.
 * 描述：
 */

public class SendMailFactory extends Provider {

    @Override
    Sender produce() {
        return new MailSender();
    }
}
