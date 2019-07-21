package com.myframe.www.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContentProviderServerActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ContentProviderServerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_server);
        ButterKnife.bind(this);

        dao = new PersonDAO(this);

        btn01.setOnClickListener(this);
    }

    private PersonDAO dao;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                ContentValues values = new ContentValues();
                values.put("name","wuhai");
                values.put("age",32);
                dao.insert(values);
                break;
        }
    }
}
