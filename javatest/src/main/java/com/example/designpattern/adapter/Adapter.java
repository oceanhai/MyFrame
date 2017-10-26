package com.example.designpattern.adapter;

/**
 * Created by wuhai on 2017/10/24 14:42.
 * 描述：
 */

public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
