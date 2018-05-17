package com.myframe.www.retrofit.xywy.presenter.mine;

import com.myframe.www.retrofit.xywy.ServiceProvider;
import com.myframe.www.retrofit.xywy.interfaces.mine.ILoginContract;
import com.myframe.www.retrofit.xywy.model.LoginEntity;
import com.myframe.www.retrofit.xywy.presenter.BasePresent;
import com.myframe.www.retrofit.xywy.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.wuhai.common.utils.L;

/**
 * Created by wuhai on 2018/5/16.
 */

public class LoginPresenter extends BasePresent implements ILoginContract.Presenter{

    private ILoginContract.View mView;
    private ServiceProvider serviceProvider;

    public LoginPresenter(ILoginContract.View mView){
        this.mView = mView;
        serviceProvider = new ServiceProvider();
    }

    @Override
    public void login(String name, String password) {
        serviceProvider.login(name, password, new Callback<LoginEntity>() {
            @Override
            public void onResponse(Call<LoginEntity> call, Response<LoginEntity> response) {
                L.e(TAG,"onResponse:"+response.body().toString());
                LoginEntity entity = response.body();
                if("10000".equals(entity.getCode())){

                    //保存token为后续接口使用
                    SPUtils.put("token",entity.getData().getToken());
                    if(mView != null){
                        mView.loginSuccess();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginEntity> call, Throwable t) {
                L.e(TAG,"onFailure:"+t.getMessage());
                if(mView != null){
                    mView.loginFail();
                }
            }
        });
    }

    @Override
    public void getCode(String phone, String flag) {
        serviceProvider.getCode(phone, flag, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    L.e(TAG,"onResponse:"+response.body().string());
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int code = jsonObject.getInt("code");
                    if(code == 10000){
                        if(mView != null){
                            mView.getCodeSuccess();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                L.e(TAG,"onFailure:"+t.getMessage());
                if(mView != null){
                    mView.getCodeFail();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
