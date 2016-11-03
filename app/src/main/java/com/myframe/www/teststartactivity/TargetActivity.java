package com.myframe.www.teststartactivity;

import android.os.Bundle;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import www.wuhai.common.utils.L;

public class TargetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        L.v(ThreadStartActivity.TAG, "onCreate-2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.v(ThreadStartActivity.TAG, "onRestart-2");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.v(ThreadStartActivity.TAG, "onStart-2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.v(ThreadStartActivity.TAG, "onResume-2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.v(ThreadStartActivity.TAG, "onPause-2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.v(ThreadStartActivity.TAG, "onStop-2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.v(ThreadStartActivity.TAG, "onDestroy-2");
    }
}
