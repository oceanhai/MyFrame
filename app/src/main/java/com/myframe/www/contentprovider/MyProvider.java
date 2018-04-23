package com.myframe.www.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 不含路径
 */
public class MyProvider extends ContentProvider {

	private SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		MyHelper helper=new MyHelper(getContext());
		db = helper.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		System.out.println("内容解析者通过Uri调用内容提供者的方法");
		// Cursor c = db.query("person", new String[] { "name" }, null, null,
		// null, null,
		// null);
		Cursor c = db.query("person", projection, selection, selectionArgs,
				null, null, null);
		return c;
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
