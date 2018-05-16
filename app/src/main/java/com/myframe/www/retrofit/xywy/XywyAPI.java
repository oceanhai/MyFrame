package com.myframe.www.retrofit.xywy;

import com.myframe.www.retrofit.xywy.model.LoginEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface XywyAPI {

    @GET("xywyapp/userlogin/index?")
    Call<LoginEntity> login(@QueryMap Map<String,String> map);

}
