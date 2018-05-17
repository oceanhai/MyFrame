package com.myframe.www.retrofit.xywy.network;


import com.myframe.www.request.Constants;
import com.myframe.www.retrofit.xywy.utils.SPUtils;

import java.util.HashMap;

public class ApiParams extends HashMap<String, String> {

    public ApiParams with(String key, String value) {
        put(key, value);
        return this;
    }


    public ApiParams with(String version) {
        put(Constants.version_key, version);
        put(Constants.source_key, Constants.source_value);
        put(Constants.os_key, Constants.os_value);
        put(Constants.pro_key, Constants.pro_value);
        put(Constants.token_key, (String) SPUtils.get("token",""));
        return this;
    }


    public ApiParams otherWith() {
        put(Constants.version_key,"4.2.1");
        put(Constants.source_key, Constants.source_other_value);
        put(Constants.os_key, Constants.os_value);
        put(Constants.pro_key, Constants.pro_value);
        return this;
    }


    /**
     * Version 手动传入
     */
    public ApiParams withNoVersion() {
        put(Constants.source_key, Constants.source_value);
        put(Constants.os_key, Constants.os_value);
        put(Constants.pro_key, Constants.pro_value);
        put(Constants.token_key, (String) SPUtils.get("token",""));
        return this;
    }

    /**
     * 注册，登录，第三方登录，修改密码  不传token
     * @return
     */
    public ApiParams withNoToken(String version) {
        put(Constants.version_key, version);
        put(Constants.source_key, Constants.source_value);
        put(Constants.os_key, Constants.os_value);
        put(Constants.pro_key, Constants.pro_value);
        return this;
    }
}
