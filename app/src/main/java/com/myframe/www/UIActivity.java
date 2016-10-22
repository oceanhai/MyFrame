package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myframe.www.widget.stickyheaderlistview.util.DensityUtil;

import www.wuhai.common.utils.DensityUtils;


/**
 * 测试 l m h xh xxh 图片用
 */
public class UIActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UIActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        DensityUtils.getDensity(this);
        DensityUtils.getDpi(4.3);
        DensityUtils.getWindowInch(this);
        DensityUtils.getWindowInch();
    }
}
