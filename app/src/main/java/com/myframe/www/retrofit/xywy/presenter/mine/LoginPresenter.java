package com.myframe.www.retrofit.xywy.presenter.mine;

import com.myframe.www.retrofit.xywy.ServiceProvider;
import com.myframe.www.retrofit.xywy.interfaces.mine.ILoginContract;
import com.myframe.www.retrofit.xywy.model.LoginEntity;
import com.myframe.www.retrofit.xywy.presenter.BasePresent;

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
    public void onDestroy() {
        mView = null;
    }
}
