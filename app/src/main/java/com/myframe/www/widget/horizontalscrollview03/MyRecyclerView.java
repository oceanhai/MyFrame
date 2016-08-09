package com.myframe.www.widget.horizontalscrollview03;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/** 
* 参考http://blog.csdn.net/lmj623565791/article/details/38173061
* OnScrollListener在新的包中，已经不再是接口了
* 所以对MyRecyclerView进行了改动 如下
*
* @author wuhai
* create at 2016/2/17 13:06
*/  
public class MyRecyclerView extends RecyclerView
{

	/**
	 * 记录当前第一个View
	 */
	private View mCurrentView;

	private OnItemScrollChangeListener mItemScrollChangeListener;

	public void setOnItemScrollChangeListener(
			OnItemScrollChangeListener mItemScrollChangeListener)
	{
		this.mItemScrollChangeListener = mItemScrollChangeListener;
	}

	public interface OnItemScrollChangeListener
	{
		void onChange(View view, int position);
	}

	public MyRecyclerView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//				super.onScrolled(recyclerView, dx, dy);
				View newView = getChildAt(0);

				if (mItemScrollChangeListener != null)
				{
					if (newView != null && newView != mCurrentView)
					{
						mCurrentView = newView ;
						mItemScrollChangeListener.onChange(mCurrentView,
								getChildPosition(mCurrentView));

					}
				}

			}
		});
	}


	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);

		mCurrentView = getChildAt(0);

		if (mItemScrollChangeListener != null)
		{
			mItemScrollChangeListener.onChange(mCurrentView,
					getChildPosition(mCurrentView));
		}
	}


//	public void onScrollStateChanged(int arg0)
//	{
//	}
//
//	/**
//	 *
//	 * 滚动时，判断当前第一个View是否发生变化，发生才回调
//	 */
//	public void onScrolled(int arg0, int arg1)
//	{
//		View newView = getChildAt(0);
//
//		if (mItemScrollChangeListener != null)
//		{
//			if (newView != null && newView != mCurrentView)
//			{
//				mCurrentView = newView ;
//				mItemScrollChangeListener.onChange(mCurrentView,
//						getChildPosition(mCurrentView));
//
//			}
//		}
//
//	}

}
