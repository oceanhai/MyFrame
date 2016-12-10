package com.xywy.component.uimodules.photoPicker.model;

import java.io.Serializable;

/**
 * 图片信息
 */
public class PhotoInfo implements Serializable {

    public boolean isSelected = false;//是否被选中
    public String netWorkUrl;//网络图片
    public boolean isUpdate = false;
    private int photoId;
    private String photoPath;
    private int width;
    private int height;

    public PhotoInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoInfo photoInfo = (PhotoInfo) o;

        if (photoId != photoInfo.photoId) return false;
        if (width != photoInfo.width) return false;
        if (height != photoInfo.height) return false;
        return !(photoPath != null ? !photoPath.equals(photoInfo.photoPath) : photoInfo.photoPath != null);

    }

    @Override
    public int hashCode() {
        int result = photoId;
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getNetWorkUrl() {
        return netWorkUrl;
    }

    public void setNetWorkUrl(String netWorkUrl) {
        this.netWorkUrl = netWorkUrl;
    }
}
