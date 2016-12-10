package com.xywy.component.uimodules.photoPicker.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.xywy.component.R;
import com.xywy.component.uimodules.PPImageLoader;
import com.xywy.component.uimodules.photoPicker.model.PhotoInfo;

import java.util.List;

/**
 * 相片集合 adapter
 */
public class PhotoListAdapter extends ViewHolderAdapter<PhotoListAdapter.PhotoViewHolder, PhotoInfo> {

    private int mScreenWidth;
    private List<PhotoInfo> mSelectPhotoList;//被选中的相片的集合
    private OnSelectListener mOnSelectListener;

    public PhotoListAdapter(Context context, List<PhotoInfo> list, List<PhotoInfo> list1, int screenWidth) {
        super(context, list);
        this.mScreenWidth = screenWidth;
        this.mSelectPhotoList = list1;
    }

    /**
     * 更新被选中的集合
     *
     * @param selectPhotoList
     */
    public void updateSelectPhotoList(List<PhotoInfo> selectPhotoList) {
        this.mSelectPhotoList = selectPhotoList;
    }

    /**
     * 父类getView中调用，创建ViewHolder
     *
     * @param parent
     * @param position
     * @return
     */
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = inflate(R.layout.pp_adapter_photo_list_item, parent);
        setHeight(view);
        return new PhotoViewHolder(view);
    }

    /**
     * 父类getView中调用，onCreateViewHolder方法之后 绑定ViewHolder
     *
     * @param holder
     * @param position
     * @return
     */
    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        final PhotoInfo photoInfo = getDatas().get(position);

        String path = "";
        if (photoInfo != null) {
            /**
             * 加载sd卡中的图片
             */
            path = ImageDownloader.Scheme.FILE.wrap(photoInfo.getPhotoPath());
        }

        /**
         * 根据图片配置（方法底层），展示图片
         */
        PPImageLoader.getInstance().displayAlbumImage(path, holder.mIvThumb);

        /**
         * 判断当前集合图片isSelected
         */
        if (photoInfo.isSelected()) {
            holder.mIvCheck.setImageResource(R.drawable.select_photo_selected);
        } else {
            holder.mIvCheck.setImageResource(R.drawable.select_photo_deselected);
        }

        /**
         * 选中的图片集合 含 展示图片集合中position位置的图片
         */
        if (mSelectPhotoList.contains(photoInfo)) {
            holder.mIvCheck.setImageResource(R.drawable.select_photo_selected);
            photoInfo.setIsSelected(true);
        } else {
            holder.mIvCheck.setImageResource(R.drawable.select_photo_deselected);
            photoInfo.setIsSelected(false);
        }

        /**
         * 选择对勾  点击监听
         */
        holder.mIvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSelectListener.onSelect(position, !photoInfo.isSelected(), holder.mIvCheck);
            }
        });

    }

    /**
     * 为布局设置宽度  1/3屏幕
     *
     * @param convertView
     */
    private void setHeight(final View convertView) {
        int height = mScreenWidth / 3;
        convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }

    /**
     * 设置回调
     *
     * @param onSelectListener
     */
    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    /**
     * 选择回调
     */
    public interface OnSelectListener {
        void onSelect(int position, boolean isSelected, ImageView mIvCheck);
    }

    public static class PhotoViewHolder extends ViewHolderAdapter.ViewHolder {

        public ImageView mIvThumb;//图片
        public ImageView mIvCheck;//是否选中icon

        public PhotoViewHolder(View view) {
            super(view);
            mIvThumb = (ImageView) view.findViewById(R.id.pp_iv_thumb);
            mIvCheck = (ImageView) view.findViewById(R.id.pp_iv_check);
        }
    }
}
