package com.example.designpattern.factorymethod;

/**
 * Created by wuhai on 2017/10/24 10:31.
 * 描述：
 */

public class SendSmsFactory extends Provider {

    @Override
    Sender produce() {
        return new SmsSender();
    }
}
