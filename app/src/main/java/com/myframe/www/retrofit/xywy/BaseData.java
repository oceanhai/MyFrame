package com.myframe.www.retrofit.xywy;

/**
 * 数据返回的基类
 * ※这是再返回值的基础上又套了一层 也就是说T data 是真正返回的数据体
 */
public class BaseData<T> {

    /**
     * 数据体
     */
    private int code = -1;
    private String msg;
    private T data;

    /**
     * TODO 这三个是？
     */
    private String tag;
    private int total;
    private boolean intermediate = false;//是否是中间数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public void setIntermediate(boolean intermediate) {
        this.intermediate = intermediate;
    }
}
