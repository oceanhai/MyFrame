package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.myframe.www.base.BaseDataAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

/**
 * convertView 会创建一屏幕内"item数+1"个
 */
public class ListviewActivity extends AppCompatActivity {

    private static final String TAG = "ListviewActivity";

    @Bind(R.id.listview)
    ListView listview;

    private static int num;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ListviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        for (int x=0;x<50;x++){
            list.add("条目"+x);
        }

        MyAdapter adapter = new MyAdapter(this);
        listview.setAdapter(adapter);
        adapter.setData(list);

    }

    static class MyAdapter extends BaseDataAdapter<String>{


        public MyAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView= LayoutInflater.from(mContext).inflate(R.layout.list_item_card_detail, parent, false);
//                convertView= LayoutInflater.from(mContext).inflate(R.layout.item_floatview2_activity, parent, false);
                L.e(TAG, "convertView=" + convertView + ",num=" + (++num));
            }
            return convertView;
        }
    }
}
