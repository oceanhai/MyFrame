package com.myframe.www.jni;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 参考 两个博客结合起来看
 * http://blog.csdn.net/yanbober/article/details/45309049/
 * http://blog.csdn.net/krubo1/article/details/50547681
 */
public class NdkJniActivity extends AppCompatActivity {

    @Bind(R.id.jni_show)
    TextView jniShow;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NdkJniActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk_jni);
        ButterKnife.bind(this);
        
        init();
    }

    private void init() {
        NdkJniUtils utils = new NdkJniUtils();
        jniShow.setText(utils.getCLanguageString());//显示jni信息
    }
}
