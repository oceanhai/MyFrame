package com.xywy.okhttprequest.networkwrapper;

import com.xywy.component.datarequest.neworkWrapper.IDataResponse;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
public class OkhttpRequestWrapper {

    private OkhttpRequestWrapper(Builder builder){

    }

    public enum OkhttpRequestMethod{
        GET,
        POST
    }

    public static class Builder{
        private OkhttpRequestMethod mMethod;
        private String mUrl;//scheme：authority
        private String mPath;// path
        //用datarequest module的，其实这应该独立出来，这样网络请求框架就容易整体替换
        private IDataResponse mResponse;
        private Map<String, String> mGetParams;
        private Map<String, String> mPostParams;
        private Map<String, File> mFiles;

        public Builder(OkhttpRequestMethod method, String url, IDataResponse response){
            this.mMethod = method;
            this.mUrl = url;
            this.mResponse = response;
        }

        public Builder path(String path){
            this.mPath = path;
            return this;
        }

        public Builder getParams(Map<String,String> getParams){
            this.mGetParams = getParams;
            return this;
        }

        public Builder postParams(Map<String,String> postParams){
            this.mPostParams = postParams;
            return this;
        }

        public Builder files(Map<String, File> files){
            this.mFiles = files;
            return this;
        }

        public OkhttpRequestWrapper build(){
            return new OkhttpRequestWrapper(this);
        }
    }
}
