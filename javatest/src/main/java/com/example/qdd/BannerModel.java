package com.example.qdd;

/**
 * Created by wuhai on 2018/7/27.
 * 轮播图
 */

public class BannerModel {

    private String name;
    private String imageUri;
    private String htmlUrl;

    public BannerModel(String name, String imageUri, String htmlUrl) {
        this.name = name;
        this.imageUri = imageUri;
        this.htmlUrl = htmlUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public int hashCode() {
        return name.hashCode()+imageUri.hashCode()+htmlUrl.hashCode();
    }

}
