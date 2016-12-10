package com.xywy.component.uimodules.photoPicker;

import java.util.Collections;
import java.util.List;

/**
 * Created by shijiazi on 16/1/14.
 */
public class PhotoPickerConfig {

    public static final String PhotoPickerTag = "PhotoPicker";
    public static final int PERMISSIONS_CODE_GALLERY = 20001;

    /**
     * 图片选择的最大数
     */
    public static int MAX_SELECT_SIZE = 3;

    /**
     * 一次图片选择的最大数
     * 当外部已经存在一张图片时候，可手动变更这个数值3-1=2
     */
//    public static int CAN_SELECT_SIZE = 3;

    /**
     * 当前文件夹照片列表
     */
//    public static List<PhotoInfo> mCurPhotoList = new ArrayList<PhotoInfo>();

    //以下是需要配置的信息
    private List<String> filterList = Collections.EMPTY_LIST;//过滤器

    public List<String> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<String> filterList) {
        this.filterList = filterList;
    }
}
