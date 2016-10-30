package com.myframe.www.testtouchevent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

/**
 * View分发机制
 */
public class TouchEventActivity extends BaseActivity {

    public static final String TAG = "TouchEventActivity";
    @Bind(R.id.myview01)
    MyView myview01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TouchEventActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        ButterKnife.bind(this);

//        myview01.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                L.v(TAG, " onTouch==" + event.getAction());
//                return false;
//            }
//        });
    }

    //分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.v(TAG, " dispatchTouchEvent==" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    //处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.v(TAG, " onTouchEvent==" + event.getAction());
        return super.onTouchEvent(event);
    }

    public void myviw01(View view) {
        L.v(TAG, "myviwe01点击了");
    }

    public void myviw02(View view) {
        L.v(TAG, "myviwe02点击了");
    }
}
