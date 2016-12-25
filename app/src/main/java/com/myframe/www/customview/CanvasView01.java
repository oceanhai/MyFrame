package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas之画布操作
 * 参考
 * https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/%5B03%5DCanvas_Convert.md
 */
public class CanvasView01 extends View{

    private Paint mPaint;
    private int mWidth,mHeight;

    public CanvasView01(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * (1)位移(translate)
         */
//        // 在坐标原点绘制一个黑色圆形
//        mPaint.setColor(Color.BLACK);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);
//
//        // 在坐标原点绘制一个蓝色圆形
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);

        /**
         * (2.1)缩放(scale)
         */
//        // 将坐标系原点移动到画布正中心
//        canvas.translate(mWidth/2, mHeight / 2);
//        RectF rectF = new RectF(0, -400, 400, 0);   // 矩形区域
//
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.BLACK);               // 绘制黑色矩形
//        canvas.drawRect(rectF, mPaint);
//
////        canvas.scale(0.5f, 0.5f);                   // 画布缩放
////        canvas.scale(0.5f, 0.5f, 200, 0);           // 画布缩放  <-- 缩放中心向右偏移了200个单位
////        canvas.scale(-0.5f, -0.5f);                 // 画布缩放 反转
//        canvas.scale(-0.5f, -0.5f, 200, 0);         // 画布缩放 反转<-- 缩放中心向右偏移了200个单位
//
//        mPaint.setColor(Color.BLUE);                // 绘制蓝色矩形
//        canvas.drawRect(rectF, mPaint);

        /**
         * (2.2)缩放(scale)
         */
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(4);
//        canvas.translate(mWidth/2, mHeight / 2);
//        RectF rectF = new RectF(-300, -300, 300, 300);
//
//        for(int x=0;x<=10;x++){
//            canvas.scale(0.9f,0.9f);
//            canvas.drawRect(rectF, mPaint);
//        }

        /**
         * (3.1)旋转(rotate)
         */
//        mPaint.setStyle(Paint.Style.STROKE);
//        // 将坐标系原点移动到画布正中心
//        canvas.translate(mWidth / 2, mHeight / 2);
//        RectF rect = new RectF(0,-400,400,0);   // 矩形区域
//
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect, mPaint);
//
////        canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点
//        canvas.rotate(180, 200, 0);             // 旋转180度 <-- 旋转中心向右偏移200个单位
//
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rect, mPaint);

        /**
         * (3.2)旋转(rotate)
         */
//        mPaint.setStyle(Paint.Style.STROKE);
//        // 将坐标系原点移动到画布正中心
//        canvas.translate(mWidth / 2, mHeight / 2);
//
//        // TODO 这条线并不跟着旋转，也就是说已经绘制好的不随画布而旋转
//        canvas.drawLine(0,420,40,420,mPaint);//绘制一条线
//
//        canvas.drawCircle(0,0,400,mPaint);          // 绘制两个圆形
//        canvas.drawCircle(0,0,380,mPaint);
//        for(int i=0;i<=180;i+=10){
//            canvas.drawLine(0,380,0,400,mPaint);
//            canvas.rotate(10);
//        }

        /**
         * (4.1)错切(skew)
         */
//        // 将坐标系原点移动到画布正中心
//        canvas.translate(mWidth / 2, mHeight / 2);
//        RectF rect = new RectF(0,0,100,100);    // 矩形区域
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect, mPaint);
//        //TODO 出现的效果跟讲解的不一样，下面没有封口 平行四边形
//        canvas.skew(1, 0);                       // 水平错切 <- 45度  tan45度=1
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rect, mPaint);

        /**
         * (4.2)错切(skew)
         */
        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rect = new RectF(0,0,100,100);   // 矩形区域
        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);
        //TODO 出现的效果跟讲解的不一样，下面没有封口 平行四边形
        canvas.skew(1,0);                       // 水平错切
        canvas.skew(0,1);                       // 垂直错切
        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);

        /**
         * 常用格式
         虽然关于状态的保存和回滚啰嗦了不少，不过大多数情况下只需要记住下面的步骤就可以了：
         save();      //保存状态
         ...          //具体操作
         restore();   //回滚到之前的状态
         */
    }
}
