package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 内部类
 * 静态内部类，成员内部类，局部内部类，匿名内部类
 */
public class InnerClassActivity extends BaseActivity {

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

//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//            }
//        };
//
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ToastUtils
//            }
//        };
//
//        button1.setOnClickListener(listener);
    }
}
