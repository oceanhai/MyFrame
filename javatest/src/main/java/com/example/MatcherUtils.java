package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by wuhai on 2017/4/13 0013 11:46.
 * 描述：
 */
public class MatcherUtils {

    public static void main(String[] args){
        System.out.println(matchIdCardLastSix("123456"));
        System.out.println(matchIdCardLastSix("12345x"));
        System.out.println(matchIdCardLastSix("12345X"));
        System.out.println(matchIdCardLastSix("42345X"));
        System.out.println(matchIdCardLastSix("32345X"));
    }


    public static boolean strSplitR(String str) {
        if (str != null) {
            String[] strs = str.split("\\.");
            if (strs.length > 0 && "R".equals(strs[0])) {
                return true;
            } else {
                return false;

            }
        }
        return false;
    }

    public static boolean isUrl(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        String regEx = "[a-zA-z]+://[^\\s]*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.matches();
    }

    /**
     * Todo
     * @param str
     * @return
     */
    public static boolean matchIdCardLastSix(String str)  {
        // 只允许字母和数字
        String regEx = "^[0,1,2,3]\\d{4}[0,1,2,3,4,5,6,7,8,9,x,X]$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.matches();
    }
}
