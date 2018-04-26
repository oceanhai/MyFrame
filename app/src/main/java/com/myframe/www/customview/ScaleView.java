package com.myframe.www.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.myframe.www.R;

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

    public ScaleView(Context context){
        super(context);
        initPaint(context, null);
    }

    public ScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context,attrs);
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        initPaint(context,attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {

        initTypeArray(context,attrs);

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

    /*圆弧线宽*/
    private float circleBorderWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
    /*内边距*/
    private float circlePadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

    /*字体大小*/
    private float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, getResources().getDisplayMetrics());
    private float topTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());
    private float bottomTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());

    /**
     * 自定义属性
     * @param context
     * @param attrs
     */
    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        
        circleBorderWidth = typedArray.getDimension(R.styleable.CircleView_circleWidth, 50);
        circlePadding = typedArray.getDimension(com.example.largeimg.stepapp.R.styleable.CircleView_circlePadding, 50);
        textSize = typedArray.getDimension(com.example.largeimg.stepapp.R.styleable.CircleView_stepTextSize, 240);
        topTextSize = typedArray.getDimension(com.example.largeimg.stepapp.R.styleable.CircleView_topTextSize, 60);
        bottomTextSize = typedArray.getDimension(com.example.largeimg.stepapp.R.styleable.CircleView_targetTextSize, 40);

        backCircleColor = typedArray.getColor
                (com.example.largeimg.stepapp.R.styleable.CircleView_backCircleColor, getResources().getColor(com.example.largeimg.stepapp.R.color.step_trans_light_grey));
        TopTextColor = typedArray.getColor(com.example.largeimg.stepapp.R.styleable.CircleView_TopTextColor, getResources().getColor(com.example.largeimg.stepapp.R.color.step_trans_black));
        stepTextColor = typedArray.getColor(com.example.largeimg.stepapp.R.styleable.CircleView_stepTextColor, getResources().getColor(com.example.largeimg.stepapp.R.color.step_trans_black));
        targetTextColor = typedArray.getColor(com.example.largeimg.stepapp.R.styleable.CircleView_targetTextColor, getResources().getColor(com.example.largeimg.stepapp.R.color.step_trans_black));
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
