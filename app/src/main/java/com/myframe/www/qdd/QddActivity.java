package com.myframe.www.qdd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.R;

import java.math.BigDecimal;

import www.wuhai.common.utils.L;

public class QddActivity extends AppCompatActivity {

    private static final String TAG = "qdd";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, QddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qdd);

        String str = getString(R.string.order_installment_money,
                new BigDecimal("1").setScale(2, BigDecimal.ROUND_HALF_UP));
        L.e(TAG,"str:"+str);
    }
}
