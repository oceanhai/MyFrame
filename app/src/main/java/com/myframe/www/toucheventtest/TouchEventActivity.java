package com.myframe.www.toucheventtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import www.wuhai.common.utils.L;

/**
 * View分发机制
 */
public class TouchEventActivity extends BaseActivity {

    public static final String TAG = "TouchEventActivity";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TouchEventActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
    }

    //分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.v(TAG," dispatchTouchEvent=="+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    //处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.v(TAG, " onTouchEvent==" + event.getAction());
        return super.onTouchEvent(event);
    }
}
