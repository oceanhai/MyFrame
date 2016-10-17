package com.example.designpattern.decorative;

/**
 * 木匠 被装饰角色
 */
public class Carpenter implements Worker {
    @Override
    public void work() {
        System.out.println("我是木匠 我去修理木制家具3");
    }
}
