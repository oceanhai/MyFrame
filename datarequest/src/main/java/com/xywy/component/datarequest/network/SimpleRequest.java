package com.xywy.component.datarequest.network;

import android.graphics.Bitmap;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 简单网络请求，是所有网络请求的基类
 * 传递url，返回String
 */
public class SimpleRequest extends Request<String> {

    private static final int SIMPLE_TIMEOUT_TIMER = 10000;
    protected Response.Listener<String> mListener;
    private boolean mCache = false;

    public SimpleRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;

        setRetryPolicy(new DefaultRetryPolicy(
                SIMPLE_TIMEOUT_TIMER,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void setCacheEntity(boolean cache) {
        mCache = cache;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        Cache.Entry entry;
        if(mCache) {
            entry = makeCacheHeaders(response);
        } else {
            entry = HttpHeaderParser.parseCacheHeaders(response);
        }
        return Response.success(parsed, entry);
    }

    @Override
    protected void deliverResponse(String response) {
        if(mListener!=null) {
            mListener.onResponse(response);
        }
    }

    /**
     * 给网络请求配置参数 用于post包体
     * @param params 在post包体中的数据
     */
    public void setParams(Map<String, String> params) {

    }

    /**
     * 给网络请求配置文件
     */
    public void setFile(String name, File file) {

    }

    /**
     * 给网络请求配置图片
     */
    public void setBitmap(String name, Bitmap bitmap) {

    }

    public static Cache.Entry makeCacheHeaders(NetworkResponse response) {
        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.ttl = Long.MAX_VALUE;
        entry.responseHeaders = response.headers;

        return entry;
    }
}
