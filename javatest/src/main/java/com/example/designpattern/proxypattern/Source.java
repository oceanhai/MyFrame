package com.example.designpattern.proxypattern;

/**
 * Created by wuhai on 2017/10/19 11:01.
 * 描述：
 */

public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
