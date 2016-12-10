package step.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import step.model.DateBaseModelBase;
import step.model.StepDataModel;
import step.model.StepModel;


/**
 * 数据库帮助类
 */
public class DateBaseHelper extends SQLiteOpenHelper {

    private static DateBaseHelper instance;
    private SQLiteDatabase wDB;
    private SQLiteDatabase rDB;
    private Map<Class<? extends DateBaseModelBase>, DateBaseModelBase> modes;


    public DateBaseHelper(Context context) {
        super(context, DBConfig.dbName, null, DBConfig.dbVersion);
        initDataBase();
    }

    public static DateBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DateBaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public SQLiteDatabase getwDB() {
        return wDB;
    }

    /**
     * 数据表创建
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        wDB = db;
        rDB = db;
        createTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2 && newVersion >= 2) {
            Log.d("StepDetector", "执行upgrade   " + System.currentTimeMillis());
            StepModel stepModel = new StepModel();
            db.execSQL(stepModel.createTableSql());
        }
    }

    /**
     * 关闭数据库
     */
    public void closeDB() {
        if (instance != null) {
            try {
                wDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }

    private void initDataBase() {
        initModeType();
        wDB = getWritableDatabase();
        rDB = getReadableDatabase();

    }

    private void initModeType() {
        modes = new HashMap<Class<? extends DateBaseModelBase>, DateBaseModelBase>();
        modes.put(StepModel.class, new StepModel());
    }

    private void createTable() {
        Set<Class<? extends DateBaseModelBase>> modeSet = modes.keySet();
        Iterator<Class<? extends DateBaseModelBase>> iterator = modeSet.iterator();
        Class<? extends DateBaseModelBase> modeType = null;
        DateBaseModelBase mode = null;
        while (iterator.hasNext()) {
            modeType = iterator.next();
            if (modeType == null) {
                continue;
            }
            mode = modes.get(modeType);
            if (mode == null) {
                continue;
            }
            execSQL(mode.createTableSql());
        }
    }

    private void execuSQL(String sql, String[] bindArgs) {
        if (wDB != null) {
            wDB.execSQL(sql, bindArgs);
        }
    }

    private void execSQL(String sql) {
        if (wDB != null) {
            wDB.execSQL(sql);
        }
    }

    public void execSQL(String sql, Object[] bindArgs) {
        if (wDB != null) {
            wDB.execSQL(sql, bindArgs);
        }
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        Log.d("StepDetector", "sql语句=" + sql);
        Cursor cursor = null;
        if (rDB != null) {
            return cursor = rDB.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }


    /**
     * 寻医问药用
     * 取出所有DB中数据list集合
     *
     * @return
     */
    public List<StepDataModel> queryAll() {
        if (wDB != null) {
            List<StepDataModel> list = new ArrayList<StepDataModel>();
            Cursor cursor = wDB.query(StepModel.table_name, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                StepDataModel model = new StepDataModel();
                model.setDate(cursor.getString(cursor.getColumnIndex(StepModel.date)));
                model.setStepCount(cursor.getInt(cursor.getColumnIndex(StepModel.stepCount)));
                list.add(model);
            }
            return list;
        }
        return null;
    }

    /**
     * 取出满足条件的所有DB中数据list集合
     *
     * @param columns
     * @param selection
     * @param selectionArgs
     * @return
     */
    public List<StepDataModel> queryList(String[] columns, String selection, String[] selectionArgs) {
        if (wDB != null) {
            List<StepDataModel> list = new ArrayList<StepDataModel>();
            Cursor cursor = wDB.query(StepModel.table_name, columns, selection, selectionArgs, null, null, null);
            while (cursor.moveToNext()) {
                StepDataModel model = new StepDataModel();
                model.setDate(cursor.getString(cursor.getColumnIndex(StepModel.date)));
                model.setStepCount(cursor.getInt(cursor.getColumnIndex(StepModel.stepCount)));
                list.add(model);
            }
            return list;
        }
        return null;
    }

    /**
     * 根据日期 获取当前日期步数
     *
     * @param date
     * @return
     */
    public int getStepNumByDate(String date) {
        if (wDB != null) {
            int stepNum = 0;
            Cursor cursor = wDB.query(StepModel.table_name, new String[]{"step_count"}, StepModel.date + "=?", new String[]{date}, null, null, null);
            while (cursor.moveToNext()) {
                stepNum = cursor.getInt(cursor.getColumnIndex(StepModel.stepCount));
            }
            return stepNum;
        }
        return 0;
    }

    /**
     * 插入一条数据：按日期
     *
     * @param values
     */
    public void insertDate(ContentValues values) {
        if (wDB != null) {
            wDB.insert(StepModel.table_name, null, values);
        }
    }
}
