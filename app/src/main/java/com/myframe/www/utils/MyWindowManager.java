package com.myframe.www.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

public class MyWindowManager {

	/**
	 * 获取手机屏幕宽
	 * @param mContext
	 * @return
	 */
	public static int getWidth(Context mContext){
		
		
		WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);

			int width = wm.getDefaultDisplay().getWidth();
		return width;
	}

	/**
	 * 获取手机屏幕高
	 * @param mContext
	 * @return
	 */
	public static int getHeight(Context mContext){
		
		
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		
		int height = wm.getDefaultDisplay().getHeight();
		return height;
	}
	/**
	 * 获取当前分辨率下指定单位对应的像素大小（根据设备信息）
	 * px,dip,sp -> px
	 * 
	 * Paint.setTextSize()单位为px
	 * 
	 * 代码摘自：TextView.setTextSize()
	 * 
	 * @param unit  TypedValue.COMPLEX_UNIT_*
	 * @param size
	 * @return
	 */
	public static float getRawSize(Context mContext,int unit, float size) {
	     
	       Resources r;

	       if (mContext == null)
	           r = Resources.getSystem();
	       else
	           r = mContext.getResources();
	        
	       return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
	}

	/**
	 * 为view设置宽高
	 * @param mContext
	 * @param view
	 * @param width
	 * @param defaultHeight
	 * @param defaultWidth
	 */
	public static void setViewHeitgh(Context mContext,View view,float width,int defaultHeight,int defaultWidth){
		LayoutParams linearParams = view.getLayoutParams(); // 取控件mGrid当前的布局参数   
		float height = width/defaultWidth*defaultHeight;
		
		linearParams.height = (int) height;// 当控件的高强制设成50象素
		linearParams.width = (int) width;// 当控件的高强制设成50象素
		view.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件myGrid  
	}

}
