package com.myframe.www.app;

import android.app.Application;

import com.igexin.sdk.PushManager;
import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;

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
         * 图片加载
         */
        ImageLoaderUtils.getInstance().init(this);

    }
}
