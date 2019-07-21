package com.myframe.www.contentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myframe.www.R;

import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class ContentProviderClientActivity extends AppCompatActivity {

    private final static String TAG = "ContentProvider";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ContentProviderClientActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_client);
        ButterKnife.bind(this);

    }

    public void click1(View v) {
//        String uriStr = "content://com.myframe.www.contentprovider.MyProvider";
        String uriStr = "content://com.qihoo360.byod.info";
        //将String类型uri转换为Uri类型的Uri
        Uri uri=Uri.parse(uriStr);
        // 获得内容解析者
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            L.e(TAG,"name="+name);
        }
    }

    public void click2(View v) {
        String uriStr = "content://com.myframe.www.contentprovider.MyProvider";
        // 将String类型uri转换为Uri类型的Uri
        Uri uri = Uri.parse(uriStr);
        Cursor c = getContentResolver().query(uri,
                new String[] { "name", "age" }, "_id=?", new String[] { "2" },
                null);
        while (c.moveToNext()) {
            String name = c.getString(0);
            int age = c.getInt(1);
            L.e(TAG,"name="+name+",age="+age);
        }
    }

    public void click3(View v) {
        String uriStr = "content://com.myframe.www.contentprovider.MyProvider2/persons";
        Uri uri = Uri.parse(uriStr);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            int age = cursor.getInt(2);
            L.e(TAG,"age="+age);
        }
    }

    public void click4(View v) {
        String uriStr = "content://com.myframe.www.contentprovider.MyProvider2/persons/name";
        Uri uri = Uri.parse(uriStr);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            L.e(TAG,"name="+name);
        }
    }

    public void click5(View v) {
        String uriStr = "content://com.myframe.www.contentprovider.MyProvider2/persons/123";
        Uri uri = Uri.parse(uriStr);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            int age = cursor.getInt(0);
            L.e(TAG,"age="+age);
        }
    }

}
