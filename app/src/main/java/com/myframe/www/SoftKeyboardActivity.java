package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.myframe.www.base.BaseActivity;

/**
* 软键盘显隐
* @author wuhai
* create at 2016/2/29 11:27
*/
public class SoftKeyboardActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SoftKeyboardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_keyboard);
    }

    /**
     * 切换键盘状态
     * @param v
     */
    public void switchInput(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打开键盘
     * @param v
     */
    public void openInput(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(findViewById(R.id.editText1),InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 关闭键盘
     * @param v
     */
    public void closeInput(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
    }

}
