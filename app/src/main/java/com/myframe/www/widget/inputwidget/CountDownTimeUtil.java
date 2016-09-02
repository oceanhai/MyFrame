package com.myframe.www.widget.inputwidget;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by aidonglei on 2015/12/10.
 * 验证码倒计时工具类
 */
public class CountDownTimeUtil extends CountDownTimer {
    //被点击的计时按钮
    private TextView mTextView;

    public CountDownTimeUtil(long millisInFuture, long countDownInterval, TextView mTextView) {
        super(millisInFuture, countDownInterval);
        this.mTextView = mTextView;
    }

    /**
     * 计时过程
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setEnabled(false);
        mTextView.setTextColor(Color.rgb(187, 187, 187));
        mTextView.setText(millisUntilFinished / 1000 + "秒");
    }

    /**
     * 计时完成
     */
    @Override
    public void onFinish() {
        mTextView.setText("重新发送");
        mTextView.setEnabled(true);
        mTextView.setTextColor(Color.rgb(50, 180, 194));
    }

    public void reSend(){
        mTextView.setText("重新发送");
        mTextView.setEnabled(true);
        mTextView.setTextColor(Color.rgb(50, 180, 194));
    }


}
