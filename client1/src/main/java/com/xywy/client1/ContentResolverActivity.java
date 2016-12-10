package com.xywy.client1;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContentResolverActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.query1)
    Button query1;
    @Bind(R.id.show)
    TextView show;
    @Bind(R.id.query2)
    Button query2;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.age)
    EditText age;
    @Bind(R.id._id)
    EditText Id;
    @Bind(R.id.path)
    EditText path;

    private ContentResolver resolver;
    private Cursor cursor;
    private String uriStr1 = "content://com.myframe.www.testcontentprovider.MyProvider";
    private String uriStr2 = "content://com.myframe.www.testcontentprovider.MyProvider2";
    private Uri uri;
    private StringBuilder stringBuilder = new StringBuilder();
    private StringBuilder stringBuilder2 = new StringBuilder();

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ContentResolverActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void init() {
        resolver = getContentResolver();
    }

    private void initListener() {
        query1.setOnClickListener(this);
        query2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query1:
                searchNoPath(null);
                break;
            case R.id.query2:
                searchNoPath(path.getText().toString());
                break;
        }
    }


    /**
     * 有/无path检索
     */
    private void searchNoPath(String path) {
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder2.delete(0, stringBuilder2.length());

        //将String类型uri转换为Uri类型的Uri
        if(!TextUtils.isEmpty(path)){
            uri = Uri.parse(uriStr2+"/"+path);
        }else{
            uri = Uri.parse(uriStr1);
        }

        String _id = Id.getText().toString();
        String nameStr = name.getText().toString();
        String ageStr = age.getText().toString();


        List<String> params = new ArrayList<>();
        if (!TextUtils.isEmpty(_id)) {
            stringBuilder.append("_id=?");
            params.add(_id);
        }
        if (!TextUtils.isEmpty(nameStr)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" and name=?");
            } else {
                stringBuilder.append("name=?");
            }
            params.add(nameStr);
        }
        if (!TextUtils.isEmpty(ageStr)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" and age=?");
            } else {
                stringBuilder.append("age=?");
            }
            params.add(ageStr);
        }

        int result = params.size();
        String[] selectionArgs = null;
        switch (result) {
            case 1:
                selectionArgs = new String[1];
                break;
            case 2:
                selectionArgs = new String[2];
                break;
            case 3:
                selectionArgs = new String[3];
                break;
        }
        for (int x = 0; x < params.size(); x++) {
            selectionArgs[x] = params.get(x);
        }

        cursor = resolver.query(uri, null, stringBuilder.toString(), selectionArgs, null);
        while (cursor.moveToNext()) {
            if(cursor.getColumnIndex("_id") != -1){
                stringBuilder2.append(cursor.getString(cursor.getColumnIndex("_id")) + ",");
            }
            if(cursor.getColumnIndex("name") != -1){
                stringBuilder2.append(cursor.getString(cursor.getColumnIndex("name")) + ",");
            }
            if(cursor.getColumnIndex("age") != -1){
                stringBuilder2.append(cursor.getString(cursor.getColumnIndex("age")) + "\n");
            }
        }
        show.setText(stringBuilder2.toString());
    }
}
