package com.myframe.www.widget.navigationmenu1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.myframe.www.R;
import com.myframe.www.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Fragment1 extends Fragment {

    @Bind(R.id.listview)
    ListViewForScrollView listview;

    private List<String> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            mDatas.add("我是条目"+i);
        }

        //这里只是显示出indicator传过来的title，可以在这里请求API，用来显示API返回来的数据
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mDatas);
        listview.setAdapter(adapter1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
