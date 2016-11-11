package com.myframe.www.widget.swtichbutton;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.myframe.www.R;

public class MovableCheckboxActivity extends PreferenceActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MovableCheckboxActivity.class);
        context.startActivity(intent);
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        addPreferencesFromResource(R.xml.test4preference);
    }
}