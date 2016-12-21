package com.myframe.www.app;

import android.app.Application;

import com.myframe.www.utils.MyUtils;
import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;
import com.xywy.component.datarequest.network.RequestManager;

import step.StepManager;
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

        /**
         * log 初始化
         */
        L.init(this);

        /**
         * 计步器 aar 初始化
         */
        StepManager.init(this,"all");//all 以手机记录步数

        /**
         * Uncaught异常 捕获
         */
        CrashHandler.getInstance().init(this);
    }
}
