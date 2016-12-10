package com.xywy.component.uimodules.photoPicker.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xywy.component.R;
import com.xywy.component.uimodules.PPImageLoader;
import com.xywy.component.uimodules.photoPicker.model.PhotoFolderInfo;
import com.xywy.component.uimodules.photoPicker.model.PhotoInfo;

import java.util.List;

/**
 * Created by shijiazi on 16/1/25.
 * 相册文件夹list    adapter
 */
public class FolderListAdapter extends ViewHolderAdapter<FolderListAdapter.FolderViewHolder, PhotoFolderInfo> {

    private PhotoFolderInfo mSelectFolder;

    public FolderListAdapter(Context context, List<PhotoFolderInfo> list) {
        super(context, list);
    }

    @Override
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = inflate(R.layout.pp_adapter_folder_list_item, parent);
        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FolderViewHolder holder, int position) {
        PhotoFolderInfo photoFolderInfo = getDatas().get(position);

        String path = "";
        PhotoInfo photoInfo = photoFolderInfo.getCoverPhoto();
        if (photoInfo != null) {
            path = "file:///" + photoInfo.getPhotoPath();
        }

        PPImageLoader.getInstance().displayAlbumImage(path, holder.mIvCover);

        holder.mTvFolderName.setText(photoFolderInfo.getFolderName());
        int size = 0;
        if (photoFolderInfo.getPhotoList() != null) {
            size = photoFolderInfo.getPhotoList().size();
        }
        holder.mTvPhotoCount.setText(getContext().getString(R.string.pp_folder_photo_size, size));

        if (mSelectFolder == photoFolderInfo || (mSelectFolder == null && position == 0)) {
            holder.mIvFolderCheck.setVisibility(View.VISIBLE);
        } else {
            holder.mIvFolderCheck.setVisibility(View.GONE);
        }
    }

    public PhotoFolderInfo getSelectFolder() {
        return mSelectFolder;
    }

    public void setSelectFolder(PhotoFolderInfo photoFolderInfo) {
        this.mSelectFolder = photoFolderInfo;
    }

    static class FolderViewHolder extends ViewHolderAdapter.ViewHolder {
        ImageView mIvCover;
        ImageView mIvFolderCheck;
        TextView mTvFolderName;
        TextView mTvPhotoCount;
        View mView;

        public FolderViewHolder(View view) {
            super(view);
            this.mView = view;
            mIvCover = (ImageView) view.findViewById(R.id.pp_iv_cover);
            mTvFolderName = (TextView) view.findViewById(R.id.pp_tv_folder_name);
            mTvPhotoCount = (TextView) view.findViewById(R.id.pp_tv_photo_count);
            mIvFolderCheck = (ImageView) view.findViewById(R.id.pp_iv_folder_check);
        }
    }
}
