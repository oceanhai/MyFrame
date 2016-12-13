package com.myframe.www.testasynctask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AsynctaskActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.imageview01)
    ImageView imageview01;
    @Bind(R.id.imageview02)
    ImageView imageview02;

    private String imageviewUrl1 = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    private String imageviewUrl2 = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2352764292,448478286&fm=80&w=179&h=119&img.JPEG";
    private Executor exec = new ThreadPoolExecutor(15, 200, 10,
                                    TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AsynctaskActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void init() {
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                new MyAsyncTask().execute(imageviewUrl1, "imageview01");
                new MyAsyncTask().execute(imageviewUrl2, "imageview02");
                break;
            case R.id.btn02:
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl1, "imageview01");
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl2, "imageview02");
                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private String flag = null;

        @Override
        protected Bitmap doInBackground(String... params) {
            String urlStr = params[0];
            flag = params[1];
            //TODO 访问网络获取数据
            URL url = null;
            try {
                url = new URL(urlStr);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.connect();

                if(conn.getResponseCode() == 200){
                    InputStream inputStream = conn.getInputStream();
                    return BitmapFactory.decodeStream(inputStream);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if("imageview01".equals(flag)){
                imageview01.setImageBitmap(result);
            }else{
                imageview02.setImageBitmap(result);
            }
//            Bitmap bitmap = imageview01.getDrawingCache();
//            if(bitmap == null){
//                imageview01.setImageBitmap(result);
//            }else{
//                imageview02.setImageBitmap(result);
//            }
        }

    }
}
