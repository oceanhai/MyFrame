package com.example.designpattern.strategymodel;

/**
 * Created by wuhai on 2017/10/19 9:50.
 * 描述：
 */

public class Plus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"\\+");
        return arrayInt[0]+arrayInt[1];
    }
}
