package com.myframe.www.testdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME="myframe.db";
	private static final int START_VERSION = 1;
	private static final int DATABASE_VERSION = 5;

	public MySQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
	}

	//数据库第一次创建的时候调用这个方法
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table student (_id integer primary key autoincrement,name varchar,age integer)";
		db.execSQL(sql);

		onUpgrade(db, START_VERSION, DATABASE_VERSION);
	}

	//数据库更新的时候执行，当数据库版本提高的时候更新
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql1 = "";
		String sql2 = "";
		for(int i=oldVersion;i<newVersion;i++){
			switch (i){
				case 1:
					//对table 增加字段
//					sql1 = "alter table student add height varchar";
//					db.execSQL(sql1);
					break;
				case 2:
//					sql1 = "alter table student add COLUMN weight varchar";
//					sql2 = "alter table student add address varchar";
//					db.execSQL(sql1);
//					db.execSQL(sql2);
					break;
				case 3:
					//表重命名
					sql1 = "alter table student rename to university";
					db.execSQL(sql1);
					break;
				case 4:
					//变更字段 3+4结合操作
					// 3因为重命名表了，所以这里咱们再新创建一个student表
					db.execSQL("create table student (_id integer primary key autoincrement,xingming varchar,age integer)");
					db.execSQL("insert into student('xingming','age') select 'name','age' from university");
					//由于在Sqlite中使用自增长字段,引擎会自动产生一个sqlite_sequence表,
					//用于记录每个表的自增长字段的已使用的最大值，所以要一起更新下。如果有没有设置自增长，则跳过此
//					db.execSQL("update sqlite_sequence set seq=3 where name='student'");
					//删除临时表
					db.execSQL("drop table university");
					break;
			}
		}
	}

}
