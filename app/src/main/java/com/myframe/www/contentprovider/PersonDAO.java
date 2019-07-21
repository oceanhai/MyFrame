package com.myframe.www.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import www.wuhai.common.utils.L;

public class PersonDAO {
	private SQLiteDatabase db;

	public PersonDAO(Context context) {
		MyHelper helper = new MyHelper(context);
		db = helper.getWritableDatabase();
	}

	public void insert(ContentValues values) {
		long id = db.insert("person", null, values);
		L.e("PersonDAO", "id="+id);
	}
}
