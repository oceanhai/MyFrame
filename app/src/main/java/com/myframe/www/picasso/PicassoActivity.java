package com.myframe.www.picasso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicassoActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.imageview01)
    ImageView imageview01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PicassoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);

        Picasso.with(this).
                load("https://www.baidu.com/img/bd_logo1.png").
                error(R.drawable.weixin).
                into(imageview01);
    }

}
