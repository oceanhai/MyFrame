package com.xywy.component.datarequest.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

/**
 * 简单的post网络请求
 */
public class PostRequest extends SimpleRequest {

    private Map<String, String> mParams = Collections.EMPTY_MAP;

    public PostRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

    @Override
    protected void deliverResponse(String response) {
        if(mListener!=null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    /**
     * 给网络请求配置参数 用于post包体
     * @param params 在post包体中的数据
     */
    public void setParams(Map<String, String> params) {
        if(params!=null && params.size()>0) {
            mParams = params;
        }
    }
}
