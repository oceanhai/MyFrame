package com.example.designpattern.strategymodel;

/**
 * Created by wuhai on 2017/10/18 10:34.
 * 描述：策略模式
 * 策略模式的决定权在用户，系统本身提供不同算法的实现，新增或者删除算法，对各种算法做封装。
 * 因此，策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可。
 */

public class StrategyModelTest {

    public static void main(String[] args){
        String exp1 ="2+8";
        System.out.println(exp1+"="+method01(exp1));
        String exp2 ="2-8";
        System.out.println(exp2+"="+method01(exp2));
        String exp3 ="2*8";
        System.out.println(exp3+"="+method01(exp3));

    }

    private static int method01(String exp) {
        ICalculator cal = null;
        if(exp.contains("+")){
            cal = new Plus();
            return cal.calculate(exp);
        }else if (exp.contains("-")){
            cal = new Minus();
            return cal.calculate(exp);
        }else if (exp.contains("*")){
            cal = new Multiply();
            return cal.calculate(exp);
        }
        return -1;
    }
}
