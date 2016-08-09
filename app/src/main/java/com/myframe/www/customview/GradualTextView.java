package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
* 文字从左自右 渐变颜色
* @author wuhai
* create at 2016/7/28 10:49
*/
public class GradualTextView extends TextView{
    private TextPaint paint;

    public GradualTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new TextPaint(getPaint());
        paint.setColor(Color.RED);
    }

    private int right;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipRect(0, 0, right, getBottom());
        // 参数1:字的内容
        // 参数2和参数3:从什么地方开始写
        canvas.drawText(getText().toString(), 0, getBaseline(), paint);
        canvas.restore();
        right++;
        right = right % getWidth();
        invalidate();
    }
}
