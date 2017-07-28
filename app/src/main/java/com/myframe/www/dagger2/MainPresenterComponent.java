package com.myframe.www.dagger2;

import dagger.Component;

/**
 * Created by wuhai on 2017/07/28 14:29.
 * 描述：
 */

@Component(modules = MainPresenterModule.class)
public interface MainPresenterComponent {
    void inject(Dagger2Activity dagger2Activity);
}
