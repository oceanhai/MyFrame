package com.xywy.okhttprequest.network;

import com.xywy.okhttprequest.https.HttpsUtils;
import com.xywy.okhttprequest.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/12/3.
 */
public class OkhttpManager {
    private static OkhttpManager instance ;
    private static OkHttpClient mOkHttpClient;

    private OkhttpManager(){
        init();
    }

    public static void init(){
        //TODO 包下不下来先注掉
//        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

//        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor(""))//TAG = "OkHttpUtils"
//                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
    }

    public static synchronized OkhttpManager getInstance(){
        if(instance != null){
            instance = new OkhttpManager();
        }
        return instance;
    }
}
