package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 只是一个输入框，记录下数据  测下 推送  切前台 是不是原封不动展示
 *
 * @author wuhai
 *         create at 2016/4/28 14:24
 */
public class ShowEdittextActivity extends BaseActivity {

    @Bind(R.id.editText1)
    EditText editText1;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ShowEdittextActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_edittext);
        ButterKnife.bind(this);
    }
}
