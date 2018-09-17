package com.myframe.www.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * createby yangzheng
 * date 2016/11/2
 * email yangzhenop@126.com
 */
public class HeaderTextGridView extends View {

    private Paint paint = new Paint();
    private int primLineColor;
    private int secLineColor;
    private int textColor;
    private int lightColor;
    private int titleColor;
    private float width;
    private float height;
    private int alpha;
    private String text[][];
    private RectF textBounds;

    private Timer timer = new Timer(true);
    private TimerTask mTask;
    boolean taskRunning;
    private int step = 0;
    private static final int MAX_STEP = 10;
    private int x = 4;
    private int y = 3;

    public HeaderTextGridView(Context context) {
        super(context);
        init();
    }

    public HeaderTextGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textBounds = new RectF();
        taskRunning = false;
        primLineColor = Color.parseColor("#656d78");
        secLineColor = Color.parseColor("#e6e9ed");
        textColor = Color.parseColor("#656d78");
        titleColor = Color.parseColor("#f3f4f6");
        lightColor = Color.rgb(255, 197, 19);
        alpha = 0;
    }

    public void setResource(String[][] source){
        if(source != null && source.length > 0 && source[0].length > 0){
            x = source.length;
            y = source[0].length;
            text = source;
            invalidate();
        }
    }

    private void stopLight() {
        if(mTask != null)
            mTask.cancel();
        timer.purge();
    }

    public void startLight() {
        if (taskRunning) {
            stopLight();
        }
        step = 0;
        try {
            timer.scheduleAtFixedRate(mTask = new TimerTask() {
                @Override
                public void run() {
                    taskRunning = true;
                    if (step <= MAX_STEP) {
                        alpha = 255 -  step * 255 / MAX_STEP;
                        postInvalidate();
                        step++;
                    } else {
                        taskRunning = false;
                        stopLight();
                    }
                }
            }, 0, 100);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        drawBackground(canvas);
        drawLine(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        if(text != null){
            paint.setColor(textColor);
            paint.setTextSize(sp(13));
            for(int i=0; i<text.length; i++){
                for(int j=0; j<text[i].length; j++){
                    if(!TextUtils.isEmpty(text[i][j])){
                        textBounds.set(width * j / text[i].length,height * i / text.length,width * (j+1) / text[i].length,height * (i+1) / text.length);
                        drawTextInCenterRect(text[i][j],textBounds,canvas);
                    }
                }
            }
            paint.reset();
        }
    }

    private int sp(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private void drawBackground(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(titleColor);
        canvas.drawRect(new RectF(0, 0, width, height * 1 / x), paint);
        paint.setColor(lightColor);
        paint.setAlpha(alpha);
        canvas.drawRect(new RectF(width * (y-1) / y, height * 1 / x, width, height), paint);
        paint.reset();
    }

    private void drawLine(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(primLineColor);
        canvas.drawLine(0, 0, width, 0, paint);
        canvas.drawLine(width, 0, width, height, paint);
        canvas.drawLine(width, height, 0, height, paint);
        canvas.drawLine(0, height, 0, 0, paint);
        paint.setColor(secLineColor);
        for(int i=1; i<x; i++){
            canvas.drawLine(0, height * i / x, width, height * i / x, paint);
        }
        for(int i=1; i<y; i++){
            canvas.drawLine(width * i / y, 0, width * i / y, height, paint);
        }
        paint.reset();
    }

    private void drawTextInCenterRect(String target,RectF targetRect, Canvas canvas) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (int) ((targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2);
        float length = paint.measureText(target);
        canvas.drawText(target,targetRect.centerX() - length / 2,baseline,paint);
    }
}