package com.myframe.www.request;


import java.util.HashMap;

public class ApiParams extends HashMap<String, String> {
    private static final long serialVersionUID = 8112047472727256876L;


    public ApiParams with(String key, String value) {
        put(key, value);
        return this;
    }


    public ApiParams with(String version) {
        put(Constants.version_key, version);
        put(Constants.source_key, Constants.source_value);
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
        return this;
    }
}
