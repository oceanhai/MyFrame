package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.myframe.www.base.BaseActivity;

public class ColourImageViewActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ColourImageViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_image_view);
    }
}
