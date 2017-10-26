package com.example.designpattern.templatemethod;


/**
 * Created by wuhai on 2017/10/19 9:50.
 * 描述：
 */

public class Plus extends AbstractCalculator{

    @Override
    int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
