package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.ToastUtils;

/**
 * 内部类
 * 静态内部类，成员内部类，局部内部类，匿名内部类
 */
public class InnerClassActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;

    private Handler handler;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InnerClassActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class);
        ButterKnife.bind(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ToastUtils.show(InnerClassActivity.this,"我接收到了消息传递", Toast.LENGTH_SHORT);
            }
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(InnerClassActivity.this,"点击了", Toast.LENGTH_SHORT);
            }
        };

//        button1.setOnClickListener(listener);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        handler.sendEmptyMessage(100);
                        handler.sendEmptyMessageDelayed(100,3000);
                    }
                }).start();
                break;
            case R.id.button3:
//                button1.setOnClickListener(listener);//TODO 这样就不能用了 局部属性
                break;
        }
    }
}
