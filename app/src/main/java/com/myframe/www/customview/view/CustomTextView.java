package com.myframe.www.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import www.wuhai.common.utils.L;

/**
 * Created by wuhai on 2018/9/18.
 */

public class CustomTextView extends View{

    private Paint mPaint;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        L.e("wh","aaaaaaaaaaaaaaaa");
        canvas.save();
        canvas.drawText("who are you",0,3,100,100,mPaint);
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setTextSize(15);
    }
}
