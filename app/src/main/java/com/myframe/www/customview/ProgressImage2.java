package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.myframe.www.R;

/** 
* 图片 颜色向上渐变效果
* @author wuhai
* create at 2016/7/28 10:34
*/  
public class ProgressImage2 extends ImageView {
	private Drawable maskDrawable;
	public ProgressImage2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 初始化另外一张红色图片
		maskDrawable = getResources().getDrawable(R.drawable.red_bg);
	}

	private int progress;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		int topline = (int) (getHeight() - getHeight() * progress * 1.0 / 100);
		canvas.clipRect(0, topline, getWidth(), getHeight());// 截取一个矩形
		maskDrawable.draw(canvas);// 把红色的图片画上来
		canvas.restore();
		progress += 1;
		if (progress > 100) {
			progress = 0;
		}
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 测量的时候设置maskDrawable的左上右下的参数
		maskDrawable.setBounds(0, 0, getWidth(), getHeight());
	}

}
