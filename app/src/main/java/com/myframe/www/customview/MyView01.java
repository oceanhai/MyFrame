package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view 01
 * https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/%5B02%5DCanvas_BasicGraphics.md
 */
public class MyView01 extends View {

    private Paint mPaint = new Paint();//画笔

    public MyView01(Context context) {
        super(context);
    }

    public MyView01(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化  画笔
     */
    private void initPaint() {
        mPaint.setColor(Color.BLACK);//设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置画笔模式为填充
        mPaint.setStrokeWidth(10f);//设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.BLUE);//绘制蓝色

//        canvas.drawPoint(200, 200, mPaint);//在坐标(200,200)位置绘制一个点
//        canvas.drawPoints(new float[]{     //绘制一组点，坐标位置由float数组指定
//                500,500,
//                500,600,
//                500,700
//        },mPaint);

//        canvas.drawLine(300,300,500,600,mPaint);// 在坐标(300,300)(500,600)之间绘制一条直线
//        canvas.drawLines(new float[]{           // 绘制一组线 每四数字(两个点的坐标)确定一条线
//                100,200,200,200,
//                100,300,200,300,
//        },mPaint);

//        /**
//         * 绘制矩形
//         */
//        //第一种
//        canvas.drawRect(100,100,800,400,mPaint);
//        // 第二种
//        Rect rect = new Rect(100,100,800,400);
//        canvas.drawRect(rect,mPaint);
//        // 第三种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawRect(rectF,mPaint);

//        /**
//         * 绘制圆角矩形
//         */
//        // 第一种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawRoundRect(rectF,30,30,mPaint);
//        // 第二种 在API21的时候才添加上
//        canvas.drawRoundRect(100,100,800,400,30,30,mPaint);


//        /**
//         * 绘制圆角矩形变椭圆
//         * 实际上在rx为宽度的一半，ry为高度的一半时，刚好是一个椭圆，通过上面我们分析的原理推算一下就能得到，而当rx大于宽度的一半，
//         * ry大于高度的一半时，实际上是无法计算出圆弧的，所以drawRoundRect对大于该数值的参数进行了限制(修正)，凡是大于一半的参数均按照一半来处理。
//         * ※上面文章说的不对只有是350,150的时候才能画出正确椭圆，大于之后并不会按照一半处理
//         */
//        // 矩形
//        RectF rectF = new RectF(100,100,800,400);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);
//        // 绘制圆角矩形
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRoundRect(rectF,350,150,mPaint);

//        /**
//         * 绘制椭圆
//         */
//        // 第一种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawOval(rectF,mPaint);
//        // 第二种 需要api21
//        canvas.drawOval(100,100,800,400,mPaint);

//        /**
//         * 绘制圆
//         */
//        canvas.drawCircle(500,500,400,mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。


//        /**
//         *  椭圆 弧度
//         * startAngle  // 开始角度
//         * sweepAngle  // 扫过角度
//         * useCenter   // 是否使用中心
//         */
//        RectF rectF = new RectF(0,0,400,200);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,0,90,false,mPaint);
//
//        RectF rectF2 = new RectF(0,300,400,500);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2,mPaint);
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF2,0,90,true,mPaint);

//        /**
//         * 正圆 弧度
//         */
//        RectF rectF = new RectF(100,100,300,300);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,0,90,false,mPaint);
//
//        RectF rectF2 = new RectF(100,400,300,600);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2,mPaint);
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF2,0,90,true,mPaint);

//        /**
//         * paint
//         * TODO 文章画圈 时候，感觉也不对，FILL和FILL_AND_STROKE效果好像是一样的
//         */
//        mPaint.setStrokeWidth(30f);
//        canvas.drawCircle(200, 200, 100, mPaint);
//
//        // 描边
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(200, 500, 100, mPaint);
//
//        // 描边加填充
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawCircle(200,800,100,mPaint);


        RectF rectF = new RectF(100,100,300,300);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(rectF, -90, 100, true, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF, 100, 225, true, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF, 225, 255, true, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 255, -90, true, mPaint);
    }
}
