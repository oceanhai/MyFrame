package com.myframe.www.yxt01;

import java.util.List;

/**
 * 品牌页 数据
 */
public class BrandData {
    private int code;
    private String msg;
    private DataEntity data;

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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
//        private List<String> categoryList;
//
//        public List<String> getCategoryList() {
//            return categoryList;
//        }
//
//        public void setCategoryList(List<String> categoryList) {
//            this.categoryList = categoryList;
//        }

        private List<PlasticCategoryModel> categoryList;

        public List<PlasticCategoryModel> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<PlasticCategoryModel> categoryList) {
            this.categoryList = categoryList;
        }
    }
}
