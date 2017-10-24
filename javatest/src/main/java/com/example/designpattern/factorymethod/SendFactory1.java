package com.example.designpattern.factorymethod;

/**
 * Created by wuhai on 2017/10/20 16:37.
 * 描述：
 */

public class SendFactory1 {
    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}
