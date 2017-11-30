package com.myframe.www.lctdahua;

import java.io.UnsupportedEncodingException;

/**
 * Created by wuhai on 2017/11/30 18:09.
 * 描述：
 */

public class ChineseAreaCode {

    public static String toAreaCode(String word){
        byte[] bs = null;
        try {
            bs = word.getBytes("GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String areaCode="";
        for(byte b:bs){
            int code=Integer.parseInt(Integer.toHexString(b & 0xff),16);
            areaCode += (code-0x80-0x20);       /*transfer the machine code to area code*/
        }
        return areaCode;
    }

}
