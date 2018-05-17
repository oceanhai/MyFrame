package com.myframe.www.retrofit.xywy.network;

import com.myframe.www.retrofit.xywy.ConfigUrl;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/16 17:03
 *
 * 描述：创建网络接口
 */
public abstract class APIBaseService {

    private static final String TAG = "APIService";

    protected static <T> T createService(Class<T> apiClass){
        return RetrofitService.createCallRequest(ConfigUrl.REQUEST_URL, apiClass);
    }

    protected static <T> T createService(String endPoint, Class<T> apiClass){
        return RetrofitService.createCallRequest(endPoint, apiClass);
    }

    protected static <T> T createService(String endPoint, Class<T> apiClass, int readTimeout){
        return RetrofitService.createCallRequest(endPoint, apiClass, readTimeout);
    }

}
