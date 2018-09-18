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

import www.wuhai.common.utils.L;

/**
 * 自定义表格 玩法
 */
public class HeaderTextGridView extends View {

    public static final String TAG = "HeaderTextGridView";

    private Paint paint = new Paint();
    private int primLineColor;//表格边框线
    private int secLineColor;//表格内线
    private int lightColor;//闪烁颜色       屎黄
    private int alpha;//透明度
    private int titleColor;//title背景色   灰色
    private float width;//宽
    private float height;//高
    private String text[][];//表格内 文本
    private int textColor;//文本颜色
    private RectF textBounds;//表格内 文本区域

    private Timer timer = new Timer(true);
    private TimerTask mTask;
    boolean taskRunning;//闪烁开关
    private int step = 0;
    private static final int MAX_STEP = 10;
    private int x = 4;//高四等分
    private int y = 3;//宽三等分

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
        L.e(TAG,"onDetachedFromWindow");
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
                        textBounds.set(width * j / text[i].length,
                                height * i / text.length,
                                width * (j+1) / text[i].length,
                                height * (i+1) / text.length);
                        drawTextInCenterRect(text[i][j],textBounds,canvas);
                    }
                }
            }
            paint.reset();
        }
    }

    /**
     * 把非标准尺寸转换成标准尺寸
     * @param sp
     * @return
     */
    private int sp(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private void drawBackground(Canvas canvas) {
        /**
         * 绘制高度占1/4的title 灰色背景
         */
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(titleColor);
        canvas.drawRect(new RectF(0, 0, width, height * 1 / x), paint);

        /**
         * 绘制闪烁区域
         * setColor 和setAlpha顺序不能对调，因为setColor里有对alpha再次设值
         */
        paint.setColor(lightColor);
        paint.setAlpha(alpha);
        canvas.drawRect(new RectF(width * (y-1) / y, height * 1 / x, width, height), paint);
        paint.reset();//重置 paint
    }

    private void drawLine(Canvas canvas) {
        /**
         * 绘制 表格外边框线（顺时针画）
         */
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(primLineColor);
        canvas.drawLine(0, 0, width, 0, paint);//上边框线
        canvas.drawLine(width, 0, width, height, paint);//右边框线
        canvas.drawLine(width, height, 0, height, paint);//下边框线
        canvas.drawLine(0, height, 0, 0, paint);//左边框线

        /**
         * 绘制表格 内线
         */
        paint.setColor(secLineColor);
        for(int i=1; i<x; i++){//横线
            canvas.drawLine(0, height * i / x, width, height * i / x, paint);
        }
        for(int i=1; i<y; i++){//竖线
            canvas.drawLine(width * i / y, 0, width * i / y, height, paint);
        }
        paint.reset();
    }

    private void drawTextInCenterRect(String target,RectF targetRect, Canvas canvas) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        /**
         * 两种计算baseline的方法
         */
        int baseline = (int) ((targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2);
        L.e(TAG,"targetRect.bottom="+targetRect.bottom+",targetRect.top="+targetRect.top+
                ",fontMetrics.bottom="+fontMetrics.bottom+",fontMetrics.top="+fontMetrics.top+",baseline="+baseline);
//        baseline = (int) (targetRect.centerY()-fontMetrics.top/2-fontMetrics.bottom/2);
//        L.e(TAG,"targetRect.centerY()="+targetRect.centerY()+",baseline="+baseline);
        float length = paint.measureText(target);//文本长
        /**
         * targetRect.centerX() - length / 2  => 开始的位置
         *
         */
        canvas.drawText(target,targetRect.centerX() - length / 2,baseline,paint);
    }
}