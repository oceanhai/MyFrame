package com.myframe.www.retrofit;


import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wuhai on 2018/5/6.
 */

public interface UserService {

    @GET("/user")
    public void getUser(@Query("userId")String userId, Callback<User> callback);
}
