package com.example.designpattern.adapter;

/**
 * Created by wuhai on 2017/10/24 15:02.
 * 描述：
 */

public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

}
