package com.xywy.component.uimodules.photoPicker;

import android.content.Context;
import android.os.AsyncTask;

import com.xywy.component.uimodules.photoPicker.model.PhotoFolderInfo;
import com.xywy.component.uimodules.utils.PhotoTools;

import java.util.Collections;
import java.util.List;

/**
 * Created by shijiazi on 16/1/14.
 * 异步 获取所有相册文件夹集合
 */
public class GetPhotoTask extends AsyncTask<Void, Integer, List<PhotoFolderInfo>> {

    private AsyncTaskCallback cb = null;
    private Context context;

    public GetPhotoTask(Context c, AsyncTaskCallback cb) {
        this.cb = cb;
        this.context = c.getApplicationContext();
    }

    @Override
    protected List<PhotoFolderInfo> doInBackground(Void... params) {
        List<PhotoFolderInfo> folders = Collections.EMPTY_LIST;
        folders = PhotoTools.getAllPhotoFolder(context);//获取所有相册文件夹集合
        return folders;
    }

    @Override
    protected void onPostExecute(List<PhotoFolderInfo> list) {
        super.onPostExecute(list);
        if (cb != null) {
            cb.onAsyncResult(list);
        }
        cb = null;
    }

    public interface AsyncTaskCallback {
        // 显示结果
        void onAsyncResult(List<PhotoFolderInfo> list);
    }

}

