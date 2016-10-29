package com.myframe.www.toucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import www.wuhai.common.utils.L;

public class MyView extends TextView {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context) {
		super(context);
	}

	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		L.v(TouchEventActivity.TAG, " MyView==dispatchTouchEvent==" + event.getAction());
		return super.dispatchTouchEvent(event);
	}
	
	//处理
	//false  没有处理
	//true  处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		L.v(TouchEventActivity.TAG, " MyView==onTouchEvent==" + event.getAction());
		return true;
	}
	

}
