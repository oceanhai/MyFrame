#include "com_myframe_www_jni_NdkJniUtils.h"
/*
 * Class:     com_myframe_www_jni_NdkJniUtils
 * Method:    getCLanguageString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_myframe_www_jni_NdkJniUtils_getCLanguageString
        (JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env, "This just a test for Android Studio NDK JNI developer!");
}