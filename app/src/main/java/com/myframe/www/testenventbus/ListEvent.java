package com.myframe.www.testenventbus;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */
public class ListEvent extends BaseEvent{

    private List<String> mData;

    public ListEvent(String eventStr, List<String> mData) {
        super(eventStr);
        this.mData = mData;
    }

    public List<String> getmData() {
        return mData;
    }

    public void setmData(List<String> mData) {
        this.mData = mData;
    }
}
