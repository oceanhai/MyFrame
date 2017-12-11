package com.example.javabase;

import java.io.UnsupportedEncodingException;

/**
 * Created by wuhai on 2017/11/30 18:09.
 * 描述：区位码
 */

public class ChineseAreaCode {


    /**
     * 正确的 区位码
     * @param word
     * @return
     */
    public static String toAreaCode(String word){
        byte[] bs = null;
        try {
            bs = word.getBytes("GB2312");//汉字占两字节
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String areaCode="";
        for(byte b:bs){
            int code=Integer.parseInt(Integer.toHexString(b & 0xff),16);
            /**
             * str.getBytes("GB2312");
             取的是机内码
             减 0x80 国际码
             减 0x20 区位码
             ※注意区位码是4位，所以字节如果是个数的话需要进行前面补0
             */
            areaCode += LctStringUtils.addZeroBefore2(2,code-0x80-0x20);       /*transfer the machine code to area code*/
        }
        return areaCode;
    }


    //------------------

    /**
     * 区位码
     * @param word
     * @return
     */
    public static String toAreaCode2(String word){
        byte[] bs = null;
        try {
            bs = word.getBytes("GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String s = "";
        for (int i = 0; i < bs.length; i++) {
            int a = Integer.parseInt(bytes2HexString(bs), 16);
            s += (a - 0x80 - 0x20) + "";
        }
        return s;
    }
    public static String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }


    public static String getQuwei(String str){
        byte[] b = null;
        try {
            b = str.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int[] quwei = new int[b.length / 2];
        for(int i = 0, k = b.length / 2; i < k; i++) {
            quwei[i] = (((b[2 * i] - 0xA0) & 0xff) * 100) + ((b[2 * i + 1] - 0xA0) & 0xff);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int x=0;x<quwei.length;x++){
            stringBuilder.append(quwei[x]);
        }
        return stringBuilder.toString();
    }
}
