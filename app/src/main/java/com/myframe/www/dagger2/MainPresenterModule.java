package com.myframe.www.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wuhai on 2017/07/28 17:46.
 * 描述：
 */

@Module
public class MainPresenterModule {

    private MainContract.View mView;

    public MainPresenterModule(MainContract.View mView) {
        this.mView = mView;
    }

    @Provides
    MainContract.View provideView(){
        return mView ;
    }
}
