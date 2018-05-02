package com.myframe.www.customview;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by wuhai on 2018/5/2 0002 15:54.
 * 描述：验证码倒计时工具类  /跳转
 */
public class CountDownTimeUtil extends CountDownTimer {
    public static final String DYNAMIC_LOGIN = "dynamic_login";//动态密码登录用
    public static final String JUMP = "Jump";//跳转
    //被点击的计时按钮
    private TextView mTextView;
    private String mFlag;

    public CountDownTimeUtil(long millisInFuture, long countDownInterval, TextView mTextView, String flag) {
        super(millisInFuture, countDownInterval);
        this.mTextView = mTextView;
        this.mFlag = flag;
    }

    /**
     * 计时过程
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setEnabled(false);
        if(DYNAMIC_LOGIN.equals(mFlag)){
            mTextView.setBackgroundColor(Color.parseColor("#c5c5c5"));
            mTextView.setText(millisUntilFinished / 1000 + "'后点击重发");
        }else if(JUMP.equals(mFlag)){
            mTextView.setTextColor(Color.rgb(187, 187, 187));
            mTextView.setText("跳转（"+millisUntilFinished / 1000 + "）");
        }else{
            mTextView.setTextColor(Color.rgb(187, 187, 187));
            mTextView.setText(millisUntilFinished / 1000 + "秒");
        }
    }

    /**
     * 计时完成
     */
    @Override
    public void onFinish() {
        mTextView.setEnabled(true);
        if(DYNAMIC_LOGIN.equals(mFlag)){
            mTextView.setBackgroundColor(Color.parseColor("#6fd8ff"));
            mTextView.setText("重新发送");
        }else if(JUMP.equals(mFlag)){
            mTextView.setTextColor(Color.rgb(50, 180, 194));
            mTextView.setText("跳转");
        }else{
            mTextView.setTextColor(Color.rgb(50, 180, 194));
            mTextView.setText("重新倒计时");
        }
    }

    /**
     * 失败后  重置
     */
    public void reSend(){
        mTextView.setEnabled(true);
        if(DYNAMIC_LOGIN.equals(mFlag)){
            mTextView.setBackgroundColor(Color.parseColor("#6fd8ff"));
            mTextView.setText("重新发送");
        }else if(JUMP.equals(mFlag)){
            mTextView.setTextColor(Color.rgb(50, 180, 194));
            mTextView.setText("跳转");
        }else{
            mTextView.setTextColor(Color.rgb(50, 180, 194));
            mTextView.setText("重新倒计时");
        }
    }


}
