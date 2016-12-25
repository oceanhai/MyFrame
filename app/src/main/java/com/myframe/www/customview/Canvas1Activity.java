package com.myframe.www.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.R;

/**
 * Canvas之画布操作
 */
public class Canvas1Activity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Canvas1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas1);
    }
}
