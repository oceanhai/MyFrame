package com.myframe.www.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 材料设计
 */
public class MaterialDesignActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.navigation_view)
    Button navigationView;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MaterialDesignActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        navigationView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigation_view:
                NavigationViewActivity.startActivity(this);
                break;
        }
    }
}
