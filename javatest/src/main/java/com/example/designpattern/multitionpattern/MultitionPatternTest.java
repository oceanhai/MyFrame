package com.example.designpattern.multitionpattern;

/**
 * Created by wuhai on 2017/10/19 15:19.
 * 描述：
 */

public class MultitionPatternTest {
    public static void main(String[] args) {
        int ministerNum = 10; //10个大臣
        for (int i = 0; i < ministerNum; i++) {
            Emperor emperor = Emperor.getInstance();
            System.out.print("第" + (i + 1) + "个大臣参拜的是：");
            emperor.emperorInfo();
        }
    }
}
