package com.myframe.www.retrofit.xywy;

import com.myframe.www.retrofit.xywy.model.LoginEntity;

import okhttp3.ResponseBody;
import retrofit2.Callback;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface IServiceProvider {

    /**
     * 登录
     * @param name
     * @param password
     * @param callback
     */
    void login(String name, String password, Callback<LoginEntity> callback);

    /**
     * 短信验证码
     * @param phone
     * @param flag
     * @param callback
     */
    void getCode(String phone, String flag, Callback<ResponseBody> callback);

}
