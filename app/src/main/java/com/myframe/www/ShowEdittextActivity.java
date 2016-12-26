package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.只是一个输入框，记录下数据  测下 推送  切前台 是不是原封不动展示
 *
 * @author wuhai
 *         create at 2016/4/28 14:24
 */
public class ShowEdittextActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.editText1)
    EditText editText1;
    @Bind(R.id.refresh_ui)
    Button refreshUi;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ShowEdittextActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_edittext);
        ButterKnife.bind(this);

        initListener();

        new Thread(new Runnable() {
            @Override
            public void run() {
                editText1.setText("onCreate 内线程更新UI");
            }
        }).start();
    }

    private void initListener() {
        refreshUi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refresh_ui:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        editText1.setText("onresume后起线程更新UI");
                    }
                }).start();
                break;
        }
    }
}
