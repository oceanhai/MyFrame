package com.myframe.www.request;


import com.xywy.component.datarequest.network.RequestManager;
import com.xywy.component.datarequest.neworkWrapper.BaseData;
import com.xywy.component.datarequest.neworkWrapper.IDataResponse;

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

    /**
     * 784 . 手机验证码相关功能接口 v 1.2
     *
     * @param phone 手机号
     */
    public static void getCode(String phone, IDataResponse iHttpResponse, Object flag) {
        ApiParams getApiParams = new ApiParams().with(Constants.version_value2)
                .with(Constants.api_key,
                        Constants.api_code_value);
        ApiParams postApiParams = new ApiParams().with(Constants.project_key,
                Constants.project_value)
                .with(Constants.act_key,
                        Constants.act_value)
                .with(Constants.phone_key, phone)
                .with(Constants.code_key, "");
        getApiParams.with(Constants.SIGN,
                DataRequestTool.getSig(getApiParams, postApiParams,
                        RequestKey.basekey));
        DataRequestTool.post(REQUEST_URL_CLUB, Namespace.CODE, getApiParams, postApiParams, iHttpResponse, BaseData
                .class, flag, false);
    }
}
