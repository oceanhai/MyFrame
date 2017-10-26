package com.example.designpattern.adapter;

/**
 * Created by wuhai on 2017/10/26 10:39.
 * 描述：
 */

public class SourceSub2 extends Wrapper2 {
    @Override
    public void method2() {
        super.method2();
        System.out.println("the sourceable interface's second Sub2!");
    }
}
