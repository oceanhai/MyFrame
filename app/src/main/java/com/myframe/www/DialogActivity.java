package com.myframe.www;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myframe.www.utils.DialogManager;
import com.myframe.www.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DialogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
    }

    private int select = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                final String[] arr = {"门店01","门店02","门店03","门店04",};
                DialogManager.showSingleChoiceDialog(this, R.string.title01, arr, 0, R.string.okResId, R.string.cancelResId, true, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        L.e("dialog","which="+which);
                        select = which;
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        L.e("dialog","which2="+which);
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                ToastUtils.showShort(DialogActivity.this,arr[select]);
                                break;
                        }
                    }
                });
                break;
        }
    }
}
