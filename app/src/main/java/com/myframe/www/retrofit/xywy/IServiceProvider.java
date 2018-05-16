package com.myframe.www.retrofit.xywy;

import com.myframe.www.retrofit.xywy.model.LoginEntity;

import retrofit2.Callback;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface IServiceProvider {

    void login(String name, String password, Callback<LoginEntity> callback);
}
