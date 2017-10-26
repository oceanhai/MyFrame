package com.example.designpattern.templatemethod;


/**
 * Created by wuhai on 2017/10/26 15:44.
 * 描述：模板方法模式
 * 解释一下模板方法模式，就是指：一个抽象类中，有一个主方法，再定义1...n个方法，可以是抽象的，
 * 也可以是实际的方法，定义一个类，继承该抽象类，重写抽象方法，通过调用抽象类，实现对子类的调用
 */

public class TemplateMethodTest {


    public static void main(String[] args){
        String exp1 ="2+8";
        System.out.println(exp1+"="+method01(exp1));
        String exp2 ="2-8";
        System.out.println(exp2+"="+method01(exp2));
        String exp3 ="2*8";
        System.out.println(exp3+"="+method01(exp3));
    }

    private static int method01(String exp) {
        AbstractCalculator cal = null;
        if(exp.contains("+")){
            cal = new Plus();
            return cal.calculate(exp,"\\+");
        }else if (exp.contains("-")){
            cal = new Minus();
            return cal.calculate(exp,"\\-");
        }else if (exp.contains("*")){
            cal = new Multiply();
            return cal.calculate(exp,"\\*");
        }
        return -1;
    }
}
