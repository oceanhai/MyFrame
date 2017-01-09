package com.myframe.www.testenventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

/**
 * 左右fragment
 */
public class Enventbus1Activity extends BaseActivity {

    public static final String TAG = Enventbus1Activity.class.getSimpleName();

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Enventbus1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enventbus1);
    }
}
