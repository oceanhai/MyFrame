package com.myframe.www.widget.customview;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomView extends LinearLayout {

	private Scroller mScroller;
	private Handler mHandler;
	private boolean mIsScrolling = false;//正在滑动
	private GridView mGrid = null;
	
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
		mHandler = new MyHandler(this);
	}

	public boolean isScrolling(){
		return this.mIsScrolling;
	}
	
	/**
	 * 
	 * @Title: smoothScrollTo 
	 * @说       明:	（暂时没用到）
	 * @参       数: @param dy
	 * @参       数: @param max
	 * @参       数: @param up
	 * @参       数: @param grid   
	 * @return void    返回类型 
	 * @throws
	 */
	public void smoothScrollTo(float dy, float max, boolean up, GridView grid){
		this.mGrid = grid;
		smoothScrollTo(dy, max, up);
	}

	/**
	 * 上面的方法调用了（但上面的方法没用到）
	 * @param dy
	 * @param max
	 * @param up
	 */
	public void smoothScrollTo(float dy, float max, boolean up){
		mIsScrolling = true;
		int duration;
		float tmp;
		if(up){
			duration = (int)(dy / max * 300);
			tmp = dy;
		}else{
			tmp = max - dy;
			duration = (int)(tmp / max * 300);
		}
		int slot = 50;
		int time = duration / slot;
		tmp /= time;
		int i;
		for(i=0; i<time; i++){
			Message msg = mHandler.obtainMessage(0);
			if(up)
				msg.arg1 = -(int)(dy - tmp * i);
			else
				msg.arg1 = -(int)(dy + tmp * i);
			msg.arg2 = 0;
			mHandler.sendMessageDelayed(msg, i * slot);
		}
		Message msg = mHandler.obtainMessage(0);
		if(up)
			msg.arg1 = 0;
		else
			msg.arg1 = -(int)max;
		msg.arg2 = 1;
		mHandler.sendMessageDelayed(msg, i * slot);
	}
	
	//调用此方法滚动到目标位置
	public void smoothScrollTo(int fx, int fy) {
		mIsScrolling = true;
		int dx = fx - mScroller.getFinalX();//fx-最终停止的水平位置 
		int dy = fy - mScroller.getFinalY();//fy-最终停止的竖直位置 
		smoothScrollBy(dx, dy);
	}

	//调用此方法设置滚动的相对偏移
	public void smoothScrollBy(int dx, int dy) {
		//设置mScroller的滚动偏移量
		mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, 500);
		invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
		mHandler.sendEmptyMessageDelayed(1, 500);//TODO 为啥要通过handler给mIsScrolling置false，直接在computeScroll滚动完成之后置false不好吗？
	}

	/**
	 * 绘制view时候调用（draw()）
	 */
	@Override
	public void computeScroll() {
	
		//先判断mScroller滚动是否完成
		if (mScroller.computeScrollOffset()) {
		
			//这里调用View的scrollTo()完成实际的滚动
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			
			//必须调用该方法，否则不一定能看到滚动效果
			postInvalidate();
		}
		super.computeScroll();
	}
	
	private static class MyHandler extends Handler {
		private final WeakReference<CustomView> mView;

		MyHandler(CustomView view) {
			this.mView = new WeakReference<CustomView>(view);
		}

		@Override
		public void handleMessage(Message msg) {
			CustomView service = mView.get();
			if (service != null) {
				super.handleMessage(msg);
				try {
					switch (msg.what) {
					case 0://TODO 什么地方调用了？
						service.scrollTo(0, msg.arg1);
						if(msg.arg2 == 1){
							service.mIsScrolling = false;
							if(service.mGrid != null)
								service.mGrid.setSelection(0);
						}
						break;
					case 1:
						service.mIsScrolling = false;//滑动结束
						break;
					}
				} catch (Exception e) {
				}
			}
		}
	}
}
