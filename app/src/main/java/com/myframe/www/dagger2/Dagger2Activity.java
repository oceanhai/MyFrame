package com.myframe.www.dagger2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myframe.www.MainActivity;
import com.myframe.www.R;
import com.myframe.www.testgreendao.GreenDaoActivity;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

public class Dagger2Activity extends AppCompatActivity {

    @Inject
    MainPresenter presenter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Dagger2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        DaggerMainPresenterComponent.builder().
                mainPresenterModule(new MainPresenterModule(this)).
                build().inject(this);
    }

}
