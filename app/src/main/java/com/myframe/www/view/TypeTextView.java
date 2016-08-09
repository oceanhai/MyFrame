package com.myframe.www.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 模拟打字机效果
 * 原理：通过一个定时器不断调用TextView的setText
 */
public class TypeTextView extends TextView {
    private Context mContext = null;

    private String mShowTextString = null;

    private Timer mTypeTimer = null;

    private OnTypeViewListener mOnTypeViewListener = null;

    private static final int TYPE_TIME_DELAY = 80;

    private int mTypeTimeDelay = TYPE_TIME_DELAY; // 打字间隔

    private int dividerNumber = 5;//前dividerNumber个字颜色

    private boolean isSpecialAction = true;//是否启动dividerNumber功能

    public TypeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTypeTextView(context);
    }

    public TypeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeTextView(context);
    }

    public TypeTextView(Context context) {
        super(context);
        initTypeTextView(context);
    }

    /**
     * 打印机的开始和结束 监听
     * @param onTypeViewListener
     */
    public void setOnTypeViewListener(OnTypeViewListener onTypeViewListener) {
        mOnTypeViewListener = onTypeViewListener;
    }

    public void start(final String textString) {
        start(textString, TYPE_TIME_DELAY);
    }

    public void start(final String textString, final int typeTimeDelay) {
        if (TextUtils.isEmpty(textString) || typeTimeDelay < 0) {
            return;
        }
        post(new Runnable() {
            @Override
            public void run() {
                mShowTextString = textString;
                mTypeTimeDelay = typeTimeDelay;
                setText("");
                startTypeTimer();
                if (null != mOnTypeViewListener) {
                    mOnTypeViewListener.onTypeStart();
                }
            }
        });
    }

    public void stop() {
        stopTypeTimer();
    }

    private void initTypeTextView(Context context) {
        mContext = context;
    }

    /**
     * start 定时器
     */
    private void startTypeTimer() {
        stopTypeTimer();
        if(mTypeTimer == null){
            mTypeTimer = new Timer();
        }
        mTypeTimer.schedule(new TypeTimerTask(), mTypeTimeDelay);
    }

    /**
     * stop 定时器
     */
    private void stopTypeTimer() {
        if (null != mTypeTimer) {
            mTypeTimer.cancel();
            mTypeTimer = null;
        }
    }

    class TypeTimerTask extends TimerTask {
        @Override
        public void run() {
            post(new Runnable() {
                @Override
                public void run() {
                    if (getText().toString()
                            .length() < mShowTextString.length()) {
                        if (isSpecialAction) {
                            if (getText().toString()
                                    .length() < dividerNumber) {

                                SpannableStringBuilder str = new SpannableStringBuilder(mShowTextString.substring(0,
                                        getText().toString()
                                        .length() + 1));
//                                str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_222)), 0,
//                                        getText().toString()
//                                                .length() + 1, 0);
                                str.setSpan(new ForegroundColorSpan(Color.parseColor("#222222")), 0,
                                        getText().toString()
                                                .length() + 1, 0);
                                setText(str);
                            } else {
                                SpannableStringBuilder str = new SpannableStringBuilder(mShowTextString.substring(0,
                                        dividerNumber));
//                                str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_222)), 0,
//                                        dividerNumber, 0);
                                str.setSpan(new ForegroundColorSpan(Color.parseColor("#222222")), 0,
                                        dividerNumber, 0);
                                SpannableStringBuilder str1 = new SpannableStringBuilder(mShowTextString.substring
                                        (dividerNumber, getText().toString()
                                                .length() + 1));
//                                str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_666)), 0,
//                                        str1.length(), 0);
                                str1.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0,
                                        str1.length(), 0);
                                str.append(str1);
                                setText(str);
                            }
                        } else {
                            setText(mShowTextString.substring(0, getText().toString()
                                    .length() + 1));
                        }
                        startTypeTimer();
                    } else {
                        stopTypeTimer();
                        if (null != mOnTypeViewListener) {
                            mOnTypeViewListener.onTypeOver();
                        }
                    }
                }
            });
        }
    }

    /**
     * 设置dividerNumber值
     */
    public void setDividerNum(int number) {
        this.dividerNumber = number;
    }

    /**
     * 从strings里取 字符串
     * @param resId
     */
    public void startResString(int resId) {
        start(mContext.getString(resId));
    }

    /**
     * 是否启动 前dividerNumber个字特别颜色 功能
     * true:启动   false:不启动
     */
    public void setSpecialAction(boolean flag) {
        this.isSpecialAction = flag;
    }

    /**
     * 监听打印机的开始和结束
     */
    public interface OnTypeViewListener {
        public void onTypeStart();

        public void onTypeOver();
    }
}