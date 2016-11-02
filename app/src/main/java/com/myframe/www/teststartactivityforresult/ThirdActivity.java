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

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.button1)
    Button button1;

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);

        button1.setOnClickListener(this);

        text = getIntent().getStringExtra("request_text_for_second");
        text1.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent i = new Intent();
                i.putExtra("request_text_for_third", text+"\n"+"从ThirdActivity再次传递到Main");
                setResult(Activity.RESULT_OK, i);
                finish();
                break;
        }
    }
}
