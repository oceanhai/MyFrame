package com.myframe.www.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import www.wuhai.common.utils.L;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "retrofit";
    private Retrofit retrofit;

    public interface BlogService {
        @GET("blog/{id}") //这里的{id} 表示是一个变量
        Call<ResponseBody> getBlog(/** 这里的id表示的是上面的{id} */@Path("id") String id);
    }

    public interface BlogService2 {
        @GET("blog/{id}") //这里的{id} 表示是一个变量
        Observable<User> getBlog(/** 这里的id表示的是上面的{id} */@Path("id") String id);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        //创建Retrofit实例
        retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:80/")
                .baseUrl("http://192.168.10.205:80/")//※如果用模拟器的话不能用localhost
                .build();

        method01();
    }

    public interface GetRequest_Interface{
        Call<ResponseBody> testFormUrlEncoded1()
    }

    private void method04(){

    }

    private void method03(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void method02(){
        BlogService2 service2 = retrofit.create(BlogService2.class);
//        Call<Observable<User>> call = service2.getBlog("2.json");
    }

    /**
     * retrofit 简单实用
     */
    private void method01(){
        //代理对象
        BlogService service = retrofit.create(BlogService.class);

        Call<ResponseBody> call = service.getBlog("2.json");
        // 用法和OkHttp的call如出一辙
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = response.body().string();
                    L.e(TAG,"str:"+str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                L.e(TAG,"faile msg:"+t.getMessage());
            }
        });
    }
}
