package com.myframe.www.widget.slidingfinish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myframe.www.FloatViewActivity;
import com.myframe.www.MainActivity;
import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

public class SlidingFinishActivity extends BaseActivity implements View.OnClickListener {

    public static void startActivity(Context context){
        Intent intent = new Intent(context,SlidingFinishActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_finish);

        Button mButtonNormal = (Button) findViewById(R.id.normal_activity);
        mButtonNormal.setOnClickListener(this);

        Button mButtonAbs = (Button) findViewById(R.id.absListview_activity);
        mButtonAbs.setOnClickListener(this);

        Button mButtonScroll = (Button) findViewById(R.id.scrollview_activity);
        mButtonScroll.setOnClickListener(this);

        Button mButtonViewPager = (Button) findViewById(R.id.viewpager_activity);
        mButtonViewPager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()) {
            case R.id.normal_activity:
                mIntent = new Intent(SlidingFinishActivity.this, NormalActivity.class);
                break;
            case R.id.absListview_activity:
                mIntent = new Intent(SlidingFinishActivity.this, AbsActivity.class);
                break;
            case R.id.scrollview_activity:
                mIntent = new Intent(SlidingFinishActivity.this, ScrollActivity.class);
                break;
            case R.id.viewpager_activity:
                mIntent = new Intent(SlidingFinishActivity.this, ViewPagerActivity.class);
                break;
        }

        startActivity(mIntent);
    }
}
