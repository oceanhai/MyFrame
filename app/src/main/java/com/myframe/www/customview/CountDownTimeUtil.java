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
    public static final String TIME = "Time";//时分秒
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
        }else if(TIME.equals(mFlag)){
            mTextView.setTextColor(Color.rgb(187, 187, 187));
            mTextView.setText(secToTime((int) (millisUntilFinished/1000)));
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
        }else if(TIME.equals(mFlag)){
            mTextView.setTextColor(Color.rgb(50, 180, 194));
            mTextView.setText("活动结束");
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
        }else if(TIME.equals(mFlag)){
            mTextView.setTextColor(Color.rgb(50, 180, 194));
            mTextView.setText("活动结束");
        }else{
            mTextView.setTextColor(Color.rgb(50, 180, 194));
            mTextView.setText("重新倒计时");
        }
    }


    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


}
