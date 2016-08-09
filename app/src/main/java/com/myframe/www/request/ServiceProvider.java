package com.myframe.www.request;


import com.xywy.component.datarequest.network.RequestManager;
import com.xywy.component.datarequest.neworkWrapper.BaseData;
import com.xywy.component.datarequest.neworkWrapper.IDataResponse;
import com.xywy.component.datarequest.tool.MD5;

import java.io.File;
import java.util.Map;

/**
 * Created by wuhai on 2016/1/4.
 * 网络请求  所有的方法
 */
public class ServiceProvider {
    public static String SIGNKEY = "RT1jUlrDFpF06M6y";

    public static boolean mIsDevelopEnv = true;
    public static String REQUEST_URL_ZONGHE = "";
    public static String REQUEST_URL_CLUB = "";
    //查询和名医
    public static String REQUEST_URL_ZONGHE_ONLINE = "http://api.app.xywy.com";
    public static String REQUEST_URL_ZONGHE_DEVELOP
            = "http://test.api.app.xywy.com";
    //中间层 封装的都是Club的接口
    public static String REQUEST_URL_CLUB_ONLINE
            = "http://api.wws.xywy.com/api.php";
    public static String REQUEST_URL_CLUB_DEVELOP
            = "http://test.api.wws.xywy.com/api.php";


    static {
        if (mIsDevelopEnv) {
            REQUEST_URL_ZONGHE = REQUEST_URL_ZONGHE_DEVELOP;
            REQUEST_URL_CLUB = REQUEST_URL_CLUB_DEVELOP;
        }
        else {
            REQUEST_URL_ZONGHE = REQUEST_URL_ZONGHE_ONLINE;
            REQUEST_URL_CLUB = REQUEST_URL_CLUB_ONLINE;
        }
    }


    /**
     * 取消请求
     */
    public static void cancel(Object flag) {
        RequestManager.cancelAll(flag);
    }
}
