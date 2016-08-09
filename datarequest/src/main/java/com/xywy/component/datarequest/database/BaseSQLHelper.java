package com.xywy.component.datarequest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shijiazi on 15/12/26.
 * 数据库帮助类
 */
public class BaseSQLHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mdb;

    private Map<Class<? extends BaseTableMode>, BaseTableMode> modes;

    public BaseSQLHelper(Context context, String name, int version,
                         List<Class<? extends BaseTableMode>> classTypes) {
        super(context, name, null, version);
        initModeType(classTypes);
        initDataBase();
    }

    /**
     * 数据表创建
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        mdb = db;
        createTable();

        //// TODO: 15/12/26 考虑创建索引等
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //// TODO: 15/12/26 考虑升级数据库的更好方式
        onUpgradeOrDowngrade(db);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTable(db);
        onCreate(db);
    }

    /**
     * 关闭数据库
     */
    public void closeDB() {
        if (mdb == null) {
            return;
        }
        mdb.close();
    }

    public long insert(Class<BaseTableMode> modeClass, ContentValues values) {
        if (mdb == null) {
            return Long.MIN_VALUE;
        }
        BaseTableMode mode = modes.get(modeClass);
        return mdb.insert(mode.getTableName(), null, values);
    }

    public long insert(Class<BaseTableMode> modeClass, ContentValues values, int configType) {
        if (mdb == null) {
            return Long.MIN_VALUE;
        }
        BaseTableMode mode = modes.get(modeClass);
        return mdb.insertWithOnConflict(mode.getTableName(), null, values, configType);
    }

    public int delete(Class<BaseTableMode> modeClass, String selection, String[] selectionArgs) {
        if (mdb == null) {
            return -1;
        }
        BaseTableMode mode = modes.get(modeClass);
        return mdb.delete(mode.getTableName(), selection, selectionArgs);
    }

    public int update(Class<BaseTableMode> modeClass, ContentValues values, String selection,
                      String[] selectionArgs) {
        if (mdb == null) {
            return -1;
        }
        BaseTableMode mode = modes.get(modeClass);
        return mdb.update(mode.getTableName(), values, selection, selectionArgs);
    }

    public Cursor query(Class<BaseTableMode> modeClass, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (mdb == null) {
            return null;
        }
        BaseTableMode mode = modes.get(modeClass);
        return mdb.query(mode.getTableName(), projection, selection, selectionArgs, null, null,
                sortOrder);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        if (mdb == null) {
            return null;
        }
        return mdb.rawQuery(sql, selectionArgs);
    }

    public void excuSQL(String sql, String[] bindArgs) {
        if (mdb == null) {
            return;
        }
        mdb.execSQL(sql, bindArgs);
    }

    public void excuSQL(String sql) {
        if (mdb == null) {
            return;
        }
        mdb.execSQL(sql);
    }

    public void setTransactionSuccessful() {
        if (mdb == null) {
            return;
        }
        mdb.setTransactionSuccessful();
    }

    public void beginTransaction() {
        if (mdb == null) {
            return;
        }
        mdb.beginTransaction();
    }

    public void endTransaction() {
        if (mdb == null) {
            return;
        }
        mdb.endTransaction();
    }

    private void initDataBase() {
        mdb = getWritableDatabase();
    }

    private void initModeType(List<Class<? extends BaseTableMode>> classTypes) {
        modes = new HashMap<>();
        final int size = classTypes.size();
        Class<? extends BaseTableMode> modeType = null;
        for (int i = 0; i < size; i++) {
            modeType = classTypes.get(i);
            if (modeType == null) {
                continue;
            }
            try {
                modes.put(modeType, modeType.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTable() {
        Set<Class<? extends BaseTableMode>> modeSet = modes.keySet();
        Iterator<Class<? extends BaseTableMode>> iterator = modeSet.iterator();
        Class<? extends BaseTableMode> modeType = null;
        BaseTableMode mode = null;
        while (iterator.hasNext()) {
            modeType = iterator.next();
            if (modeType == null) {
                continue;
            }
            mode = modes.get(modeType);
            if (mode == null) {
                continue;
            }
            excuSQL(mode.createTableSql());
        }
    }

    private void onUpgradeOrDowngrade(SQLiteDatabase db) {
        Set<Class<? extends BaseTableMode>> modeSet = modes.keySet();
        Iterator<Class<? extends BaseTableMode>> iterator = modeSet.iterator();
        Class<? extends BaseTableMode> modeType = null;
        BaseTableMode mode = null;
        while (iterator.hasNext()) {
            modeType = iterator.next();
            if (modeType == null) {
                continue;
            }
            mode = modes.get(modeType);
            if (mode == null) {
                continue;
            }
            startUpgradeOrDowngrade(db, mode);
        }
    }

    /**
     * 数据库升级操作
     *
     * @param db
     * @param model
     */
    private void startUpgradeOrDowngrade(SQLiteDatabase db, BaseTableMode model) {
        String columns = getCommonColumns(db, model);
        if (columns == null) {
            try {
                dropTable(model);
            } catch (Exception e) {}
            return;
        }
        try {
            db.beginTransaction();
            // 将旧的表重新命名
            String tmp_table_name = "temp_" + model.getTableName();
            db.execSQL("ALTER TABLE " + model.getTableName() + " RENAME TO " + tmp_table_name + ";");
            // 创建新的表
            db.execSQL(model.createTableSql());
            // 数据复制
            db.execSQL("INSERT INTO " + model.getTableName() + "(" + columns + ")" + " SELECT "
                    + columns + " FROM " + tmp_table_name + ";");
            // 删除旧表
            db.execSQL("DROP TABLE " + tmp_table_name + ";");
            db.setTransactionSuccessful();
        } catch (Exception e) {} finally {
            db.endTransaction();
        }
    }

    /**
     * 获取共同的字段
     */
    private String getCommonColumns(SQLiteDatabase db, BaseTableMode model) {
        List<String> oldColumnList = getOldColumnList(db, model);
        if (oldColumnList == null) {
            return null;
        }
        int oldListSize = oldColumnList.size();
        List<String> newColumnList = getNewColumnList(model);
        StringBuffer sb = null;
        int newListSize = newColumnList.size();
        for (int i = 0; i < newListSize; i++) {
            String temp = newColumnList.get(i);
            if (temp == null || "".equals(temp)) {
                break;
            }
            for (int j = 0; j < oldListSize; j++) {
                if (temp.equals(oldColumnList.get(j))) {
                    if (sb == null) {
                        sb = new StringBuffer();
                        sb.append(temp);
                        break;
                    }
                    sb.append("," + temp);
                    break;
                }
            }
        }
        if (sb == null) {
            return null;
        }
        String columns = sb.toString();
        return columns;
    }

    /**
     * 获取旧的表结构
     *
     * @param db
     * @return
     */
    private ArrayList<String> getOldColumnList(SQLiteDatabase db, BaseTableMode model) {
        String table_name = model.getTableName();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("PRAGMA table_info(" + table_name + ");", null);
            ArrayList<String> oldColumnList = null;
            if (cursor != null && cursor.moveToFirst()) {
                oldColumnList = new ArrayList<String>(cursor.getCount());
                int column = cursor.getColumnIndexOrThrow("name");
                do {
                    String str = cursor.getString(column);
                    oldColumnList.add(str);
                } while (cursor.moveToNext());
                return oldColumnList;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    /**
     * 获取新的表结构
     *
     * @return
     */
    private List<String> getNewColumnList(BaseTableMode model) {
        String[] strs = model.getFieldAll();
        List<String> newColumnList = new ArrayList<>(strs.length);
        for (int i = 0; i < strs.length; i++) {
            newColumnList.add(strs[i]);
        }
        return newColumnList;
    }

    private void dropAllTable(SQLiteDatabase db) {
        String selectTableSql = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor cursor = db.rawQuery(selectTableSql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String tableName = cursor.getString(0);
                if (!tableName.equals("sqlite_sequence")) {
                    db.execSQL("DROP TABLE " + tableName);
                }
            }
        }
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }

    /**
     * 删除表
     *
     * @param model
     */
    private void dropTable(BaseTableMode model) {
        if (model == null) {
            return;
        }
        try {
            mdb.execSQL(model.deleteTableSql());
        } catch (Exception e) {}
    }


}
