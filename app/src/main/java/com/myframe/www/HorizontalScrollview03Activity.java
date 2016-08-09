package com.myframe.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.horizontalscrollview03.GalleryAdapter;
import com.myframe.www.widget.horizontalscrollview03.MyRecyclerView;
/**
* 横向的Scrollview 联动图片Gallery 快速滑动时候也能联动
* @author wuhai
* create at 2016/2/17 14:02
*/
public class HorizontalScrollview03Activity extends BaseActivity
{

	private MyRecyclerView mRecyclerView;
	private RecyclerView mRecyclerView2;
	private GalleryAdapter mAdapter,mAdapter2;
	private List<Integer> mDatas;
	private ImageView mImg ;

	private View mCurrentView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_horizontal_scrollview03);
		
		mImg = (ImageView) findViewById(R.id.id_content);

		mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.a,
				R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
				R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.l));

//		mRecyclerView = (MyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
//		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//		mRecyclerView.setLayoutManager(linearLayoutManager);
//		mAdapter = new GalleryAdapter(this, mDatas);
//		mRecyclerView.setAdapter(mAdapter);
//
//		mRecyclerView.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
//			@Override
//			public void onChange(View view, int position) {
//				mImg.setImageResource(mDatas.get(position));
//			}
//		});
//
//		mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
//			@Override
//			public void onItemClick(View view, int position) {
////				Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT)
////						.show();
//				mImg.setImageResource(mDatas.get(position));
//			}
//		});

		/**
		 * 2是因为OnScrollListener不再是接口了，试图直接用RecyclerView实现
		 * 这样实现效果跟上面注释掉的是一样一样的，只是把实现拿到外面了，而且不用自定义MyRecyclerView（也不用在类里再定义个回调接口）
		 */
		mRecyclerView2 = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal2);
		LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
		linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView2.setLayoutManager(linearLayoutManager2);
		mAdapter2 = new GalleryAdapter(this, mDatas);
		mRecyclerView2.setAdapter(mAdapter2);
		mAdapter2.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
//				Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT)
//						.show();
				mImg.setImageResource(mDatas.get(position));
			}
		});
		mRecyclerView2.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				View newView = recyclerView.getChildAt(0);

				if (newView != null && newView != mCurrentView)
				{



					mCurrentView = newView ;
					mImg.setImageResource(mDatas.get(recyclerView.getChildPosition(mCurrentView)));
				}
			}
		});
	}

}
