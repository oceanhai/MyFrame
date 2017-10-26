package com.example.designpattern.adapter;

/**
 * Created by wuhai on 2017/10/25 14:11.
 * 描述：
 */

public class SourceSub1 extends Wrapper2 {
    @Override
    public void method1() {
        super.method1();
        System.out.println("the sourceable interface's first Sub1!");
    }
}
