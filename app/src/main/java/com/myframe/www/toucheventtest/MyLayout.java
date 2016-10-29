package com.myframe.www.toucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import www.wuhai.common.utils.L;

public class MyLayout extends LinearLayout {

	// public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
	// super(context, attrs, defStyleAttr);
	// }

	// xml 无样式
	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// 代码
	public MyLayout(Context context) {
		super(context);
	}

	// 分法事件
	// 不会重写
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		L.v(TouchEventActivity.TAG, " MyLayout==dispatchTouchEvent==" + ev.getAction());
		return super.dispatchTouchEvent(ev);
	}

	// 拦截事件
	// false 不拦截 事件会继续往下传递
	// true 拦截 onTounch
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		L.v(TouchEventActivity.TAG, " MyLayout==onInterceptHoverEvent==" + ev.getAction());
//		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//			return true;
//			// action_cancel 通知内蹭的组件，不用处理事件了
//		}

		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		L.v(TouchEventActivity.TAG, " MyLayout==onTouchEvent==" + event.getAction());
		return false;
	}

}
