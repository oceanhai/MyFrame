package com.myframe.www.utils;


/**
 * Created by aidonglei on 2015/12/10.
 */
public class VerificationPhoneUtil {

    public static boolean isMobile(String mobiles) {
        String telRegex = "(13[0-9]|14[57]|15[012356789]|17[0123456789]|18[0123456789])\\d{8}";

        //Pattern p = Pattern
        //        .compile("^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$");
        //Matcher m = p.matcher(mobiles);
        return !mobiles.matches(telRegex);
    }
}
