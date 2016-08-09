package com.myframe.www;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.view.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

public class FloatView1Activity extends BaseActivity {
    private ListView listView01,listView02;
    private List<String> mDatas;
    private ObservableScrollView observableScrollView;

    public static void startActivity(Context context){
        Intent intent = new Intent(context,FloatView1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view1);
        initData();
        init();
    }

    private void initData() {
        mDatas=new ArrayList<String>();
        for (int i = 0; i < 16; i++) {
            mDatas.add("我是条目"+i);
        }
    }

    private void init() {
        listView01 = (ListView) findViewById(R.id.listview_01);
        listView02 = (ListView) findViewById(R.id.listview_02);
        observableScrollView= (ObservableScrollView) findViewById(R.id.scroll_layout);
        FrameLayout mViewOutScroll = (FrameLayout) findViewById(R.id.viewOutScroll);
        observableScrollView.setFloatView(findViewById(R.id.buy_read_layout), mViewOutScroll);//设置非浮动View和固定View
        observableScrollView.setHorizontalFadingEdgeEnabled(false);//删除android ScrollView边界阴影方法方法
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        listView01.setAdapter(adapter1);
        listView02.setAdapter(adapter2);



    }
}
