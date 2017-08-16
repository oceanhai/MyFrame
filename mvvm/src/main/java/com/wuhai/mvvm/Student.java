package com.wuhai.mvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by wuhai on 2017/08/14 15:47.
 * 描述：
 */

public class Student extends BaseObservable{

    private String name;
    private String addr;
    private String photo;

    public Student() {
    }

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public Student(String name, String addr, String photo) {
        this(name,addr);
        this.photo = photo;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
        notifyPropertyChanged(BR.addr);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @BindingAdapter("photo")
    public static void getInternetImage(ImageView iv, String url) {
        Picasso.with(iv.getContext()).
                load(url).
                error(R.mipmap.weixin).
                into(iv);
    }
}
