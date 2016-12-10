package step.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.largeimg.stepapp.R;


/**
 * Created by DongJr on 2016/5/10.
 */
public class CircleView extends View {

    /*圆弧线宽*/
    private float circleBorderWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
    /*内边距*/
    private float circlePadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

    /*字体大小*/
    private float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, getResources().getDisplayMetrics());
    private float topTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());
    private float bottomTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());
    /*颜色*/
    private int backCircleColor;
    private int stepTextColor;
    private int TopTextColor;
    private int targetTextColor;

    /*绘制圆周的画笔*/
    private Paint backCirclePaint;
    /*绘制内圆的画笔*/
    private Paint innerCirclePaint;
    /*绘制内圆边线*/
    private Paint lineCirclePaint;
    /*绘制刻度的画笔*/
    private Paint linePaint;
    /*绘制文字的画笔*/
    private Paint textPaint;
    /*顶部文字画笔*/
    private Paint topPaint;
    /*底部文字画笔*/
    private Paint bottomPaint;
    /*百分比*/
    private float percent = 1;
    /*渐变圆周颜色数组*/
    private int[] gradientColorArray = new int[]{
            Color.parseColor("#febe3d"),
            Color.parseColor("#febe3d")
    };

    private Paint gradientCirclePaint;
    private int mCurrentStep = 0;
    private int mTargetStep = 5000;
    private String mTopText = "今日步数";
    private String mBottomText = "目标:";

    public CircleView(Context context) {
        super(context);
        init(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        initTypeArray(context, attrs);


        backCirclePaint = new Paint();
        //设置画笔的风格(空心或者实心)
        backCirclePaint.setStyle(Paint.Style.STROKE);
        //设置画笔锯齿效果,true代表无锯齿
        backCirclePaint.setAntiAlias(true);
        //设置画笔颜色
        backCirclePaint.setColor(getResources().getColor(R.color.step_background));
        //设置空心边框的宽度
        backCirclePaint.setStrokeWidth(circleBorderWidth);
//        backCirclePaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));

        innerCirclePaint = new Paint();
        innerCirclePaint.setStyle(Paint.Style.FILL);
        innerCirclePaint.setAntiAlias(true);
        innerCirclePaint.setColor(getResources().getColor(R.color.step_white));

        //刻度盘外侧圆圈
        lineCirclePaint = new Paint();
        lineCirclePaint.setStyle(Paint.Style.STROKE);
        lineCirclePaint.setAntiAlias(true);
        lineCirclePaint.setColor(getResources().getColor(R.color.step_background));
        lineCirclePaint.setStrokeWidth(2);

        //刻度线
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(getResources().getColor(R.color.step_trans_light_grey));
        linePaint.setStrokeWidth(4);

        gradientCirclePaint = new Paint();
        gradientCirclePaint.setStyle(Paint.Style.STROKE);
        gradientCirclePaint.setAntiAlias(true);
        gradientCirclePaint.setColor(Color.LTGRAY);
        gradientCirclePaint.setStrokeWidth(circleBorderWidth);
        //设置弧度
        gradientCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(stepTextColor);
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
//        textPaint.setTextAlign(Paint.Align.CENTER);

        topPaint = new Paint();
        topPaint.setAntiAlias(true);
        topPaint.setTextSize(topTextSize);
        topPaint.setColor(TopTextColor);
//        topPaint.setTextAlign(Paint.Align.CENTER);

        bottomPaint = new Paint();
        bottomPaint.setAntiAlias(true);
        bottomPaint.setTextSize(bottomTextSize);
        bottomPaint.setColor(targetTextColor);
//        bottomPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        circleBorderWidth = typedArray.getDimension(R.styleable.CircleView_circleWidth, 50);
        circlePadding = typedArray.getDimension(R.styleable.CircleView_circlePadding, 50);
        textSize = typedArray.getDimension(R.styleable.CircleView_stepTextSize, 240);
        topTextSize = typedArray.getDimension(R.styleable.CircleView_topTextSize, 60);
        bottomTextSize = typedArray.getDimension(R.styleable.CircleView_targetTextSize, 40);

        backCircleColor = typedArray.getColor
                (R.styleable.CircleView_backCircleColor, getResources().getColor(R.color.step_trans_light_grey));
        TopTextColor = typedArray.getColor(R.styleable.CircleView_TopTextColor, getResources().getColor(R.color.step_trans_black));
        stepTextColor = typedArray.getColor(R.styleable.CircleView_stepTextColor, getResources().getColor(R.color.step_trans_black));
        targetTextColor = typedArray.getColor(R.styleable.CircleView_targetTextColor, getResources().getColor(R.color.step_trans_black));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(measureWidth, measureHeight), Math.min(measureWidth, measureHeight));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.绘制灰色背景圆环，drawArcm绘制弧形，扇形
        RectF rectF = new RectF(circlePadding, circlePadding,
                getMeasuredWidth() - circlePadding, getMeasuredHeight() - circlePadding);
        canvas.drawArc(rectF, 0, 360, false, backCirclePaint);

        //2.绘制颜色渐变圆环
        //float x0: 渐变起始点x坐标
        //float y0:渐变起始点y坐标
        //float x1:渐变结束点x坐标
        //float y1:渐变结束点y坐标
        //int[] colors:颜色 的int 数组
        //float[] positions: 相对位置的颜色数组,可为null,  若为null,可为null,颜色沿渐变线均匀分布
        //Shader.TileMode tile: 渲染器平铺模式
        LinearGradient linearGradient = new LinearGradient(circlePadding / 2, circlePadding / 2,
                getMeasuredWidth() - circlePadding / 2,
                getMeasuredHeight() - circlePadding / 2,
                gradientColorArray, null, Shader.TileMode.MIRROR);
        gradientCirclePaint.setShader(linearGradient);
        gradientCirclePaint.setShadowLayer(10, 10, 10, Color.RED);

        canvas.drawArc(rectF, -90, percent * 360, false, gradientCirclePaint);

        //绘制内部实心圆
        RectF innerRectF = new RectF(circlePadding + circleBorderWidth * 1.5f, circlePadding + circleBorderWidth * 1.5f,
                getMeasuredWidth() - (circlePadding + circleBorderWidth * 1.5f),
                getMeasuredHeight() - (circlePadding + circleBorderWidth * 1.5f));
        canvas.drawArc(innerRectF, 0, 360, false, innerCirclePaint);

        //绘制刻度盘边框
        RectF lineCircleRectF = new RectF(circlePadding + circleBorderWidth * 1.5f, circlePadding + circleBorderWidth * 1.5f,
                getMeasuredWidth() - (circlePadding + circleBorderWidth * 1.5f),
                getMeasuredHeight() - (circlePadding + circleBorderWidth * 1.5f));
        canvas.drawArc(lineCircleRectF, 0, 360, false, lineCirclePaint);

        //3.绘制刻度线

        //半径
        float radius = (getMeasuredWidth() - circlePadding - circleBorderWidth * 1.5f) / 2;
        //X轴中点坐标
        int centerX = getMeasuredWidth() / 2;

        for (float i = -45; i < 45; i += 6) {
            double rad = i * Math.PI / 180;
//            float startX = (float) (centerX - (radius - circleBorderWidth ) * Math.sin(rad));
//            float startY = (float) (centerX - (radius - circleBorderWidth ) * Math.cos(rad));

            float startX = (float) (centerX - (radius - circleBorderWidth - circlePadding / 2) * Math.sin(rad));
            float startY = (float) (centerX - (radius - circleBorderWidth - circlePadding / 2) * Math.cos(rad));

            float stopX = (float) (centerX - (radius - circleBorderWidth - circlePadding / 2 - 20) * Math.sin(rad));
            float stopY = (float) (centerX - (radius - circleBorderWidth - circlePadding / 2 - 20) * Math.cos(rad));

            canvas.drawLine(startX, startY, stopX, stopY, linePaint);
        }

        //4.绘制文字
        float topTextX = centerX - topPaint.measureText(mTopText + "") / 2;
        float textX = centerX - textPaint.measureText(mCurrentStep + "") / 2;
        float bottomTextX = centerX - bottomPaint.measureText(mBottomText + mTargetStep) / 2;

        int textY = (int) (textPaint.descent() - textPaint.ascent());
        int topTextY = (int) (topPaint.descent() - textPaint.ascent());
        int bottomTextY = (int) (bottomPaint.getFontMetrics().descent - textPaint.getFontMetrics().ascent);

        float textCenterY = getMeasuredHeight() / 2 - textY / 5;

        canvas.drawText(mTopText, topTextX,
                textCenterY - topTextY / 2, topPaint);

        canvas.drawText(mCurrentStep + "",
                textX, textCenterY + textY / 2, textPaint);

        canvas.drawText(mBottomText + mTargetStep,
                bottomTextX, getMeasuredHeight() / 2 + bottomTextY, bottomPaint);
    }

//    /**
//     * 设置百分比
//     *
//     * @param
//     */
//    public float setPercent(int currentStep, int targetStep) {
//
//        return percent;
//    }

    /**
     * 设置当前步数
     */
    public void setCurrentStep(int step) {
        mCurrentStep = step;

        percent = (float) mCurrentStep / mTargetStep;

        if (percent < 0) {
            percent = 0;
        } else if (percent > 1) {
            percent = 1;
            gradientColorArray = new int[]{
                    Color.parseColor("#ff823e"),
                    Color.parseColor("#ff823e")
            };
        }

        invalidate();
    }

    /**
     * 设置目标步数
     */
    public void setTargetStep(int step) {
        mTargetStep = step;
        invalidate();
    }

    /**
     * 设置上面文字
     */
    public void setTopText(String text) {
        mTopText = text;
    }
}
