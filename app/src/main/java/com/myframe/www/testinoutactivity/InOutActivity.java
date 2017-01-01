package com.myframe.www.testinoutactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;
import com.myframe.www.teststartactivityforresult.FirstActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InOutActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.btn03)
    Button btn03;
    @Bind(R.id.btn04)
    Button btn04;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InOutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                FirstActivity.startActivity(this, "left in right out");
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.btn02:
                break;
            case R.id.btn03:
                break;
            case R.id.btn04:
                break;
        }
    }
}
