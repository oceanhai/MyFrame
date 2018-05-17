package com.myframe.www.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.myframe.www.BuildConfig;
import com.myframe.www.MainActivity;
import com.myframe.www.retrofit.xywy.utils.DeviceUtil;
import com.myframe.www.retrofit.xywy.utils.SPUtils;
import com.myframe.www.testgreendao.GreenDaoManager;
import com.myframe.www.utils.MyUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;
import com.xywy.component.datarequest.network.RequestManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import step.StepManager;
import www.wuhai.common.utils.L;

/**
 * Created by wuhai on 2016/3/2.
 */
public class MyApplication extends Application{

    private final static String TAG = "MyApplication";

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        //一个应用里，不同进程都会调用一次Application
        String processName = MyUtils.getProcessName(this, android.os.Process.myPid());
        L.v(TAG,"processName:"+processName);

        mContext = this;

        init();
    }

    private void init() {

        //bugly
        buglyInit();

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
        StepManager.init(this, "all");//all 以手机记录步数

        /**
         * Uncaught异常 捕获
         */
//        CrashHandler.getInstance().init(this);

        //greenDao全局配置,只希望有一个数据库操作对象
        GreenDaoManager.getInstance();

        //Fresco
        Fresco.initialize(this);

        //sp
        //设置用户代理
        if(!SPUtils.contains("userAgent")){
            SPUtils.put("userAgent", DeviceUtil.getUserAgentHeader(this));
        }
    }

    private void buglyInit() {
//        //单进程时候使用 此
//        CrashReport.initCrashReport(getApplicationContext(), "1da0776e65", BuildConfig.BUGLY_DEBUG);

        /**
         * 多进程的时候 使用主进程上报问题
         */
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
//        CrashReport.initCrashReport(context, "1da0776e65", BuildConfig.BUGLY_DEBUG, strategy);
        //+升级sdk后，上面注释掉
        Bugly.init(getApplicationContext(), "1da0776e65",  BuildConfig.BUGLY_DEBUG, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);


        /**
         * 设置 更多详情设置请看文档
         */
        //设置点击过确认的弹窗在App下次启动自动检查更新时会再次显示
        Beta.canShowUpgradeActs.add(MainActivity.class);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static Context getContext() {
        return mContext;
    }
}
