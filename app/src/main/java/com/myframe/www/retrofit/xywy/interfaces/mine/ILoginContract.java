package com.myframe.www.retrofit.xywy.interfaces.mine;

import com.myframe.www.retrofit.xywy.interfaces.IBasePresenterInterFace;

import java.io.File;

/**
 * Created by wuhai on 2018/5/16.
 */

public interface ILoginContract {

    interface View {
        void loginSuccess();
        void loginFail();

        void getCodeSuccess();
        void getCodeFail();

        void uploadImgSuccess();
        void uploadImgFail();
    }

    interface Presenter extends IBasePresenterInterFace{

        void login(String name, String password);

        void getCode(String phone,String flag);

        void uploadImg(File file);
    }

}
