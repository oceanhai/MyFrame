package com.myframe.www.testenventbus;

/**
 * Created by wuhai on 2017/1/11.
 * 描述：基础事件
 */
public class BaseEvent {

    private String eventStr;

    public BaseEvent(){
    }

    public BaseEvent(String eventStr) {
        this.eventStr = eventStr;
    }

    public String getEventStr() {
        return eventStr;
    }

    public void setEventStr(String eventStr) {
        this.eventStr = eventStr;
    }
}
