package com.example.lctusb;

import java.math.BigDecimal;

/**
 * Created by wuhai on 2017/07/19 16:11.
 * 描述：
 */

public class TestGetInt {
    public static void main(String[] args){
//        double i=2, j=2.1, k=2.5, m=2.9;
//        System.out.println("舍掉小数取整:Math.floor(2)=" + (int)Math.floor(i));
//        System.out.println("舍掉小数取整:Math.floor(2.1)=" + (int)Math.floor(j));
//        System.out.println("舍掉小数取整:Math.floor(2.5)=" + (int)Math.floor(k));
//        System.out.println("舍掉小数取整:Math.floor(2.9)=" + (int)Math.floor(m));

   /* 这段被注释的代码不能正确的实现四舍五入取整
   System.out.println("四舍五入取整:Math.rint(2)=" + (int)Math.rint(i));
   System.out.println("四舍五入取整:Math.rint(2.1)=" + (int)Math.rint(j));
   System.out.println("四舍五入取整:Math.rint(2.5)=" + (int)Math.rint(k));
   System.out.println("四舍五入取整:Math.rint(2.9)=" + (int)Math.rint(m));

   System.out.println("四舍五入取整:(2)=" + new DecimalFormat("0").format(i));
   System.out.println("四舍五入取整:(2.1)=" + new DecimalFormat("0").format(i));
   System.out.println("四舍五入取整:(2.5)=" + new DecimalFormat("0").format(i));
   System.out.println("四舍五入取整:(2.9)=" + new DecimalFormat("0").format(i));
   */

//        System.out.println("四舍五入取整:(2)=" + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP));
//        System.out.println("四舍五入取整:(2.1)=" + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP));
//        System.out.println("四舍五入取整:(2.5)=" + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP));
//        System.out.println("四舍五入取整:(2.9)=" + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));
//
//        System.out.println("凑整:Math.ceil(2)=" + (int)Math.ceil(i));
//        System.out.println("凑整:Math.ceil(2.1)=" + (int)Math.ceil(j));
//        System.out.println("凑整:Math.ceil(2.5)=" + (int)Math.ceil(k));
//        System.out.println("凑整:Math.ceil(2.9)=" + (int)Math.ceil(m));
//
//        System.out.println("舍掉小数取整:Math.floor(-2)=" + (int)Math.floor(-i));
//        System.out.println("舍掉小数取整:Math.floor(-2.1)=" + (int)Math.floor(-j));
//        System.out.println("舍掉小数取整:Math.floor(-2.5)=" + (int)Math.floor(-k));
//        System.out.println("舍掉小数取整:Math.floor(-2.9)=" + (int)Math.floor(-m));
//
//        System.out.println("四舍五入取整:(-2)=" + new BigDecimal("-2").setScale(0, BigDecimal.ROUND_HALF_UP));
//        System.out.println("四舍五入取整:(-2.1)=" + new BigDecimal("-2.1").setScale(0, BigDecimal.ROUND_HALF_UP));
//        System.out.println("四舍五入取整:(-2.5)=" + new BigDecimal("-2.5").setScale(0, BigDecimal.ROUND_HALF_UP));
//        System.out.println("四舍五入取整:(-2.9)=" + new BigDecimal("-2.9").setScale(0, BigDecimal.ROUND_HALF_UP));
//
//        System.out.println("凑整:Math.ceil(-2)=" + (int)Math.ceil(-i));
//        System.out.println("凑整:Math.ceil(-2.1)=" + (int)Math.ceil(-j));
//        System.out.println("凑整:Math.ceil(-2.5)=" + (int)Math.ceil(-k));
//        System.out.println("凑整:Math.ceil(-2.9)=" + (int)Math.ceil(-m));

        double a1 = 1.346;
        double a2 = 0.346;
        double a3 = 1.500;
        double a4 = 1.510;
        double a5 = 1.499;
        BigDecimal result1 = new BigDecimal(a1).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal result2 = new BigDecimal(a2).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal result3 = new BigDecimal(a3).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal result4 = new BigDecimal(a4).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal result5 = new BigDecimal(a5).setScale(1, BigDecimal.ROUND_HALF_UP);
        double z1 = result1.doubleValue();
        double z2 = result2.doubleValue();
        double z3 = result3.doubleValue();
        double z4 = result4.doubleValue();
        double z5 = result5.doubleValue();
        System.out.println("z1:"+z1);
        System.out.println("z2:"+z2);
        System.out.println("z3:"+z3);
        System.out.println("z4:"+z4);
        System.out.println("z5:"+z5);
    }
}
