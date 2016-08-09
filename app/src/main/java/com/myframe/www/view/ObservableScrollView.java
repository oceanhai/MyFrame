package com.myframe.www.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 带浮动条的ScrollView
 * 
 */
public class ObservableScrollView extends ScrollView {
	private OnScrollListener onScrollListener = null;
	private float xDistance, yDistance, xLast, yLast;
	private View viewInScroll, viewOutScroll;
	private boolean mIsShow = true;

	public void setmIsShow(boolean mIsShow) {
		this.mIsShow = mIsShow;
	}

	public ObservableScrollView(Context context, AttributeSet attrs,
			int defStyle) {

		super(context, attrs, defStyle);

	}

	public ObservableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ObservableScrollView(Context context) {
		super(context);
	}

	/**
	 * 
	 * @Title: setOnScrollListener 
	 * @说       明:回调监听滚动接口（但外部不需要监听的时候是可以不用设置的，demo里就无需设置）
	 * @参       数: @param onScrollListener   
	 * @return void    返回类型 
	 * @throws
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (onScrollListener != null) {
			onScrollListener.onScrollChanged(this, x, y, oldx, oldy);
		}
		if (mIsShow) {
			computeFloatIfNecessary();//实现浮动效果
		}
		
	}

	/**
	 * 监听ScrollView滚动接口
	 * 
	 */
	public interface OnScrollListener {

		public void onScrollChanged(ObservableScrollView scrollView, int x,
									int y, int oldx, int oldy);

	}

	/**
	 * 设置需要浮动的View
	 * 
	 * @param viewInScroll
	 *            ScollView内的view，非浮动的View
	 * @param viewOutScroll
	 *            ScollView外的view，真正需要浮动的view（其实是固定住的）
	 */
	public void setFloatView(View viewInScroll, View viewOutScroll) {
		this.viewInScroll = viewInScroll;
		this.viewOutScroll = viewOutScroll;
	}

	/**
	 * 
	 * @Title: computeFloatIfNecessary 
	 * @说       明:实现浮动效果
	 * @参       数:    
	 * @return void    返回类型 
	 * @throws
	 */
	private void computeFloatIfNecessary() {
		if (viewInScroll == null && viewOutScroll == null) {
			return;
		}

		int[] location = new int[2];
		this.getLocationInWindow(location);//ObservableScrollView获取在当前窗口内的绝对坐标
		// 获取非浮动View的x,y坐标
		int[] loc = new int[2];
		viewInScroll.getLocationOnScreen(loc);

		// 当非浮动view的y <= ObservableScrollView的y坐标时，把固定的view显示出来
		if (loc[1] <= location[1]) {
			// 处理一下把原有view设置INVISIBLE，这样显示效果会好点
			if (viewOutScroll.getVisibility() != View.VISIBLE) {
				viewOutScroll.setVisibility(View.VISIBLE);
			}

		} else {
			// 记得还原回来
			if (viewOutScroll.getVisibility() == View.VISIBLE) {
				viewOutScroll.setVisibility(View.INVISIBLE);
			}

		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();

			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;

			if (xDistance > yDistance) {//滑动时，横向滑动距离>纵向滑动距离时候，不消费事件（不上下滑动）
				return false;
			}
		}

		return super.onInterceptTouchEvent(ev);
	}
}