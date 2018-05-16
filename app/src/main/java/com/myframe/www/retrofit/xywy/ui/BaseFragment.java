package com.myframe.www.retrofit.xywy.ui;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.myframe.www.R;
import com.myframe.www.app.MyApplication;
import com.myframe.www.retrofit.xywy.interfaces.IBaseViewInterFace;
import com.myframe.www.retrofit.xywy.ui.customview.LoadingDialog;
import com.myframe.www.utils.ToastUtils;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/16 10:08
 *
 * 描述：
 */

public abstract class BaseFragment extends Fragment implements IBaseViewInterFace{


    public static String TAG="";
    public LoadingDialog mLoading;//等待的loading

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG= getClass().getSimpleName();
        View v = getViewByLayoutName(inflater, container);
        mLoading = new LoadingDialog(getActivity());
        initView(v);
        return v;
    }


    /**
     * 初始化view
     *
     * @param v
     * @return
     */
    protected abstract void initView(View v);


    /**
     * layout  根据layoutName 获取view
     *
     * @param inflater
     * @param container
     * @param
     * @return
     */
    protected View getViewByLayoutName(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(setLayoutName(), container, false);
        return view;
    }

    /**
     * layout  根据layoutName 获取view
     *
     * @return layoutName
     */
    protected abstract int setLayoutName();


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
    public void showLoading() {
        if (mLoading != null && !mLoading.isShowing()) {
            try {
                mLoading.show();
            } catch (Exception e) {
            }

        }
    }

    @Override
    public void dimssLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            try {
                mLoading.dismiss();
            } catch (Exception e) {

            }

        }
    }

    @Override
    public Dialog getLoadingDialog() {
        if(mLoading == null){
            mLoading = new LoadingDialog(getActivity());
        }
        return mLoading;
    }

    @Override
    public void onDestroy() {
        dimssLoading();
        mLoading = null;
        super.onDestroy();
    }

    /**
     * 关闭键盘
     */
    public void closeBoard() {
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive())  //一直是true
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }
}
