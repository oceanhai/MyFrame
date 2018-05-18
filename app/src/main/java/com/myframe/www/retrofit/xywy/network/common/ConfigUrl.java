package com.myframe.www.retrofit.xywy.network.common;

import com.myframe.www.BuildConfig;

/**
 * Created by wuhai on 2018/5/14.
 */

public class ConfigUrl {

    public static String REQUEST_URL = "";

    /**
     * retrofit必须满足
     * baseUrl must end in /
     */
    public static String REQUEST_URL_ZONGHE_DEVELOP
            = "http://test.api.app.xywy.com/";
    //中间层 封装的都是Club的接口
    public static String REQUEST_URL_CLUB_ONLINE
            = "http://api.wws.xywy.com/api.php/";

    static {
        if(BuildConfig.DEBUG){//走线上
            REQUEST_URL = REQUEST_URL_CLUB_ONLINE;
        }else{
            REQUEST_URL = REQUEST_URL_CLUB_ONLINE;
        }
    }
}
