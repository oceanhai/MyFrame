package com.myframe.www;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    @Bind(R.id.sv_01)
    SimpleDraweeView sv01;
    @Bind(R.id.sv_02)
    SimpleDraweeView sv02;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        method02();
    }

    private void method02() {
        sv01.setImageURI("http://47.97.210.41/upload/image/20180831/FGQDFTCSPLUT0.jpg");
//        sv01.setImageURI("http://static.qiandaodao.cn/upload/image/20180831/FGQDFTCSPLUT0.jpg");
        sv02.setImageURI("https://www.baidu.com/img/bd_logo1.png?where=super");
    }

    /**
     * TODO 这种倒计时设计好繁琐啊 钱到到闪屏页用跳转倒计时
     */
    private int mDownTime1 = 6;

    public void method01() {
        va = new ValueAnimator().ofInt(0, 5);
        va.setDuration(5000);
        va.setEvaluator(new TypeEvaluator<Integer>() {
            @Override
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return 5 - (int) Math.floor(fraction * 5);
            }
        });
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /**
                 * 通过这样一个监听事件，我们就可以获取
                 * 到ValueAnimator每一步所产生的值。
                 *
                 * 通过调用getAnimatedValue()获取到每个时间因子所产生的Value。
                 * */
                if ((int) animation.getAnimatedValue() < mDownTime1) {
                    L.e(TAG, "value=" + (int) animation.getAnimatedValue());

                    mDownTime1 = (int) animation.getAnimatedValue();
                }
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isFinishing()) {
                    L.e(TAG, "animation=" + animation.toString());
                }
            }
        });
        va.start();
    }


    private ValueAnimator va;
    private int mDownTime = 4;

    public void method() {
        va = new ValueAnimator();
        va.setDuration(3000);
        va.setIntValues(3);
        va.setEvaluator(new TypeEvaluator<Integer>() {
            @Override
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return 3 - (int) Math.floor(fraction * 3);
            }
        });
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if ((int) animation.getAnimatedValue() < mDownTime) {
                    String text = "跳过" + (int) animation.getAnimatedValue();
                    SpannableString ss = new SpannableString(text);
                    ss.setSpan(new ForegroundColorSpan(Color.parseColor("#ffc513")), 2,
                            text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    L.e(TAG, "ss=" + ss.toString());

                    mDownTime = (int) animation.getAnimatedValue();
                }
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isFinishing()) {
                    L.e(TAG, "animation=" + animation.toString());
                }
            }
        });
        va.start();
    }
}
