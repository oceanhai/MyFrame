package com.myframe.www.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.R;
import com.myframe.www.customview.view.HeaderTextGridView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomView3Activity extends AppCompatActivity {


    @Bind(R.id.pay_amount_detail)
    HeaderTextGridView payAmountDetail;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomView3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view3);
        ButterKnife.bind(this);

        payAmountDetail.setResource(new String[][]{{"", "还款前", "还款后"},
                {"借款本金", 100 + "元", 101 + "元"},
                {"借款费用", 200 + "元", 201 + "元"},
                {"逾期费用", 300 + "元", 301 + "元"}});
        payAmountDetail.startLight();
    }
}
