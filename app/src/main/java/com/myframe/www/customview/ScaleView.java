package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆环 带刻度的
 */
public class ScaleView extends View{

    /**
     * 间隔条角度大小
     */
    private float mLineWidth = 0.3f;

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint textTopPaint;
    private Paint textMidPaint;
    private Paint textBottonPaint;

    public ScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setColor(Color.BLACK);
        mPaint1.setAntiAlias(true);
        mPaint1.setStrokeWidth(2);

        mPaint2 = new Paint();
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setColor(Color.BLACK);
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeWidth(20);

        textTopPaint = new Paint();
        textTopPaint.setStyle(Paint.Style.STROKE);
        textTopPaint.setColor(Color.BLACK);
        textTopPaint.setTextSize(40);
        textTopPaint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
        canvas.drawArc(rectF,0,360,false,mPaint1);

        int centerXY = getMeasuredHeight()/2;

        RectF rectF1 = new RectF(20,20,getMeasuredWidth()-20,getMeasuredHeight()-20);
        int start = 225;//起始角度
        for(int x=0;x<15;x++){
//            canvas.drawLine(r,r,r-30,r-30,mPaint);
            canvas.drawArc(rectF1,start-mLineWidth,mLineWidth,false,mPaint2);
            start+=6;
        }

        float topTextX = centerXY - textTopPaint.measureText("真任性啊")/2;//中间位置-字宽度/2
        int topTextY = (int) (textTopPaint.descent()-textTopPaint.ascent());//计算文字高度
        canvas.drawText("真任性啊-中", topTextX, centerXY+topTextY/2,textTopPaint);
        canvas.drawText("真任性啊-上", topTextX, centerXY-topTextY/2,textTopPaint);
        canvas.drawText("真任性啊-下", topTextX, centerXY+3*topTextY/2,textTopPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width,height) ,Math.min(width,height));
    }
}
