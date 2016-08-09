package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.swipemenulistview.SwipeMenu;
import com.myframe.www.widget.swipemenulistview.SwipeMenuCreator;
import com.myframe.www.widget.swipemenulistview.SwipeMenuItem;
import com.myframe.www.widget.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
* ListView 侧滑销毁item
* @author wuhai
* create at 2016/6/6 11:36
*/
public class SwipemenulistviewActivity extends BaseActivity {

    @Bind(R.id.listview)
    SwipeMenuListView sListView;

    private List<String> mDatas;
    private MyAdapter adapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SwipemenulistviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipemenulistview);
        ButterKnife.bind(this);

        initData();

//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        adapter=new MyAdapter(this);
        sListView.setAdapter(adapter);

        initSwipeMenuListView();//初始化自定义侧滑ListView
    }

    private void initSwipeMenuListView() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                //创建一个侧滑菜单
                SwipeMenuItem item= new SwipeMenuItem(SwipemenulistviewActivity.this);
                item.setBackground(new ColorDrawable(Color.rgb(0xc9, 0xc9, 0xce)));//设置背景
                item.setIcon(R.drawable.icon_del);//设置图标
                item.setWidth(dp2px(80));//设置宽度

                menu.addMenuItem(item);//添加菜单
            }
        };

        sListView.setMenuCreator(creator);
        //侧滑菜单点击事件
        sListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        mDatas.remove(position);
//					adapter.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });

        //条目点击事件
        sListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(SwipemenulistviewActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mDatas=new ArrayList<String>();
        for (int i = 1; i <= 50; i++) {
            mDatas.add("我是条目"+i);
        }
    }

    //将dp转化为px
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    public class MyAdapter extends BaseAdapter {
        private Context mContext;

        public MyAdapter(Context context){
            this.mContext=context;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView= LayoutInflater.from(mContext).inflate(R.layout.item_swipemenulistview, null);
                holder=new ViewHolder();
                holder.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_title.setText(mDatas.get(position));
            return convertView;
        }

    }

    static class ViewHolder{
        private TextView tv_title;
    }
}
