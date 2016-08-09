package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.myframe.www.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
* 添加上下文菜单  及 标题菜单
* @author wuhai
* create at 2016/6/6 10:28
*/
public class MenuActivity extends BaseActivity {

    @Bind(R.id.lv)
    ListView lv;

    private List<String> mDatas;
    private int mPosition;
    private MyAdapter adapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        mDatas = new ArrayList<String>();
        initData();
        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
//				Toast.makeText(MainActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();
                mPosition = position;
                return false;
            }
        });
        registerForContextMenu(lv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_item, menu);
//		menu.add(Menu.NONE, 0, Menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                mDatas.remove(mPosition);
                adapter.notifyDataSetChanged();
                break;
            case R.id.add:
                mDatas.add("新娘子");
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void initData() {
        for (int i = 0; i < 50; i++) {
            mDatas.add("条目" + i);
        }

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv=(TextView) LayoutInflater.from(MenuActivity.this).inflate(android.R.layout.simple_list_item_1, null);
            tv.setText(mDatas.get(position));
            return tv;
        }

    }
}
