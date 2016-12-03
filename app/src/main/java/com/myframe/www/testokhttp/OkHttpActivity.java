package com.myframe.www.testokhttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
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
    @Bind(R.id.multipart)
    Button multipart;

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showInfo.setText(msg.obj.toString());
//            ToastUtils.showShort(OkHttpActivity.this, "我是handler1");
        }
    };

    private Handler handler2 = new Handler() {
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
        multipart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get:
                okhttpGet();
                break;
            case R.id.post:
                okhttpPost();
                break;
            case R.id.multipart:
                okhttpMultipart();
                break;
        }
    }

    /**
     * get请求
     */
    private void okhttpGet() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                final String resultFail = request.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("fail"+resultFail);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String resutlt = response.body().string();
                L.v(TAG, "response.body():" + resutlt);
                Message message = new Message();
                message.obj = resutlt;
//                handler1.obtainMessage(1,message);//TODO 可删
//                handler1.sendMessage(message);

//                handler2.obtainMessage();//TODO 可删
//                handler2.sendMessage(message);

                L.v(TAG, "handler1.looper：" + handler1.getLooper() + ",looper.MessageQueue：" + handler1.getLooper().getQueue());
                L.v(TAG, "handler2.looper：" + handler2.getLooper() + ",looper.MessageQueue：" + handler2.getLooper().getQueue());

                //或者
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("success"+resutlt);
                    }
                });
            }
        });

//        try {
//            Response response =call.execute();//TODO 阻塞方式 response处理结果
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * post请求
     */
    private void okhttpPost() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("usermae", "胖大海");//参数

        final Request request = new Request.Builder()
                .url("https://github.com/oceanhai")
                .post(builder.build())
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                final String resultFail = request.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("fail"+resultFail);
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("success"+result);
                    }
                });
            }
        });
    }

    /**
     * 文件+post
     */
    private void okhttpMultipart() {
        OkHttpClient mOkHttpClient = new OkHttpClient();

        File file = new File(Environment.getExternalStorageDirectory(), "goldfallen.mp3");
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);

        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of("Content-Disposition","form-data; name='username'"),
                        RequestBody.create(null,"胖大海"))
                .addPart(Headers.of("Content-Disposition","form-data;name='mFile'; filename='goldfallen.mp3'"),
                        fileBody)
                .build();
        //http://localhost/okHttpServer/fileUpload
        Request request = new Request.Builder()
                .url("http://192.168.1.3:80/okHttpServer/fileUpload")
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                L.e(TAG,e.toString());
                final String resultFail = request.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("fail"+resultFail);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("success"+result);
                    }
                });
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
