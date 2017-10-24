package com.example.designpattern.factorymethod;

/**
 * Created by wuhai on 2017/10/20 16:37.
 * 描述：
 */

public class SendFactory3 {

    public static Sender produceMail(){
        return new MailSender();
    }

    public  static Sender produceSms(){
        return new SmsSender();
    }
}
