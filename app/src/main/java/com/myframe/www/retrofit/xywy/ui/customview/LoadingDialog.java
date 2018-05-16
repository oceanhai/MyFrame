package com.myframe.www.retrofit.xywy.ui.customview;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.myframe.www.R;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/16 10:15
 *
 * 描述：
 */
public class LoadingDialog extends Dialog {


    public ImageView loadintImage_iv;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_dialog_loading);
        getWindow().getAttributes().alpha = 0.5f;
        getWindow().getAttributes().gravity = Gravity.CENTER;
        loadintImage_iv = (ImageView) findViewById(R.id.dialong_laoding_loadingimage_iv);
        loadintImage_iv.setBackgroundResource(R.mipmap.icon_lodingquanquan);
        setCancelable(true);
    }

    @Override
    public void show() {
        super.show();
        ObjectAnimator animator = ObjectAnimator.ofFloat(loadintImage_iv, "rotation", 0f, 360f);
        animator.setDuration(3000);
        animator.setRepeatCount(-1);
        LinearInterpolator lin = new LinearInterpolator();
        animator.setInterpolator(lin);
        animator.start();
    }
}