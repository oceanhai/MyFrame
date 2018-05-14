package com.myframe.www.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myframe.www.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;
import www.wuhai.common.utils.L;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "retrofit";
    private Retrofit retrofit;

    public interface BlogService {
        @GET("blog/{id}") //这里的{id} 表示是一个变量
        Call<ResponseBody> getBlog(/** 这里的id表示的是上面的{id} */@Path("id") String id);

        @POST("blog")
        Call<Blog> createBlog(@Body Blog blog);

        @POST("/form")
        @FormUrlEncoded
        Call<ResponseBody> testFormUrlEncode1(@Field("username") String name, @Field("age") int age);

        @POST("/form")
        @FormUrlEncoded
        Call<ResponseBody> testFormUrlEncode2(@FieldMap Map<String,Object> map);

        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload1(@Part("name")RequestBody name,
                                           @Part("age")RequestBody age, @Part MultipartBody.Part file);

        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload2(@PartMap Map<String,ResponseBody> args,
                                           @Part MultipartBody.Part file);

        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload3(@PartMap Map<String,ResponseBody> args);
    }

    public interface BlogService2 {
        @GET("blog/{id}") //这里的{id} 表示是一个变量
        Observable<User> getBlog(/** 这里的id表示的是上面的{id} */@Path("id") String id);
    }

    public interface GetRequest_Interface{
        @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
        Call<Translation> getCall();
    }


    public interface PostRequest_Interface{
        // 参数说明
        // doctype：json 或 xml
        // jsonversion：如果 doctype 值是 xml，则去除该值，若 doctype 值是 json，该值为空即可
        // xmlVersion：如果 doctype 值是 json，则去除该值，若 doctype 值是 xml，该值为空即可
        // type：语言自动检测时为 null，为 null 时可为空。英译中为 EN2ZH_CN，中译英为 ZH_CN2EN，日译中为 JA2ZH_CN，中译日为 ZH_CN2JA，韩译中为 KR2ZH_CN，中译韩为 ZH_CN2KR，中译法为 ZH_CN2FR，法译中为 FR2ZH_CN
        // keyform：mdict. + 版本号 + .手机平台。可为空
        // model：手机型号。可为空
        // mid：平台版本。可为空
        // imei：???。可为空
        // vendor：应用下载平台。可为空
        // screen：屏幕宽高。可为空
        // ssid：用户名。可为空
        // abtest：???。可为空

        // 请求方式说明
        // 请求方式：POST
        // 请求体：i
        // 请求格式：x-www-form-urlencoded

        /**
         * 采用@Post表示Post方法进行请求（传入部分url地址）
         * 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
         * 需要配合@Field 向服务器提交需要的字段
         * @param targetSentence
         * @return
         */
        @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
        @FormUrlEncoded
        Call<Translation1> getCall(@Field("i") String targetSentence);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        //创建Retrofit实例
        retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:4567/")//这是在 E:\2018_qdd_demo\Retrofit2Demo 起的服务
                .baseUrl("http://192.168.1.110:4567/")//※如果用模拟器的话不能用localhost
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        method06();
    }

    //@Part
    private void method07(){


        MediaType textType = MediaType.parse("text/plain");
        RequestBody name = RequestBody.create(textType,"Carson");
        RequestBody age = RequestBody.create(textType,"24");
        RequestBody file = RequestBody.
                create(MediaType.parse("application/octet-stream"),"这里是摸你文件的内容");

        //@part
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file","test.txt",file);

        BlogService service = retrofit.create(BlogService.class);
        Call<ResponseBody> call3 = service.testFileUpload1(name,age,filePart);
        //TODO 有问题
//        ResponseBodyPrinter.printResponseBody(call3);

        // @PartMap
        // 实现和上面同样的效果
        Map<String, RequestBody> fileUpload2Args = new HashMap<>();
        fileUpload2Args.put("name", name);
        fileUpload2Args.put("age", age);
        //这里并不会被当成文件，因为没有文件名(包含在Content-Disposition请求头中)，但上面的 filePart 有
        //fileUpload2Args.put("file", file);
        //TODO 有问题
//        Call<ResponseBody> call4 = service.testFileUpload2(fileUpload2Args, filePart); //单独处理文件
        //TODO 有问题
//        ResponseBodyPrinter.printResponseBody(call4);
    }

    /**
     * https://blog.csdn.net/carson_ho/article/details/73732076
     * 实例2
     */
    private void method06(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);
        Call<Translation1> call = request.getCall("I love you");
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                L.e(TAG,"onResponse:"+response.body().toString());
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable t) {
                L.e(TAG,"onFailure:"+t.getMessage());
            }
        });
    }

    /**
     * https://blog.csdn.net/carson_ho/article/details/73732076
     * 实例1
     */
    private void method05(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                L.e(TAG,"onResponse:"+response.body().toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                L.e(TAG,"onFailure:"+throwable.getMessage());
            }
        });
    }

    private void method04(){
        BlogService service = retrofit.create(BlogService.class);
        Blog blog = new Blog();
        blog.content = "新建的Blog";
        blog.title = "测试";
        blog.author = "怪盗kidou";
        Call<Blog> call = service.createBlog(blog);
        call.enqueue(new Callback<Blog>() {
            @Override
            public void onResponse(Call<Blog> call, Response<Blog> response) {
                Blog result = response.body();
                L.e(TAG, result.toString());
            }

            @Override
            public void onFailure(Call<Blog> call, Throwable t) {
                L.e(TAG, t.getMessage());
            }
        });
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
