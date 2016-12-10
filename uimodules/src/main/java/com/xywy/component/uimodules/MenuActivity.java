package com.xywy.component.uimodules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xywy.component.R;
import com.xywy.component.uimodules.menu.CustomContextMenu;
import com.xywy.component.uimodules.menu.MenuData;
import com.xywy.component.uimodules.popwindow.FloatingWindow;
import com.xywy.component.uimodules.popwindow.PositionCanChangePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 进来先看这里
 * PositionCanChangePopupWindow 随长按位置弹出删除  效果比较好 长按tv1 可看效果
 * FloatingWindow 打算弄个非随点击位置弹出的 ，效果暂时不太好  点击tv3 可看效果
 * CustomContextMenu  佳子的，大体知道如何实现，但还没入手改动布局啥的，可点击btn1和btn2看效果
 * btn1在屏幕最顶的时候，也是可以出现下弹的（跟PositionCanChangePopupWindow判断上下弹也许差不多）
 */
public class MenuActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    List<MenuData> menuData = new ArrayList<>();
    private FloatingWindow mFloatWindow;
    private PositionCanChangePopupWindow mPopup;
    private float downX, downY;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
//        tv1.setOnClickListener(this);
//        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        tv1.setOnLongClickListener(this);
//        tv2.setOnLongClickListener(this);
//        tv3.setOnLongClickListener(this);

        /**
         * btn01 这个参数可是布局中任意固定view，只是用来取getWindowToken()
         */
        mPopup = new PositionCanChangePopupWindow(this, btn01, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MenuActivity.this, "删除", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        menuData.clear();
        MenuData data = new MenuData();
        menuData.add(data);
        data.setText("添加");

        data = new MenuData();
        menuData.add(data);
        data.setText("删除");

        data = new MenuData();
        menuData.add(data);
        data.setText("更多...");

        switch (v.getId()) {
            case R.id.btn01:
                CustomContextMenu menu1 = new CustomContextMenu(this, btn01, new CustomContextMenu.Callback() {
                    @Override
                    public void onClick(MenuData data) {
                        Toast.makeText(MenuActivity.this, data.getText(), Toast.LENGTH_SHORT).show();
                    }
                }, menuData);
                menu1.show();
                break;
            case R.id.btn02:
                CustomContextMenu menu2 = new CustomContextMenu(this, btn02, new CustomContextMenu.Callback() {
                    @Override
                    public void onClick(MenuData data) {
                        Toast.makeText(MenuActivity.this, data.getText(), Toast.LENGTH_SHORT).show();
                    }
                }, menuData);
                menu2.show();
                break;
            case R.id.tv1:
                mFloatWindow = new FloatingWindow(this, v);
                mFloatWindow.show(0, 0, true);
                break;
            case R.id.tv3:
                mFloatWindow = new FloatingWindow(this, v);
                mFloatWindow.show(0, 0, true);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                mPopup.showPopupWindow(downX, downY);
                break;
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (mFloatWindow != null) {
                mFloatWindow.hide();// TODO 不随点击位置
            }
            mPopup.hide();// TODO 随点击位置
            downX = ev.getX();
            downY = ev.getY();
        }
        return super.dispatchTouchEvent(ev);
    }
}
