package com.myframe.www.widget.calendar;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//TODO 加签到时候使用
public class DBManager
{
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context)
    {
        helper = new DatabaseHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * add persons
     * 
     * @param persons
     */
    public void add(List<sqlit> persons)
    {
        // 采用事务处理，确保数据完整性
        db.beginTransaction(); // 开始事务
        try
        {
            for (sqlit person : persons)
            {
                db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME
                        + " VALUES(?, ?)", new Object[] { person.date,
                        person.isselct});
                // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
                // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
                // 使用占位符有效区分了这种情况
            }
            db.setTransactionSuccessful(); // 设置事务成功完成
        }
        finally
        {
            db.endTransaction(); // 结束事务
        }
    }

    /**
     * update person's age
     * 
     * @param person
     */
    public void updateAge(sqlit person)
    {
        ContentValues cv = new ContentValues();
        cv.put("isselct", person.isselct);
        db.update(DatabaseHelper.TABLE_NAME, cv, "isselct = ?",
                new String[] { person.isselct });
    }

    /**
     * delete old person
     * 
     * @param person
     */
    public void deleteOldPerson(sqlit person)
    {
        db.delete(DatabaseHelper.TABLE_NAME, "isselct= ?",
                new String[] { String.valueOf(person.isselct) });
    }

    /**
     * query all persons, return list
     * 
     * @return List<Person>
     */
    public List<sqlit> query()
    {
        ArrayList<sqlit> persons = new ArrayList<sqlit>();
        Cursor c = queryTheCursor();
        while (c.moveToNext())
        {
        	sqlit person = new sqlit();
            person.date = c.getString(c.getColumnIndex("date1"));
            person.isselct = c.getString(c.getColumnIndex("isselct"));
            persons.add(person);
        }
        c.close();
        return persons;
    }

    /**
     * query all persons, return cursor
     * 
     * @return Cursor
     */
    public Cursor queryTheCursor()
    {
        Cursor c = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME,
                null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB()
    {
        // 释放数据库资源
        db.close();
    }

}
