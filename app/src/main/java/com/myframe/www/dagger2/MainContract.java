package com.myframe.www.dagger2;

/**
 * Created by wuhai on 2017/07/28 10:08.
 * 描述：
 */

public interface MainContract {

    interface View{
        void updateUI();
    }

    interface Presenter{
        void getData();
    }
}
