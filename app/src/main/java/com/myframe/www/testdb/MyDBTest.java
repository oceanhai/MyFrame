package com.myframe.www.testdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;
//写一个类继承AndroidTestCase
public class MyDBTest extends AndroidTestCase{
	
	//严格定义方法
	public void testDBInsert(){
		MySQLiteOpenHelper helper=new MySQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into student (name,age,height) values ('小雪','18',1.65)");
	}
	public void testDelete(){
		MySQLiteOpenHelper helper=new MySQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
//		db.execSQL("delete from student where name='苏苏'");
		db.execSQL("delete from student where _id='7'");
	}
	public void testUdate(){
		MySQLiteOpenHelper helper=new MySQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update student set name='大雪' where _id=2");
	}
	
	public void testQuery1(){
		MySQLiteOpenHelper helper=new MySQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql="select * from student";
		//游标
		Cursor cursor = db.rawQuery(sql, null);
		//将游标移动到下一条数据，返回boolean结果表示是否存在下一条数据
//		boolean next1 = cursor.moveToNext();
//		if(next1){
////			String name = cursor.getString(1);
		//获得字段名在数据库表中的列的位置
//			int index = cursor.getColumnIndex("name");
			//通过列的位置获得数据的值
//			String name = cursor.getString(index);
//			System.out.println(name);
//		}
//		boolean next2 = cursor.moveToNext();
//		if(next2){
//			int columnIndex = cursor.getColumnIndex("name");
//			String name = cursor.getString(columnIndex);
//			System.out.println(name);
//		}
//		boolean next3 = cursor.moveToNext();
//		if(next3){
//			int columnIndex = cursor.getColumnIndex("name");
//			String name = cursor.getString(columnIndex);
//			System.out.println(name);
//		}
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			System.out.println(name);
		}
		
	}
	public void testQuery2(){
		MySQLiteOpenHelper helper=new MySQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		//参数2：补全参数1中的？，如果不需要就填null
//		Cursor cursor = db.rawQuery("select name,age,height from student where name=? and _id=?", new String[]{"大雪","2"});
//		while(cursor.moveToNext()){
//			String name = cursor.getString(cursor.getColumnIndex("name"));
//			System.out.println(name);
//		}
		List<Student> list=new ArrayList<Student>();
		Cursor cursor = db.rawQuery("select name,age,height from student",null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			String height = cursor.getString(cursor.getColumnIndex("height"));
			Student s=new Student();
			s.setName(name);
			s.setAge(age);
			s.setHeight(height);
			list.add(s);
		}
		System.out.println(list);
		
		
		
	}
	
}
