package com.xywy.component.uimodules;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.xywy.component.R;
import com.xywy.component.uimodules.photoPicker.model.PhotoInfo;

import java.util.List;

/**
 * 上传图片使用
 *
 * @author wuhai
 *         create at 2015/12/16 10:02
 */
public class QuestionPictureAdapter extends BaseAdapter {
    private Context mContext;
    private List<PhotoInfo> mDatas;

    public QuestionPictureAdapter(Context context) {
        this.mContext = context;
    }

    public void setDatas(List<PhotoInfo> datas) {
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_question_picture, parent, false);
            holder = new ViewHolder();
            holder.question_picture_imageview = (ImageView) convertView.findViewById(R.id.question_picture_imageview);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        final PhotoInfo photoInfo = mDatas.get(position);
        String path = "";
        if (photoInfo != null) {
            /**
             * 加载sd卡中的图片
             */
            path = ImageDownloader.Scheme.FILE.wrap(photoInfo.getPhotoPath());
        }
        PPImageLoader.getInstance().displayAlbumImage(path, holder.question_picture_imageview);

        return convertView;
    }

    static class ViewHolder {
        private ImageView question_picture_imageview;
    }

}
