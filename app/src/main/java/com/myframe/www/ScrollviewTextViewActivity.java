package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollviewTextViewActivity extends BaseActivity {

    @Bind(R.id.textView01)
    TextView textView01;
    @Bind(R.id.scrollView01)
    ScrollView scrollView01;
    @Bind(R.id.editText01)
    EditText editText01;
    @Bind(R.id.scrollView02)
    ScrollView scrollView02;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ScrollviewTextViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_text_view);
        ButterKnife.bind(this);
    }
}
