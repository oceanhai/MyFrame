package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.testenventbus.EnventbusActivity;

/**
 * 点9图
 */
public class DotNineActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DotNineActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_nine);
    }
}
