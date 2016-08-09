package com.myframe.www;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.translationx_1)
    Button translationx1;
    @Bind(R.id.translationx_2)
    Button translationx2;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.ll1)
    LinearLayout ll1;
    @Bind(R.id.translationy_1)
    Button translationy1;
    @Bind(R.id.translationy_2)
    Button translationy2;
    @Bind(R.id.ll2)
    LinearLayout ll2;
    @Bind(R.id.frame_iv)
    ImageView frameIv;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AnimActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        translationx1.setOnClickListener(this);
        translationx2.setOnClickListener(this);
        iv.setOnClickListener(this);
        translationy1.setOnClickListener(this);
        translationy2.setOnClickListener(this);

        frameIv.setImageResource(R.drawable.frame_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) frameIv.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.translationx_1:
                ObjectAnimator.ofFloat(iv, "translationX", 0, 100).setDuration(200).start();
                break;
            case R.id.translationx_2:
                ObjectAnimator.ofFloat(iv, "translationX", 100, 0).setDuration(200).start();
                break;
            case R.id.iv:
                ToastUtils.showShort(this, "我被点了");
                break;
            case R.id.translationy_1:
                ObjectAnimator.ofFloat(iv, "translationY", 0, 100).setDuration(200).start();
                break;
            case R.id.translationy_2:
                ObjectAnimator.ofFloat(iv, "translationY", 100, 0).setDuration(200).start();
                break;
        }
    }
}
