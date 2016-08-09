package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.view.SelectAgeDialog;
import com.myframe.www.view.SelectRegionDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wuhai
 *         create at 2016/5/12 15:17
 */
public class WheelViewActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv01)
    TextView tv01;
    @Bind(R.id.tv02)
    TextView tv02;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, WheelViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        tv01.setOnClickListener(this);
        tv02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv01:
                SelectAgeDialog mSelectAgeDialog = new SelectAgeDialog(
                        this);
                mSelectAgeDialog.setPosition("岁","20");//初始化设置
                mSelectAgeDialog.show();
                mSelectAgeDialog
                        .setOnGetAgeListener(new SelectAgeDialog.OnGetAgeListener() {
                            @Override
                            public void onGetAge(String type, String data) {
                                tv01.setText(data + type);
                            }
                        });
                break;
            case R.id.tv02:
                SelectRegionDialog mSelectRegionDialog = new SelectRegionDialog(
                        this);
                mSelectRegionDialog.setPosition("黑龙江","齐齐哈尔");//初始化设置
                mSelectRegionDialog.show();
                mSelectRegionDialog
                        .setOnGetAgeListener(new SelectRegionDialog.OnGetAgeListener() {
                            @Override
                            public void onGetAge(String type, String data) {
                                tv01.setText(type+"-"+data);
                            }
                        });
                break;
        }
    }
}
