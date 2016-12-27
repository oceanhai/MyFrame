package com.myframe.www.testfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Android解惑 - 为什么要用Fragment.setArguments(Bundle bundle)来传递参数
 * 而不建议用构造方法
 */
public class FragmentSetArgumentsActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FragmentSetArgumentsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_set_arguments);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01://构造方法传值fragment
                FragmentSetArguments1Activity.startActivity(this,"1");
                break;
            case R.id.btn02://SetArguments
                FragmentSetArguments1Activity.startActivity(this,"2");
                break;
        }
    }
}
