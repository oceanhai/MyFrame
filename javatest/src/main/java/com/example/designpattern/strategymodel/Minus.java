package com.example.designpattern.strategymodel;

/**
 * Created by wuhai on 2017/10/19 9:51.
 * 描述：
 */

public class Minus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"-");
        return arrayInt[0]-arrayInt[1];
    }

}
