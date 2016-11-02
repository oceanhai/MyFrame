package com.myframe.www.teststartactivityforresult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.text1)
    TextView text1;

    private final int FIRST_REQUEST_CODE = 1;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FirstActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent i = new Intent(this,SecondActivity.class);
                i.putExtra("request_text_for_main", "从Main传递到SecondActivity");
                startActivityForResult(i, FIRST_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FIRST_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data != null) {
                text1.setText(data.getStringExtra("request_text_for_third"));
            }
        }
    }
}
