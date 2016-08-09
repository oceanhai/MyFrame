package com.xywy.component.datarequest.database;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseArray;

public abstract class BaseContentProvider extends ContentProvider {

	private UriMatcher mUriMatcher;

	private BaseSQLHelper mSqlHelper;

	private static final int NO_MATCH_CODE = -1;

	private SparseArray<BaseDBSpecialOperator> operators;

	private String dbName;

	/**
	 * 注册特殊uri
	 *
	 * @param specialTags
	 * @return
	 */
	protected List<BaseDBSpecialOperator> onSpecialUriMatcherRegist(
			List<BaseDBSpecialOperator> specialTags) {
		return specialTags;
	}

	protected List<Class<? extends BaseTableMode>> onTableCreateRegist(
			List<Class<? extends BaseTableMode>> classTypes) {
		return classTypes;
	}

	public abstract String getDBName();

	@Override
	public final boolean onCreate() {
		return true;
	}

	private synchronized void initDBHelper() {
		if (dbName == null) {
			treateInitDBHelper();
			return;
		}
		if (!checkDBNameChange()) {
			return;
		}
		closeDBHelper();
		treateInitDBHelper();
	}

	private void closeDBHelper() {
		if (mSqlHelper == null) {
			return;
		}
		mSqlHelper.close();
	}

	private void treateInitDBHelper() {
		dbName = getDBName();
		List<Class<? extends BaseTableMode>> classTypes = new ArrayList<Class<? extends BaseTableMode>>();
		List<BaseDBSpecialOperator> specialTags = new ArrayList<>();
		mSqlHelper = new BaseSQLHelper(getContext(), getDBName(),
				DBConfig.DB_VERSION, onTableCreateRegist(classTypes));
		registSpecialUriMatcher(onSpecialUriMatcherRegist(specialTags));
	}

	private boolean checkDBNameChange() {
		return !dbName.equals(getDBName());
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		initDBHelper();
		int code = mUriMatcher.match(uri);
		long id = 0;
		if (code != NO_MATCH_CODE) {
			id = treateInsertSpecialUri(getSpaecialTag(uri),
					operators.get(code), values);
		} else {
			int insertConfig = UriFactory.parserSpecialInsertUriConfig(uri);
			if (insertConfig != UriFactory.NO_INSERT_CONFIG_TAG) {
				id = mSqlHelper.insert(getModeClass(uri), values, insertConfig);
			} else {
				id = mSqlHelper.insert(getModeClass(uri), values);
			}
		}
		return appendInsertUri(uri, id);
	}

	private Uri appendInsertUri(Uri uri, long id) {
		if (id < 0) {
			return uri;
		}
		return Uri.withAppendedPath(uri, String.valueOf(id));
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		initDBHelper();
		int code = mUriMatcher.match(uri);
		if (code != NO_MATCH_CODE) {
			return treateDeleteSpecialUri(getSpaecialTag(uri),
					operators.get(code), selection, selectionArgs);
		}
		return mSqlHelper.delete(getModeClass(uri), selection, selectionArgs);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		initDBHelper();
		int code = mUriMatcher.match(uri);
		if (code != NO_MATCH_CODE) {
			return treateUpDataSpecialUri(getSpaecialTag(uri),
					operators.get(code), values, selection, selectionArgs);
		}
		return mSqlHelper.update(getModeClass(uri), values, selection,
				selectionArgs);
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		initDBHelper();
		int isReturn = 0;
		try {
			mSqlHelper.beginTransaction();
			isReturn = super.bulkInsert(uri, values);
			mSqlHelper.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mSqlHelper.endTransaction();
		}
		return isReturn;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		initDBHelper();
		int code = mUriMatcher.match(uri);
		if (code != NO_MATCH_CODE) {
			return treateQuerySpecialUri(getSpaecialTag(uri),
					operators.get(code), projection, selection, selectionArgs,
					sortOrder);
		}
		return mSqlHelper.query(getModeClass(uri), projection, selection,
				selectionArgs, sortOrder);
	}

	@Override
	public ContentProviderResult[] applyBatch(
			ArrayList<ContentProviderOperation> operations)
			throws OperationApplicationException {
		initDBHelper();
		
		mSqlHelper.beginTransaction();
		ContentProviderResult[] results = new ContentProviderResult[0];
		try{
			results = super.applyBatch(operations);
			mSqlHelper.setTransactionSuccessful();   //设置事务标记为successful
		}finally{
			mSqlHelper.endTransaction();//结束事务
			return results;
		}
	}

	private void registSpecialUriMatcher(
			List<BaseDBSpecialOperator> specialTags) {
		mUriMatcher = new UriMatcher(NO_MATCH_CODE);
		operators = new SparseArray<BaseDBSpecialOperator>();
		final int size = specialTags.size();
		for (int i = 0; i < size; i++) {
			registSpecialUriMatcher(specialTags.get(i));
		}
	}

	private void registSpecialUriMatcher(BaseDBSpecialOperator operator) {
		String tag = null;
		int code = 0;
		String authority = getClass().getAnnotation(
				BaseContentProviderNode.class).authorities();
		final int size = operator.getSpecialOperatorTag().size();
		List<String> tags = operator.getSpecialOperatorTag();
		for (int i = 0; i < size; i++) {
			tag = tags.get(i);
			code = Math.abs(tag.hashCode());
			mUriMatcher.addURI(authority, tag, code);
			operators.put(code, operator);
		}
	}

	private long treateInsertSpecialUri(String tag,
			BaseDBSpecialOperator operator, ContentValues values) {
		return operator.insert(tag, values, mSqlHelper);
	}

	private int treateDeleteSpecialUri(String tag,
			BaseDBSpecialOperator operator, String selection,
			String[] selectionArgs) {
		return operator.delete(tag, selection, selectionArgs, mSqlHelper);
	}

	private int treateUpDataSpecialUri(String tag,
			BaseDBSpecialOperator operator, ContentValues values,
			String selection, String[] selectionArgs) {
		return operator.upData(tag, values, selection, selectionArgs,
				mSqlHelper);
	}

	private Cursor treateQuerySpecialUri(String tag,
			BaseDBSpecialOperator operator, String[] projection,
			String selection, String[] selectionArgs, String sortOrder) {
		return operator.query(tag, projection, selection, selectionArgs,
				sortOrder, mSqlHelper);
	}

	private Class<BaseTableMode> getModeClass(Uri uri) {
		List<String> paths = uri.getPathSegments();
		Class<BaseTableMode> modeClass = null;
		try {
			modeClass = (Class<BaseTableMode>) Class.forName(paths.get(0));
		} catch (Exception e) {
		}
		return modeClass;
	}

	private String getSpaecialTag(Uri uri) {
		List<String> paths = uri.getPathSegments();
		return paths.get(0);
	}

}
