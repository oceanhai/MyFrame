package com.example.javabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuhai on 2018/01/08 10:57.
 * 描述：
 */

public class PatternUtils {

    /**
     * 判断是否是会员卡
     * 99、00开头的6位、11位数字
     * @param str
     * @return
     */
    public static boolean isMemberCard(String str){
        Pattern pattern = Pattern.compile("^(00|99)(\\d{4}|\\d{9})$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
