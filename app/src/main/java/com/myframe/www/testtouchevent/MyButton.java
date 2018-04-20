package com.myframe.www.testtouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import www.wuhai.common.utils.L;

public class MyButton extends Button {

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyButton(Context context) {
		super(context);
	}

	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		L.v(TouchEventActivity.TAG, " MyButton==dispatchTouchEvent==" + event.getAction());
		return super.dispatchTouchEvent(event);
	}


	//处理
	//false  没有处理
	//true  处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		L.v(TouchEventActivity.TAG, " MyButton==onTouchEvent==" + event.getAction());
//		return true;
//		return false;
		return super.onTouchEvent(event);
	}

}
