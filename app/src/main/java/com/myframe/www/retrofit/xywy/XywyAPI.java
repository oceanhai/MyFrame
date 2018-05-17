package com.myframe.www.retrofit.xywy;

import com.myframe.www.retrofit.xywy.model.LoginEntity;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface XywyAPI {

    @GET("xywyapp/userlogin/index?")
    Call<LoginEntity> login(@QueryMap Map<String,String> map);

    @POST("user/userSmsPhoneCode/index?")
    @FormUrlEncoded
    Call<ResponseBody> getCode(@QueryMap Map<String,String> getApiParams,
                               @FieldMap Map<String,String> postApiParams);

    @POST("common/uploadImg/index?")
    @Multipart
    Call<ResponseBody> uploadImg(@QueryMap Map<String,String> getApiParams,
                                 @PartMap Map<String, RequestBody> params);
}
