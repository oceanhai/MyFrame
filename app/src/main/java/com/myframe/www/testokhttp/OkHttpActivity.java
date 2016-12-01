package com.myframe.www.testokhttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;
import www.wuhai.common.utils.ToastUtils;

public class OkHttpActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = "OkHttpActivity";

    @Bind(R.id.get)
    Button get;
    @Bind(R.id.post)
    Button post;
    @Bind(R.id.show_info)
    TextView showInfo;

    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showInfo.setText(msg.obj.toString());
//            ToastUtils.showShort(OkHttpActivity.this, "我是handler1");
        }
    };

    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ToastUtils.showShort(OkHttpActivity.this, "我是handler2");
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OkHttpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        get.setOnClickListener(this);
        post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get:
                okhttpGet();
                break;
            case R.id.post:

                break;
        }
    }

    private void okhttpGet() {
        OkHttpClient mokhttpclient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow")
                .build();
        Call call = mokhttpclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String resutlt = response.body().string();
                L.v(TAG, "response.body():" + resutlt);
                Message message = new Message();
                message.obj = resutlt;
//                handler1.obtainMessage(1,message);
                handler1.sendMessage(message);

//                handler2.obtainMessage();
                handler2.sendMessage(message);

                L.v(TAG, "handler1.looper：" + handler1.getLooper() + ",looper.MessageQueue：" + handler1.getLooper().getQueue());
                L.v(TAG, "handler2.looper："+handler2.getLooper()+",looper.MessageQueue："+handler2.getLooper().getQueue());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler1.removeCallbacksAndMessages(null);
        handler2.removeCallbacksAndMessages(null);
    }
}
