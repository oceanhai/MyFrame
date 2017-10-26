package com.example.designpattern.templatemethod;


/**
 * Created by wuhai on 2017/10/19 9:52.
 * 描述：
 */

public class Multiply extends AbstractCalculator {

    @Override
    int calculate(int num1, int num2) {
        return num1 * num2;
    }
}
