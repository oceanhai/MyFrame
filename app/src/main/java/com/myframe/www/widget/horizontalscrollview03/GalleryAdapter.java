package com.myframe.www.widget.horizontalscrollview03;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myframe.www.R;

public class GalleryAdapter extends
		RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{

	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	private LayoutInflater mInflater;
	private List<Integer> mDatas;

	public GalleryAdapter(Context context, List<Integer> datats)
	{
		mInflater = LayoutInflater.from(context);
		mDatas = datats;
	}

	public void setDatas(List<Integer> datats){
		mDatas = datats;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		public ViewHolder(View arg0)
		{
			super(arg0);
		}

		ImageView mImg;
		TextView mTxt;
	}

	@Override
	public int getItemCount()
	{
		return mDatas.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
		View view = mInflater.inflate(R.layout.widget_horizontal_scrollview_item,
				viewGroup, false);
		ViewHolder viewHolder = new ViewHolder(view);

		viewHolder.mImg = (ImageView) view
				.findViewById(R.id.id_index_gallery_item_image);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i)
	{
		viewHolder.mImg.setImageResource(mDatas.get(i));

		if (mOnItemClickLitener != null)
		{
			viewHolder.itemView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					mOnItemClickLitener.onItemClick(viewHolder.itemView, i);

					/**
					 * 点击图片 高度变高
					 */
					int mwidth = 160;
					int mheight = 180;
					RelativeLayout.LayoutParams imageBtnParams = new RelativeLayout.LayoutParams(mwidth , mheight);
					imageBtnParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
					viewHolder.mImg.setLayoutParams(imageBtnParams);
				}
			});

		}

	}

}
