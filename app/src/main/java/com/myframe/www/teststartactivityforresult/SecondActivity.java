package com.myframe.www.teststartactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.button1)
    Button button1;

    private final int SECOND_REQUEST_CODE = 2;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        L.e("onActivityResult","second-onCreate");

        button1.setOnClickListener(this);

        if (getIntent().getStringExtra("request_text_for_main") != null) {
            text = getIntent().getStringExtra("request_text_for_main");
            text1.setText(text);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e("onActivityResult","second-onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e("onActivityResult","second-onResume");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
//                i.putExtra("request_text_for_second", text+"\n"+"从SecondActivity传递到ThirdActivity");
                i.putExtra("request_text_for_second", "从SecondActivity传递到ThirdActivity");
                startActivityForResult(i, SECOND_REQUEST_CODE);
                finish();//销毁了，所以从第三个activity回传不到第一个activity了
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.e("onActivityResult","second:resultCode="+resultCode+",resultCode="+resultCode);
        if(requestCode == SECOND_REQUEST_CODE && data != null){
            setResult(Activity.RESULT_OK, data);
            L.e("onActivityResult","second:"+data.getStringExtra("request_text_for_third"));
            finish();
        }
    }
}
