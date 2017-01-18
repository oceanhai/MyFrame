package com.myframe.www.testormlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrmliteActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OrmliteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite);
        ButterKnife.bind(this);
        
        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn01:
                addTheme();
                break;
        }
    }

    private void addTheme() {
        Theme theme = new Theme();
        // 赋值
        theme.id = "1";
        theme.title = "主题";
        theme.detail = "主题详情";
        new ThemeDao(this).add(theme);
    }
}
