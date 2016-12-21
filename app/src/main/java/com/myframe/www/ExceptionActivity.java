package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 异常
 */
public class ExceptionActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn00)
    Button btn00;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ExceptionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        btn00.setOnClickListener(this);
        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn00:
                ExceptionActivity.startActivity(this);
                break;
            case R.id.btn01:
                int num = 100 / 0;
                break;
        }
    }
}
