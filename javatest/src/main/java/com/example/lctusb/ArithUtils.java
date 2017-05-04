package com.example.lctusb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by wuhai on 2017/02/13 14:48.
 * 描述：算术
 */

public class ArithUtils {
    /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
     public static double sub(double value1,double value2){
         BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
         BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
         return b1.subtract(b2).doubleValue();
     }

     /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
     public static double mul(double value1,double value2){
         BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
         BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
         return b1.multiply(b2).doubleValue();
      }

     /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
     public static double div(double value1,double value2,int scale) throws IllegalAccessException{
         //如果精确范围小于0，抛出异常信息
         if(scale<0){
             throw new IllegalAccessException("精确度不能小于0");
         }
         BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
         BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
         return b1.divide(b2, scale).doubleValue();
     }


    //--------------------------------------------------------

    /**
     * 保留两位小数，四舍五入的一个老土的方法
     * @param d
     * @return
     */
    public static double formatDouble1(double d) {
        return (double)Math.round(d*100)/100;
    }


    /**
     * The BigDecimal class provides operations for arithmetic, scale manipulation, rounding, comparison, hashing, and format conversion.
     * @param d
     * @return
     */
    public static double formatDouble2(double d) {
        // 旧方法，已经不再推荐使用
//        BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);

        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);

        return bg.doubleValue();
    }

    /**
     * NumberFormat is the abstract base class for all number formats.
     * This class provides the interface for formatting and parsing numbers.
     * @param d
     * @return
     */
//    public static String formatDouble3(double d) {
//        NumberFormat nf = NumberFormat.getNumberInstance();
//
//        // 保留两位小数
//        nf.setMaximumFractionDigits(2);
//
//        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
//        nf.setRoundingMode(RoundingMode.UP);
//
//        return nf.format(d);
//    }

    /**
     * 这个方法挺简单的。
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers.
     * @param d
     * @return
     */
    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }


    /**
     * 如果只是用于程序中的格式化数值然后输出，那么这个方法还是挺方便的。
     * 应该是这样使用：System.out.println(String.format("%.2f", d));
     * @param d
     * @return
     */
    public static String formatDouble5(double d) {
        return String.format("%.2f", d);
    }
}
