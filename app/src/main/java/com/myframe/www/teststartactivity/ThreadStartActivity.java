package com.myframe.www.teststartactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class ThreadStartActivity extends BaseActivity {

    public static final String TAG = "ThreadStartActivity";
    @Bind(R.id.text1)
    TextView text1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ThreadStartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_start);
        ButterKnife.bind(this);

        L.v(TAG, "onCreate-1");

        L.e(TAG,"UI isMainThread:"+isMainThread1()+","+isMainThread2()+","+isMainThread3());

        //非Ui线程能否启动activity？
        new Thread(new Runnable() {
            @Override
            public void run() {
                L.e(TAG,"new Thread isMainThread:"+isMainThread1()+","+isMainThread2()+","+isMainThread3());

                Intent intent = new Intent(ThreadStartActivity.this, TargetActivity.class);
                startActivity(intent);
            }
        }).start();

//        Intent intent = new Intent(ThreadStartActivity.this,TargetActivity.class);
//        startActivity(intent);


        //这你妈肯定是主线程啊
        text1.post(new Runnable() {
            @Override
            public void run() {
                L.v(TAG,"threadd1:"+Thread.currentThread().getName());
            }
        });

        L.v(TAG, "threadd1-oncreate:" + Thread.currentThread().getName());

        //这也是主线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                L.v(TAG,"threadd2:"+Thread.currentThread().getName());
            }
        });
        L.v(TAG, "threadd2-oncreate:" + Thread.currentThread().getName());

        //这个就不是在主线程了呢
        HandlerThread thread = new HandlerThread("test");
        thread.start();
        Handler handler1 = new Handler(thread.getLooper());
        handler1.post(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    L.v(TAG,"threadd3:"+Thread.currentThread().getName());
                }
            }
        });

        for(int x=0;x<10;x++){
            L.v(TAG, "threadd3-oncreate:" + Thread.currentThread().getName());
        }
    }

    /**
     * 判断是否是主线程的三种方式
     */
    private boolean isMainThread1(){
        return Looper.getMainLooper() == Looper.myLooper();
    }
    private boolean isMainThread2(){
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
    private boolean isMainThread3(){
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.v(TAG, "onRestart-1");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.v(ThreadStartActivity.TAG, "onStart-1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.v(TAG, "onResume-1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.v(TAG, "onPause-1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.v(TAG, "onStop-1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.v(TAG, "onDestroy-1");
    }
}
