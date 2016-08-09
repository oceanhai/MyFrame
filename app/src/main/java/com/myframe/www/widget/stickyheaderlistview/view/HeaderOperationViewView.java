package com.myframe.www.widget.stickyheaderlistview.view;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.myframe.www.R;
import com.myframe.www.ShowEdittextActivity;
import com.myframe.www.utils.ToastUtils;
import com.myframe.www.widget.stickyheaderlistview.adapter.HeaderOperationAdapter;
import com.myframe.www.widget.stickyheaderlistview.model.OperationEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderOperationViewView extends HeaderViewInterface<List<OperationEntity>> implements AdapterView.OnItemClickListener {

    @Bind(R.id.gv_operation)
    FixedGridView gvOperation;

    public HeaderOperationViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<OperationEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_operation_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<OperationEntity> list) {
        HeaderOperationAdapter adapter = new HeaderOperationAdapter(mContext, list);
        gvOperation.setAdapter(adapter);
        gvOperation.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.showShort(mContext, "点击了" + position);
        ShowEdittextActivity.startActivity(mContext);
    }
}
