package com.example.lctusb;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

/**
 * Created by wuhai on 2017/02/10 16:44.
 * 描述：TODO 将来与lct的maven基础库合并
 */

public class StringUtils {

    /**
    * 返回长度为【strLength】的随机数
    * 最大16位
    */
    public static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 2);
    }

    /**
     * double 类型 截取小数点后位数
     * @param format
     * @param num
     * @return
     */
    public static String getDecimalFormat(String format, double num){
        DecimalFormat df = null;
        if (format == null || format.length()==0){
            df = new DecimalFormat("######0.00");
        }else{
            df = new DecimalFormat(format);
        }
        return df.format(num);
    }


    /**
     * 获取uuid
     *
     */
    public static String getUUid() {

        UUID uuid=UUID.randomUUID();
        return   uuid.toString().replace("-","").toLowerCase();
    }


}
