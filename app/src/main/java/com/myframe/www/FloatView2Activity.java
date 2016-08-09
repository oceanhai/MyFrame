package com.myframe.www;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.myframe.www.adapter.FloatItemAdapter;
import com.myframe.www.base.BaseActivity;
import com.myframe.www.view.ListViewFloatItemForScrollView;

import java.util.ArrayList;

/**
* listview 浮动item
* @author wuhai
* create at 2016/2/29 16:42
*/
public class FloatView2Activity extends BaseActivity {

    private ListView listView01;
    private ArrayList<String> mDatas;
    private ListViewFloatItemForScrollView observableScrollView;
    private TextView tv_title;

    public static void startActivity(Context context){
        Intent intent = new Intent(context,FloatView2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view2);

        tv_title=(TextView) findViewById(R.id.tv_title);
        initData();
        init();
    }

    private void initData() {
        mDatas=new ArrayList<String>();
        for (int i = 0; i < 32; i++) {
            mDatas.add("我是条目"+i);
        }
    }

    private void init() {
        listView01 = (ListView) findViewById(R.id.viewInListView);
        FloatItemAdapter adapter = new FloatItemAdapter(this);
        adapter.setData(mDatas);
        listView01.setAdapter(adapter);

        listView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                observableScrollView.setPosition(position);
                System.out.println("点击的位置：" + position);
                Toast.makeText(FloatView2Activity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                tv_title.setText("我是条目" + position);
            }
        });


        observableScrollView= (ListViewFloatItemForScrollView) findViewById(R.id.scroll_layout);
        LinearLayout mViewOutScroll = (LinearLayout) findViewById(R.id.viewOutScroll);
        mViewOutScroll.setClickable(true);//使固定view 消费点击事件
        observableScrollView.setFloatView(listView01, mViewOutScroll);//设置非浮动ListView和固定ItemView
        observableScrollView.setPosition(2);
        observableScrollView.setHorizontalFadingEdgeEnabled(false);//删除android ScrollView边界阴影方法方法
    }
}
