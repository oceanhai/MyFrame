package com.myframe.www.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CountDownActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.tv01)
    TextView tv01;
    @Bind(R.id.tv02)
    TextView tv02;
    @Bind(R.id.tv03)
    TextView tv03;

    private CountDownTimeUtil countDownTimeUtil1;
    private CountDownTimeUtil countDownTimeUtil2;
    private CountDownTimeUtil countDownTimeUtil3;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CountDownActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        ButterKnife.bind(this);

        tv01.setOnClickListener(this);
        tv02.setOnClickListener(this);
        tv03.setOnClickListener(this);

        countDownTimeUtil1 = new CountDownTimeUtil(60 * 1000, 1000, tv01, CountDownTimeUtil.DYNAMIC_LOGIN);
        countDownTimeUtil2 = new CountDownTimeUtil(4 * 1000, 1000, tv02, CountDownTimeUtil.JUMP);
        countDownTimeUtil3 = new CountDownTimeUtil(60 * 1000, 1000, tv03, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv01:
                countDownTimeUtil1.start();
                break;
            case R.id.tv02:
                countDownTimeUtil2.start();
                break;
            case R.id.tv03:
                countDownTimeUtil3.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimeUtil1 != null){
            countDownTimeUtil1.cancel();
        }
        if(countDownTimeUtil2 != null){
            countDownTimeUtil2.cancel();
        }
        if(countDownTimeUtil3 != null){
            countDownTimeUtil3.cancel();
        }
    }
}
