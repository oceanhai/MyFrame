package com.myframe.www.retrofit.xywy.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import com.myframe.www.R;
import com.myframe.www.app.MyApplication;
import com.myframe.www.retrofit.xywy.interfaces.IBaseViewInterFace;
import com.myframe.www.retrofit.xywy.ui.customview.LoadingDialog;
import com.myframe.www.utils.ToastUtils;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/16 10:12
 *
 * 描述：
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseViewInterFace{


    public LoadingDialog mLoadingDialog;// 等待的窗口
    public static String TAG="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG= getClass().getSimpleName();

        /**
         * 在应用的入口activity加入以下代码，解决首次安装应用，点击应用图标打开应用，
         * 点击home健回到桌面，再次点击应用图标，进入应用时多次初始化SplashActivity的问题
         */
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }

        mLoadingDialog=new LoadingDialog(this);

        setLayoutId( );
        initData();
        initView();
        getDate();
    }

    protected abstract void setLayoutId( );
    protected abstract void initData();
    protected abstract void initView();
    protected abstract void getDate();

    /**
     * 没有网络的提示词
     */
    @Override
    public void showNoNetText() {
        ToastUtils.showShort(MyApplication.getContext(),R.string.onNoInterNet);
    }



     //服务器json异常
     @Override
    public void onJSONException() {
        ToastUtils.showShort(MyApplication.getContext(),R.string.serviceError);
    }

    //服务器无响应
    @Override
    public void onServerError() {
        ToastUtils.showShort(MyApplication.getContext(),R.string.serviceError);
    }

    @Override
    public  void showLoading(){
        if(mLoadingDialog!=null&& !mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    @Override
    public  void dimssLoading(){
        if(mLoadingDialog!=null &&  mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public Dialog getLoadingDialog() {
        if(mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this);
        }
        return mLoadingDialog;
    }

    @Override
    protected void onDestroy() {
        dimssLoading();
        mLoadingDialog=null;
        super.onDestroy();
    }

    /**
     * 关闭键盘
     */
    public void closeBoard( ) {
        try {
            InputMethodManager imm = (InputMethodManager)  getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm.isActive())  //一直是true
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e){

        }
    }
}
