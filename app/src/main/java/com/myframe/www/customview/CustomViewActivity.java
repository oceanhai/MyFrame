package com.myframe.www.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.ToastUtils;

/**
 * 自定义控件 集合
 *
 * @author wuhai
 *         create at 2016/7/28 9:52
 */
public class CustomViewActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {

    @Bind(R.id.rippleButton)
    RippleButton rippleButton;
    @Bind(R.id.progressImage1)
    ProgressImage1 progressImage1;
    @Bind(R.id.progressImage2)
    ProgressImage2 progressImage2;
    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1001:
                    progressImage1.setProgress();
                    break;
            }
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);

        init();

        initListener();


    }

    private void init() {
//        handler = new Handler();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(500);
                    handler.sendEmptyMessage(1001);
                }
            }
        }).start();
    }

    private void initListener() {
        rippleButton.setOnClickListener(this);
        btn01.setOnLongClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rippleButton:
                /**
                 * 延迟2秒，right动画
                 */
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rippleButton.showRight();
                    }
                }, 2000);

                /**
                 * 延迟4秒，err动画
                 */
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rippleButton.showError();
                    }
                }, 4000);
                break;
            case R.id.btn02:
                CustomView1Activity.startActivity(this);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                ToastUtils.showShort(this, "长按监听");
                break;
        }
        return false;
    }
}