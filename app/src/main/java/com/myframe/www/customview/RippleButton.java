package com.myframe.www.customview;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;


/**
* 主要 用到 点击涟漪效果 其他效果可忽略
* @author wuhai
* create at 2016/7/28 9:59
*/
public class RippleButton extends Button{
    private float minRadius = 0f;
    private float maxRadius;
    private float centerX;
    private float centerY;
    private ValueAnimator rippleAnimator;//点击涟漪效果
    private Paint paint;
    private boolean isShowRipple = false;
    private float radius = 0f;
    private ValueAnimator rightShowAnimator;//right 属性动画
    private TranslateAnimation errorAnimator;//err 属性动画
    private float textDrawY = 0;
    private static final int DURATION = 500;
    private boolean isShowRight = false;
    private Handler handler;

    public RippleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.CENTER);
        handler = new Handler();
    }

    @Override
    protected void onDraw(Canvas canvas) {
      //  super.onDraw(canvas);
        initAnimator();
        paint.setTextSize(getTextSize());
        Drawable drawable = getBackground();
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
        paint.setColor(getCurrentTextColor());

        if(isShowRight){
            canvas.drawText(getText().toString(), getWidth()/2, getHeight()/2+getTextSize()/2-textDrawY, paint);//
            canvas.drawText("√", getWidth()/2, getHeight()+getTextSize()*3/2-textDrawY, paint);
        }else{
            canvas.drawText(getText().toString(), getWidth()/2, getHeight()/2+getTextSize()/2, paint);//点击前效果
        }

        if(isShowRipple){
            paint.setColor(Color.parseColor("#7fffffff"));
            canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);//radius 帧动画回调，直到radius>maxRadius(宽/2)停止绘圈
        }


    }
    private void initAnimator(){

        /**
         * 回调  绘圈r(实现涟漪效果)
         */
        if(rippleAnimator == null){
            maxRadius = getWidth()/2;
            //TODO 还是不太懂 两个参数就能实现个动画效果 是不是结合实现呢
            rippleAnimator = ValueAnimator.ofFloat(minRadius,maxRadius);//初始值与结束值之间的平滑过度
            rippleAnimator.setDuration(DURATION);
            rippleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    radius = (float) animation.getAnimatedValue();//每个动画帧中被调用
                    if (radius >= maxRadius) {
                        isShowRipple = false;
                    }
                    invalidate();
                }
            });

            rightShowAnimator = ValueAnimator.ofFloat(0,getHeight()/2+getTextSize());
            rightShowAnimator.setDuration(DURATION);
            rightShowAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    textDrawY = (float) animation.getAnimatedValue();//利用每一帧返回的值，可以做一些改变View大小，坐标，透明度等等操作
                    if(textDrawY >= getHeight()/2+getTextSize()){
                            isShowRight = false;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    rightShowAnimator.reverse();//反方向执行
                                }
                            },500);
                    }else{
                        isShowRight = true;
                        invalidate();
                    }
                }
            });

            /**
             * 左右 浮动 属性动画
             */
            errorAnimator = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0
            ,Animation.RELATIVE_TO_SELF,0.05f,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF
            ,0);
            errorAnimator.setDuration(100);
            errorAnimator.setRepeatCount(4);//重复次数
            errorAnimator.setRepeatMode(Animation.REVERSE);//设置反方向执行
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isShowRipple = true;
        rippleAnimator.start();
        return super.onTouchEvent(event);
    }

    public void showRight(){
        rightShowAnimator.start();
    }

    public void showError(){
        startAnimation(errorAnimator);
    }
}
