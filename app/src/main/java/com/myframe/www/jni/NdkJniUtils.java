package com.myframe.www.jni;

/**
 * jni
 */
public class NdkJniUtils {

    static {
        System.loadLibrary("YanboberJniLibName");   //defaultConfig.ndk.moduleName
    }

    public native String getCLanguageString();
}
