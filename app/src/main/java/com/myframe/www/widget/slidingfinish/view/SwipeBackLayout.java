package com.myframe.www.widget.slidingfinish.view;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.myframe.www.R;


/**
 * 
 * @author xiaanming
 * 
 * @blog http://blog.csdn.net/xiaanming
 * 
 */
public class SwipeBackLayout extends FrameLayout {
	private static final String TAG = SwipeBackLayout.class.getSimpleName();
	private View mContentView;
	private int mTouchSlop;//触发移动事件的最短距离
	private int downX;
	private int downY;
	private int tempX;
	private Scroller mScroller;
	private int viewWidth;
	private boolean isSilding;
	private boolean isFinish;
	private Drawable mShadowDrawable;//阴影
	private Activity mActivity;
	private List<ViewPager> mViewPagers = new LinkedList<ViewPager>();//有viewpager布局时候，viewpager的集合

	public SwipeBackLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SwipeBackLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		//触发移动事件的最短距离
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mScroller = new Scroller(context);

		mShadowDrawable = getResources().getDrawable(R.drawable.shadow_left);
	}

	public void attachToActivity(Activity activity) {
		mActivity = activity;
//		TypedArray a = activity.getTheme().obtainStyledAttributes(
//				new int[] { android.R.attr.windowBackground });
//		int background = a.getResourceId(0, 0);
//		a.recycle();

		/**
		 * UI 界面框架图
		 * Activity
		 *  PhoneWindow
		 *   DecorView
		 *    ViewGroup(LinearLayout )  getChildAt(0)取到
		 *     TitleView
		 *     ContentView
		 */

		ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();// 获得根视图
		ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
//		decorChild.setBackgroundResource(background);//TODO 设置背景 其实没啥特殊作用 侧滑显示前一个activity是在清单文件中设置透明主题
		decor.removeView(decorChild);//把decorChild从decor移除，不然decorChild是无法被SwipeBackLayout addview操作的(因为decorChild存在父窗体)
		addView(decorChild);//SwipeBackLayout addview操作
		setContentView(decorChild);//mContentView 其实就是当前SwipeBackLayout
		decor.addView(this);// 在根视图，添加自定义的SwipeBackLayout
	}

	private void setContentView(View decorChild) {
		mContentView = (View) decorChild.getParent();// 父界面 mContentView 其实就是当前SwipeBackLayout
	}

	/**
	 * 事件拦截操作
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// 处理ViewPager冲突问题
		ViewPager mViewPager = getTouchViewPager(mViewPagers, ev);
		Log.i(TAG, "mViewPager = " + mViewPager);

		if (mViewPager != null && mViewPager.getCurrentItem() != 0) {
			return super.onInterceptTouchEvent(ev);
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = tempX = (int) ev.getRawX();
			downY = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getRawX();
			// 满足此条件屏蔽SildingFinishLayout里面子类的touch事件
			if (moveX - downX > mTouchSlop
					&& Math.abs((int) ev.getRawY() - downY) < mTouchSlop) {
				return true;
			}
			break;
		}

		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) event.getRawX();
			int deltaX = tempX - moveX;
			tempX = moveX;
			if (moveX - downX > mTouchSlop
					&& Math.abs((int) event.getRawY() - downY) < mTouchSlop) {
				isSilding = true;
			}

			//右滑 && isSilding=true
			if (moveX - downX >= 0 && isSilding) {
				mContentView.scrollBy(deltaX, 0);//横坐标移动deltaX距离
			}
			break;
		case MotionEvent.ACTION_UP:
			isSilding = false;

			/**
			 * 当前view的左上角相对于母视图的左上角的X轴偏移量（这里是负数） <= -viewWidth / 2
			 * true:滑动超过一半  false:滑动未超过一半
			 */
			if (mContentView.getScrollX() <= -viewWidth / 2) {
				isFinish = true;
				scrollRight();
			} else {
				scrollOrigin();
				isFinish = false;
			}
			break;
		}

		return true;
	}

	/**
	 * 获取SwipeBackLayout里面的ViewPager的集合
	 * 
	 * @param mViewPagers
	 * @param parent
	 */
	private void getAlLViewPager(List<ViewPager> mViewPagers, ViewGroup parent) {
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {// 在ViewGroup中查找是否存在viewPager
			View child = parent.getChildAt(i);
			if (child instanceof ViewPager) {
				mViewPagers.add((ViewPager) child);
			} else if (child instanceof ViewGroup) {
				getAlLViewPager(mViewPagers, (ViewGroup) child);
			}
		}
	}

	/**
	 * 返回我们touch的ViewPager
	 * 
	 * @param mViewPagers
	 * @param ev
	 * @return
	 */
	private ViewPager getTouchViewPager(List<ViewPager> mViewPagers,
			MotionEvent ev) {
		if (mViewPagers == null || mViewPagers.size() == 0) {
			return null;
		}
		Rect mRect = new Rect();
		for (ViewPager v : mViewPagers) {
			v.getHitRect(mRect);//ViewPager的矩形
			//矩形内
			if (mRect.contains((int) ev.getX(), (int) ev.getY())) {
				return v;
			}
		}
		return null;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			viewWidth = this.getWidth();

			getAlLViewPager(mViewPagers, this);
			Log.i(TAG, "ViewPager size = " + mViewPagers.size());
		}
	}

	/**
	 * 这个方法主要用于控制子View的绘制分发，重载该方法可改变子View的绘制，进而实现一些复杂的视效，
	 * @param canvas
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mShadowDrawable != null && mContentView != null) {

			int left = mContentView.getLeft()
					- mShadowDrawable.getIntrinsicWidth();
			int right = left + mShadowDrawable.getIntrinsicWidth();
			int top = mContentView.getTop();
			int bottom = mContentView.getBottom();

			mShadowDrawable.setBounds(left, top, right, bottom);
			mShadowDrawable.draw(canvas);
		}

	}

	/**
	 * 滚动出界面
	 */
	private void scrollRight() {
		final int delta = (viewWidth + mContentView.getScrollX());//屏幕宽+当前view的左上角相对于母视图的左上角的X轴偏移量（在这里是负数）
		// 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
		mScroller.startScroll(mContentView.getScrollX(), 0, -delta + 1, 0,
				Math.abs(delta));
		postInvalidate();
	}

	/**
	 * 滚动到起始位置
	 */
	private void scrollOrigin() {
		int delta = mContentView.getScrollX();//这是负数
		//第三个参数  正数：向左滑动  负数：向右滑动
		mScroller.startScroll(mContentView.getScrollX(), 0, -delta, 0,
				Math.abs(delta));
		postInvalidate();// TODO 刷新界面，但为啥要用在工作者线程的postInvalidate，而不是invalidate()？？？
	}

	@Override
	public void computeScroll() {
		// 调用startScroll的时候scroller.computeScrollOffset()返回true，
		if (mScroller.computeScrollOffset()) {// 判断是否滑动结束
			mContentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();

			if (mScroller.isFinished() && isFinish) {
				mActivity.finish();
			}
		}
	}

}
