package com.myframe.www.widget.stickyheaderlistview.view;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myframe.www.R;
import com.myframe.www.ShowEdittextActivity;
import com.myframe.www.widget.stickyheaderlistview.adapter.HeaderChannelAdapter;
import com.myframe.www.widget.stickyheaderlistview.model.ChannelEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.ToastUtils;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderChannelViewView extends HeaderViewInterface<List<ChannelEntity>> implements AdapterView.OnItemClickListener {

    @Bind(R.id.gv_channel)
    FixedGridView gvChannel;//GridViewForScrollView

    public HeaderChannelViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<ChannelEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_channel_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<ChannelEntity> list) {
        int size = list.size();

        if (size <= 4) {
            gvChannel.setNumColumns(size);
        } else if (size == 6) {
            gvChannel.setNumColumns(3);
        } else if (size == 8) {
            gvChannel.setNumColumns(4);
        } else {
            gvChannel.setNumColumns(4);
        }

        HeaderChannelAdapter adapter = new HeaderChannelAdapter(mContext, list);
        gvChannel.setAdapter(adapter);
        gvChannel.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.showShort(mContext, "点击了" + position);
        ShowEdittextActivity.startActivity(mContext);
    }
}
