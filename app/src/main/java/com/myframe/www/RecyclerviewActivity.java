package com.myframe.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerviewActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.btn03)
    Button btn03;
    @Bind(R.id.btn04)
    Button btn04;
    @Bind(R.id.btn05)
    Button btn05;
    @Bind(R.id.btn06)
    Button btn06;
    @Bind(R.id.btn07)
    Button btn07;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
        btn06.setOnClickListener(this);
        btn07.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn01://实现横向listview效果
                intent.setClass(this, HorizontalScrollview01Activity.class);
                startActivity(intent);
                break;
            case R.id.btn02://实现横向listview效果，画廊（move）
                intent.setClass(this, HorizontalScrollview02Activity.class);
                startActivity(intent);
                break;
            case R.id.btn03://实现横向listview效果，画廊（快速滑动也联动）
                intent.setClass(this, HorizontalScrollview03Activity.class);
                startActivity(intent);
                break;
            case R.id.btn04://实现横向listview效果，长按拖拽移动位置
                intent.setClass(this, HorizontalScrollview04Activity.class);
                startActivity(intent);
                break;
            case R.id.btn05://待定
                Toast.makeText(this, "我是btn05", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn06://待定
                Toast.makeText(this, "我是btn06", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn07://待定
                Toast.makeText(this, "我是btn07", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
