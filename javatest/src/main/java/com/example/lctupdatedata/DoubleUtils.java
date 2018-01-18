package com.example.lctupdatedata;

import java.text.DecimalFormat;

/**
 * Created by wuhai on 2017/10/17 14:35.
 * 描述：
 */

public class DoubleUtils {

    /**
     * double 类型 截取小数点后位数
     * @param format
     * @param num
     * @return
     */
    public static double getDoubleByDecimalFormat(String format, double num){
        DecimalFormat df = null;
        if (format == null || format.length()==0){
            df = new DecimalFormat("######0.00");
        }else{
            df = new DecimalFormat(format);
        }
        return Double.valueOf(df.format(num));
    }

}
