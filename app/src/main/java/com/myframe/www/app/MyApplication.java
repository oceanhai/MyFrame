package com.myframe.www.app;

import android.app.Application;

import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;
import com.xywy.component.datarequest.network.RequestManager;

/**
 * Created by wuhai on 2016/3/2.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

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
