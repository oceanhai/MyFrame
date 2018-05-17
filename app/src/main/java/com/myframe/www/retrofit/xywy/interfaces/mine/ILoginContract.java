package com.myframe.www.retrofit.xywy.interfaces.mine;

import com.myframe.www.retrofit.xywy.interfaces.IBasePresenterInterFace;

/**
 * Created by wuhai on 2018/5/16.
 */

public interface ILoginContract {

    interface View {
        void loginSuccess();
        void loginFail();

        void getCodeSuccess();
        void getCodeFail();
    }

    interface Presenter extends IBasePresenterInterFace{

        void login(String name, String password);

        void getCode(String phone,String flag);
    }

}
