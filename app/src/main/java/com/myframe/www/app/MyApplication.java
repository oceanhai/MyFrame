package com.myframe.www.app;

import android.app.Application;
import android.os.*;

import com.myframe.www.utils.MyUtils;
import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;
import com.xywy.component.datarequest.network.RequestManager;

import www.wuhai.common.utils.L;

/**
 * Created by wuhai on 2016/3/2.
 */
public class MyApplication extends Application{

    private final static String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        //一个应用里，不同进程都会调用一次Application
        String processName = MyUtils.getProcessName(this, android.os.Process.myPid());
        L.v(TAG,"processName:"+processName);

        init();
    }

    private void init() {

        /**
         * volley 初始化
         */
        RequestManager.init(this);

        /**
         * 图片加载
         */
        ImageLoaderUtils.getInstance().init(this);

    }
}
