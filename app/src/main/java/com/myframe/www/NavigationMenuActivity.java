package com.myframe.www;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.navigationmenu1.NavigationMenu1Activity;
import com.myframe.www.widget.navigationmenu2.NavigationMenu2Activity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 导航菜单
 *
 * @author wuhai
 *         create at 2016/4/25 10:26
 */
public class NavigationMenuActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, NavigationMenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
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
            case R.id.btn01://ScrollerTabView
                NavigationMenu1Activity.startActivity(this);
                break;
            case R.id.btn02://SegmentedRadioGroup
                NavigationMenu2Activity.startActivity(this);
                break;
        }
    }

}
