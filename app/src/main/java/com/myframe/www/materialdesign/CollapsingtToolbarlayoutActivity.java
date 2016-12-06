package com.myframe.www.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.ToastUtils;

public class CollapsingtToolbarlayoutActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CollapsingtToolbarlayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsingt_toolbarlayout);
        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void init(){
        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle("我是课程");
    }

    private void initListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_action_button:
                ToastUtils.showShort(this, "浮动button被点了");
                break;
        }
    }
}
