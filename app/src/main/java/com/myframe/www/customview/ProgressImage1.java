package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.myframe.www.R;

public class ProgressImage1 extends ImageView {
	private Drawable maskDrawable;
	public ProgressImage1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 初始化另外一张红色图片
		maskDrawable = getResources().getDrawable(R.drawable.red_bg);
	}

	private int progress;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		int topLine = (int) (getHeight() - getHeight() * progress * 1.0 / 100);
		// 截取一个矩形
		canvas.clipRect(0, topLine, getWidth(), getHeight());
		// 把另外的图片画上来
		maskDrawable.draw(canvas);
		canvas.restore();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 测量的时候设置maskDrawable的左上右下的参数
		maskDrawable.setBounds(0, 0, getWidth(), getHeight());
	}

	public void setProgress() {
		progress += 5;
		if (progress > 100) {
			progress = 0;
		}
		invalidate();

	}

}
