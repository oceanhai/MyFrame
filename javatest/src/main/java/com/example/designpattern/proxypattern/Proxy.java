package com.example.designpattern.proxypattern;

/**
 * Created by wuhai on 2017/10/19 11:06.
 * 描述：
 */

public class Proxy implements Sourceable {

    private Source source;

    public Proxy() {
        source = new Source();
    }

    @Override
    public void method() {
        before();
        source.method();
        after();
    }

    private void after() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }

}
