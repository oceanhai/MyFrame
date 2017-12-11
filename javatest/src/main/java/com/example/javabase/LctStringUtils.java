package com.example.javabase;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuhai on 2017/02/10 16:44.
 * 描述:
 */

public class LctStringUtils {

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
//    public static String getDecimalFormat(String format, double num){
//        DecimalFormat df = null;
//        if (TextUtils.isEmpty(format)){
//            df = new DecimalFormat("######0.00");
//        }else{
//            df = new DecimalFormat(format);
//        }
//        df.setRoundingMode(RoundingMode.HALF_UP);
//        String result = df.format(num);
//        LogUtil.e("StringUtils:"+"入参 format="+format+",num="+num+",结果 result="+result);
//        return result;
//    }


    /**
     * 获取uuid
     *
     */
    public static String getUUid() {

        UUID uuid=UUID.randomUUID();
        return   uuid.toString().replace("-","").toLowerCase();
    }



    /**
     *
     * @return
     */
//    public static String get17RandomString( ){
//
//        String dateAllMsecString = DateUtils.getDateAllMsecString().replace("-","").replace(" ","").replaceAll(":","");
//        return dateAllMsecString;
//    }

    /**
     * 获得一个随机 id
     * 订单id、退货单id、订货单id
     * @return
     */
//    public static String get17RandomNo(){
//        return DateUtils.getDateString("yyMMddHHmm", System.currentTimeMillis())+ LctStringUtils.getFixLenthString(7);
//    }

    /**
     * 判断 str 是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric1(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断 str 是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric2(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * x位，前面不足自动补零
     * @param num       位数
     * @param str       入参
     * @return
     */
    public static String addZeroBefore(int num, String str){
        return String.format("%0"+num+"d",Integer.valueOf(str));
    }

    /**
     * x位，前面不足自动补零
     * @param num       位数
     * @param str       入参
     * @return
     */
    public static String addZeroBefore2(int num, int str){
        return String.format("%0"+num+"d",str);
    }
}
