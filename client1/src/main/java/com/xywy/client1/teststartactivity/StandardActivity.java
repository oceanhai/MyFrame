package com.xywy.client1.teststartactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xywy.client1.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.standard)
    Button standard;
    @Bind(R.id.singleTop)
    Button singleTop;
    @Bind(R.id.singleTask)
    Button singleTask;
    @Bind(R.id.singleInstance)
    Button singleInstance;
    @Bind(R.id.message)
    EditText message;
    @Bind(R.id.hint)
    TextView hint;
    @Bind(R.id.message_show)
    TextView messageShow;
    @Bind(R.id.num)
    TextView num;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, StandardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
        ButterKnife.bind(this);

        hint.setText("Standard模式");
        standard.setOnClickListener(this);
        singleTop.setOnClickListener(this);
        singleTask.setOnClickListener(this);
        singleInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.standard:
                StandardActivity.startActivity(this);
                break;
            case R.id.singleTop:
                SingleTopActivity.startActivity(this, message.getText().toString());
                break;
            case R.id.singleTask:
                SingleTaskActivity.startActivity(this, message.getText().toString());
                break;
            case R.id.singleInstance:
                Intent intent = new Intent();
                intent.setClassName("com.myframe.www",
                        "com.myframe.www.teststartactivityforresult.SingleInstanceActivity");
//                intent.setComponent(new ComponentName("com.myframe.www",
//                        "com.myframe.www.teststartactivityforresult.SingleInstanceActivity"));
                startActivity(intent);
                break;
        }
    }

}
