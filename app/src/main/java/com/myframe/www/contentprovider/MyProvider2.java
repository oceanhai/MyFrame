package com.myframe.www.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 含路径
 */
public class MyProvider2 extends ContentProvider {

	private SQLiteDatabase db;
	// 定义UriMatcher
	private static UriMatcher MATCHER = null;
	private static final String AUTHORITY = "com.myframe.www.contentprovider.MyProvider2";// 这个可以和name不一致的
	private static final int PERSONS = 1;
	private static final int PERSON_NAME = 2;
	private static final int PERSON_AGE = 3;
	private static final int PERSON_ID = 4;
	static {
		MATCHER = new UriMatcher(UriMatcher.NO_MATCH);// 初始不匹配任何uri
		// 向UriMatcher中添加Uri
		/*
		 * 参数1:认证 参数2:path 参数3:匹配成功的返回值
		 */
		MATCHER.addURI(AUTHORITY, "persons", PERSONS);
		// MATCHER.addURI(AUTHORITY, "persons/#", PERSON_AGE);
		// MATCHER.addURI(AUTHORITY, "persons/*", PERSON_NAME);
		MATCHER.addURI(AUTHORITY, "persons/id", PERSON_ID);
	}

	@Override
	public boolean onCreate() {
		MyHelper helper = new MyHelper(getContext());
		db = helper.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// 当内容解析者解析数据时，先用匹配器匹配，判断返回值，返回相应数据
		int result = MATCHER.match(uri);
		System.out.println("result:" + result);
		Cursor cursor = null;
		switch (result) {
		case PERSONS:
			cursor = db.query("person", null, null, null, null, null, null);
			break;
		case PERSON_AGE:
			cursor = db.query("person", new String[] { "age" }, null, null,
					null, null, null);
			break;
		case PERSON_NAME:

			// cursor = db.query("person", new String[] { "name" }, null, null,
			// null, null,
			// null);
			cursor = null;
			break;
		case PERSON_ID:
			cursor = db.query("person", new String[] { "_id" }, null, null,
					null, null, null);
			break;

		default:
			throw new RuntimeException("你是🐷");
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {

		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		return 0;
	}

}
