package com.myframe.www.widget.draggridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.myframe.www.R;
import com.myframe.www.RoundProgressBarActivity;
import com.myframe.www.utils.ToastUtils;
import com.myframe.www.widget.draggridview.framework.DragGridView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * @blog http://blog.csdn.net/xiaanming 
 *
 * @author xiaanming
 *
 */
public class DragGridViewActivity extends Activity implements AdapterView.OnItemClickListener {
	private List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();

	public static void startActivity(Context context) {
		Intent intent = new Intent(context, DragGridViewActivity.class);
		context.startActivity(intent);
	}

	private DragAdapter mDragAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag_grid_view);

		DragGridView mDragGridView = (DragGridView) findViewById(R.id.dragGridView);
		for (int i = 0; i < 15; i++) {
			HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
			itemHashMap.put("item_image",R.drawable.xingxing);
			itemHashMap.put("item_text", "拖拽 " + Integer.toString(i));
			dataSourceList.add(itemHashMap);
		}

		mDragAdapter = new DragAdapter(this, dataSourceList);

		mDragGridView.setAdapter(mDragAdapter);
		mDragGridView.setOnItemClickListener(this);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Object object = mDragAdapter.getItem(position);
		if(object instanceof HashMap){
			HashMap<String,Object> hashMap = (HashMap<String, Object>) object;
			String str = (String) hashMap.get("item_text");
			ToastUtils.showShort(this, "点击了第" + position + ":" + str);
		}

	}
}
