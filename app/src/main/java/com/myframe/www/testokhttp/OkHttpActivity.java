package com.myframe.www.testokhttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class OkHttpActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = "OkHttpActivity";

    @Bind(R.id.get)
    Button get;
    @Bind(R.id.post)
    Button post;
    @Bind(R.id.show_info)
    TextView showInfo;

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
                L.v(TAG, "response.body():" + response.body().toString());
                showInfo.setText(response.body().toString());
            }
        });
    }
}
