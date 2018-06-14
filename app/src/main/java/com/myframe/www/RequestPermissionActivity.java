package com.myframe.www;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RequestPermissionActivity extends AppCompatActivity implements View.OnClickListener {
    
    private static final String TAG = "RequestPermission";

    @Bind(R.id.request01)
    Button request01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RequestPermissionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);
        ButterKnife.bind(this);
        
        request01.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.request01:
                //检查权限
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "没有权限");
                    //申请授权
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                } else {

                    Log.e(TAG, "已经被授权");
                }
                break;
        }
    }

    //处理权限申请回调(写在Activity中)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1: {
                // 授权被允许
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "授权请求被允许");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //TODO 拨电话
                } else {
                    Log.e(TAG, "授权请求被拒绝");
                }
                return;
            }
        }
    }

    /**
     * 拨电话
     * @param mobile
     */
    private void callDirectly(String mobile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + mobile));
        this.startActivity(intent);
    }
}
