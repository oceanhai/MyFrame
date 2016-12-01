package com.myframe.www.testhandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class HandlerActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = "HandlerActivity";

    @Bind(R.id.sendToMain)
    Button sendToMain;
    @Bind(R.id.sendToSub)
    Button sendToSub;

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            L.e(TAG, "handler1:收到子线程发来的空消息");
        }
    };

    private Handler handler2;

    private Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            L.e(TAG, "handler3:收到子线程发来的空消息");
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, HandlerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);

        initListener();
        init();
    }

    private void init() {
        new MyThread("子线程").start();
    }

    private void initListener() {
        sendToMain.setOnClickListener(this);
        sendToSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendToMain:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Message message = handler1.obtainMessage();
//                        message.obj = "hehe";
                        handler1.sendEmptyMessageAtTime(1, 6000);
                        L.e(TAG, "handler1.looper：" + handler1.getLooper() + ",looper.MessageQueue：" + handler1.getLooper().getQueue());
                        handler3.sendEmptyMessageAtTime(1, 3000);
                        L.e(TAG, "handler3.looper：" + handler3.getLooper() + ",looper.MessageQueue：" + handler3.getLooper().getQueue());
                    }
                }).start();

                break;
            case R.id.sendToSub:
                handler2.sendEmptyMessageAtTime(1, 3000);
                break;
        }
    }

    class MyThread extends Thread{

        public MyThread(String threadname){
            super(threadname);
        }

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            handler2 = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    L.e(TAG, "收到主线程发来的空消息");
                }
            };
            L.e(TAG, "handler2.looper：" + handler2.getLooper() + ",looper.MessageQueue：" + handler2.getLooper().getQueue());
            Looper.loop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler1.removeCallbacksAndMessages(null);
        handler2.removeCallbacksAndMessages(null);
        handler3.removeCallbacksAndMessages(null);
    }
}
