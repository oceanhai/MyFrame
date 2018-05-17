package com.myframe.www.retrofit.xywy.network;

import com.myframe.www.retrofit.xywy.utils.SPUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/16 16:54
 *
 * 描述：创建retrofit
 */
public final class RetrofitService {

    private RetrofitService(){}

    public static <T> T createCallRequest(String endPoint, Class<T> serviceClass){
        return createCallRequest(endPoint,serviceClass,30);
    }

    public static <T> T createCallRequest(String endPoint, Class<T> serviceClass, int readTimeout){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttpClient(readTimeout))
                .build();
        return retrofit.create(serviceClass);
    }

    /**
     * 设置自己的请求头 User-Agent
     * @param readTimeout
     * @return
     */
    private static OkHttpClient buildOkHttpClient(int readTimeout){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(readTimeout,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        request = request.newBuilder()
                                .removeHeader((String) SPUtils.get("userAgent",""))
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
    }
}

