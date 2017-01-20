package com.myframe.www.testormlite;

import android.content.Context;
import android.content.pm.*;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuhai on 2017/01/18 16:18.
 * 描述：
 */

public class DBHelper extends OrmLiteSqliteOpenHelper{

    /**
     * 数据库名字
     */
    private static final String DB_NAME = "test.db";
    /**
     * 数据库版本
     */
    private static final int START_VERSION = 1;
    private static final int DATABASE_VERSION = 3;
    /**
     * 用来存放Dao的map
     */
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private static DBHelper instance;

    /**
     * 构造方法
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, START_VERSION);
    }    /**
     * 获取单例
     * @param context
     * @return
     */
    public static synchronized DBHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 这里创建表
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        // 创建表
        try {
            /**
             * 逻辑混乱下面的表
             */
            TableUtils.createTable(connectionSource, PackageInfo.class);
            TableUtils.createTable(connectionSource, Photographer.class);
            TableUtils.createTable(connectionSource, Theme.class);
            TableUtils.createTable(connectionSource, Img.class);
            /**
             * 以下面的为主
             */
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Article.class);

            //版本升级
            onUpgrade(sqLiteDatabase, connectionSource, START_VERSION, DATABASE_VERSION);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    onupgrade

    /**
     * 这里进行更新表操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try
        {
//            /**
//             * 逻辑混乱下面的表
//             */
//            TableUtils.dropTable(connectionSource, PackageInfo.class, true);//这种是直接删掉表了
//            TableUtils.dropTable(connectionSource, Photographer.class, true);
//            TableUtils.dropTable(connectionSource, Theme.class, true);
//            TableUtils.dropTable(connectionSource, Img.class, true);
//            /**
//             * 以下面的为主
//             */
//            TableUtils.dropTable(connectionSource, User.class, true);
//            TableUtils.dropTable(connectionSource, Article.class, true);
//            onCreate(sqLiteDatabase, connectionSource);

            String sql1 = "";
            for(int i=oldVersion;i<newVersion;i++) {
                switch (i) {
                    case 1://数据库版本1 升级到 版本2
                        //对table 增加字段
                        sql1 = "alter table tb_user add age integer";
                        getDao(User.class).executeRawNoArgs(sql1);
                        break;
                    case 2://数据库版本2 升级到 版本3
                        sql1 = "alter table tb_user add height integer";
                        getDao(User.class).executeRawNoArgs(sql1);
                        break;
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过类来获得指定的Dao
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }else{
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
