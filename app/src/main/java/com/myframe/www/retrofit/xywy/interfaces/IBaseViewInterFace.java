package com.myframe.www.retrofit.xywy.interfaces;

import android.app.Dialog;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/16 10:12
 *
 * 描述：view
 */
public interface IBaseViewInterFace {

    /**
     * 展现等待的loading
     *
     */
    Dialog getLoadingDialog();

    /**
     * 没有 网络的文字提示
     *
     */
    void showNoNetText();

    /**
     * 服务器 异常或者超时
     *
     */
    void onServerError();

    /**
     * Json 异常
     *
     */
    void onJSONException();

    /**
     * loading
     */
    void showLoading();

    /**
     * dimssLoading
     */
    void dimssLoading();
}
