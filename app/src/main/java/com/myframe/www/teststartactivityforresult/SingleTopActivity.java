package com.myframe.www.teststartactivityforresult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingleTopActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.hint)
    TextView hint;
    @Bind(R.id.standard)
    Button standard;
    @Bind(R.id.singleTop)
    Button singleTop;
    @Bind(R.id.singleTask)
    Button singleTask;
    @Bind(R.id.singleInstance)
    Button singleInstance;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SingleTopActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        hint.setText("singleTop模式");
        standard.setOnClickListener(this);
        singleTop.setOnClickListener(this);
        singleTask.setOnClickListener(this);
        singleInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.standard:
                FirstActivity.startActivity(this);
                break;
            case R.id.singleTop:
                SingleTopActivity.startActivity(this);
                break;
            case R.id.singleTask:
                break;
            case R.id.singleInstance:
                break;
        }
    }
}
