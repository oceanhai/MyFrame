package com.example.designpattern.decorative;

/**
 * 水管工   被装饰角色
 */
public class Plumber implements Worker{

    @Override
    public void work() {
        System.out.println("我是水管工 我去修水管2");
    }
}
