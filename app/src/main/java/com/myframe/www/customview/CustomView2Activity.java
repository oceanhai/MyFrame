package com.myframe.www.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.R;

public class CustomView2Activity extends AppCompatActivity {


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomView2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view2);
    }
}
