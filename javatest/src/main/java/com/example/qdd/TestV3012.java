package com.example.qdd;

import java.math.BigDecimal;

/**
 * Created by wuhai on 2018/5/18.
 */

public class TestV3012 {

    public static void main(String[] args){
        
        
//        method04(1000,0.09,1);

        System.out.println("result1："+StringUtil.formatMoney("0",2));
        System.out.println("result1："+StringUtil.formatMoney("80",2));
        System.out.println("result2："+StringUtil.formatMoney("80.5",2));
        System.out.println("result3："+StringUtil.formatMoney("80.50",2));
        System.out.println("result4："+StringUtil.formatMoney("8820548.85",2));
    }

    /**
     * 还本付息
     * @param money
     * @param rateYear  年利率
     * @param month     月
     */
    private static void method04(int money, double rateYear, int month) {
        double repayMoney = money*(1+(rateYear/12)*month);
        System.out.println("还本付息:"+repayMoney);
    }

    /**
     * 等额本息
     * @param money
     * @param rateYear
     * @param month
     */
    private static void method03(int money, double rateYear, int month) {
        /**
         * 等额本息
         */
        double repayMoney = (money*(rateYear/12)*Math.pow(1+(rateYear/12),month))/(Math.pow(1+(rateYear/12),month)-1);
        System.out.println("repayMoney:"+repayMoney);
    }

    /**
     * 等额本息
     * @param money
     * @param rateYear
     * @param year
     */
    private static void method02(int money, double rateYear, int year) {
        /**
         * 等额本息
         */
        double repayMoney = (money*(rateYear/12)*Math.pow(1+(rateYear/12),year*12))/(Math.pow(1+(rateYear/12),year*12)-1);
        System.out.println("repayMoney:"+repayMoney);
    }

    private static void method01() {
        System.out.println(""+new BigDecimal("1.11").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.2").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.3").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.4").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.5").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.6").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.9").setScale(0,BigDecimal.ROUND_UP));
    }
}
