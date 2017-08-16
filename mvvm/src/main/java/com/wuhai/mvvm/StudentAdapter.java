package com.wuhai.mvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuhai.mvvm.databinding.ItemStudentBinding;

/**
 * Created by wuhai on 2017/08/15 17:40.
 * 描述：
 */

public class StudentAdapter extends BaseDataAdapter<Student>{

    private LayoutInflater inflater;

    public StudentAdapter(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemStudentBinding dataBinding;
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_student, parent, false);
        } else {
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setStu(mData.get(position));

        return  dataBinding.getRoot();
    }

}
