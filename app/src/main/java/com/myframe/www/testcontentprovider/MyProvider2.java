package com.myframe.www.testcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.myframe.www.testdb.DBDAO;

/**
 * 内容提供者 含路径
 */
public class MyProvider2 extends ContentProvider{

    private DBDAO dao = null;
    // 定义UriMatcher
    private static UriMatcher matcher = null;
    // 这个可以和name不一致的
    private static final String AUTHORITY = "com.myframe.www.testcontentprovider.MyProvider2";
    private static final int PERSONS = 1;
    private static final int PERSON_NAME = 2;
    private static final int PERSON_AGE = 3;
    private static final int PERSON_ID = 4;
    static {
        // 初始不匹配任何uri
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 向UriMatcher中添加Uri
		/*
		 * 参数1:认证 参数2:path 参数3:匹配成功的返回值
		 */
        matcher.addURI(AUTHORITY, "persons", PERSONS);
        //*来匹配任意文本
//        matcher.addURI(AUTHORITY, "persons/*", PERSON_NAME);
        //#表示数据id（#代表任意数字）
//        matcher.addURI(AUTHORITY, "persons/#", PERSON_AGE);
        matcher.addURI(AUTHORITY, "persons/id", PERSON_ID);
    }


    @Override
    public boolean onCreate() {
        dao = new DBDAO(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // 当内容解析者解析数据时，先用匹配器匹配，判断返回值，返回相应数据
        int result = matcher.match(uri);
        Cursor cursor = null;
        switch (result){
            case PERSONS:
                cursor = dao.queryAll();
                break;
            case PERSON_NAME:
                cursor = dao.query(new String[]{"name"}, null, null);
                break;
            case PERSON_AGE:
                cursor = dao.query(new String[]{"age"},null,null);
                break;
            case PERSON_ID:
                cursor = dao.query(new String[]{"_id"},null,null);
                break;
            default:
                throw new RuntimeException("内容解析者 检索路径不对");
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
