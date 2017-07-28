package com.myframe.www.dagger2;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by wuhai on 2017/07/28 11:33.
 * 描述：
 */

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View mView;

    @Inject
    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getData() {
        try {
            Thread.sleep(3000);
            if(mView != null){
                mView.updateUI();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
