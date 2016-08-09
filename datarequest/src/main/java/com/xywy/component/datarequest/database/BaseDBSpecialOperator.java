package com.xywy.component.datarequest.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class BaseDBSpecialOperator {

	public abstract List<String> getSpecialOperatorTag();

	public Cursor rawSQL(String tag, String selectionArgs, BaseSQLHelper helper) {
		return null;
	}

	public void exceSQL(String tag, BaseSQLHelper helper) {
	}

	public long insert(String tag, ContentValues values, BaseSQLHelper helper) {
		return 0;
	}

	public int delete(String tag, String selection, String[] selectionArgs,
			BaseSQLHelper helper) {
		return 0;
	}

	public int upData(String tag, ContentValues values, String selection,
			String[] selectionArgs, BaseSQLHelper helper) {
		return 0;
	}

	public Cursor query(String tag, String[] projection, String selection,
			String[] selectionArgs, String sortOrder, BaseSQLHelper helper) {
		return null;
	}

}
