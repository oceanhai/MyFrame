package com.myframe.www.testgreendao;

import com.myframe.www.app.MyApplication;

/**
 * Created by wuhai on 2017/2/13.
 */
public class GreenDaoManager {

    private static GreenDaoManager mInstance; //单例

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager(){
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(MyApplication.getContext(), "greendao.db", null);//此处为自己需要处理的表
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {//保证异步处理安全操作

                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        return mDaoSession;
    }
    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
