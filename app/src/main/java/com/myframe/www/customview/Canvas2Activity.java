package com.myframe.www.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Canvas之图片文字
 */
public class Canvas2Activity extends AppCompatActivity {

    @Bind(R.id.checkview)
    CheckView checkview;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Canvas2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas2);
        ButterKnife.bind(this);

        checkview.check();
    }
}
