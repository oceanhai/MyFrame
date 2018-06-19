package com.example.javaee.day18.myutils;

import com.example.javaee.day18.beans.User;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/*
 *  自己定义工具类,实现对JavaBean数据的封装
 */
public class MyUtils {

//	@Test
//	public void demo03(){
//		Map<String,String[]> properties = new HashMap<String, String[]>();
//		properties.put("id", new String[]{"k001"});
//		properties.put("username", new String[]{"jack"});
//		properties.put("password", new String[]{"666"});
//		properties.put("hobbies", new String[]{"读书","上网","美女","捡钱1"});
//		User user =myUtils03(new User(), properties);
//		System.out.println(user);
//	}

	/**
	 * wuhai 自己封装的
	 * @param t
	 * @param properties
	 * @param <T>
     * @return
     */
	public static <T> T myUtils03(T t,Map<String, String[]> properties){
		try {
			BeanUtils.populate(t, properties);
			return t;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void demo02(){
		Map<String,String[]> properties = new HashMap<String, String[]>();
		properties.put("id", new String[]{"k001"});
		properties.put("username", new String[]{"jack"});
		properties.put("password", new String[]{"666"});
		properties.put("hobbies", new String[]{"读书","上网","美女","捡钱"});
		User user =(User) myUtils02(User.class, properties);
		System.out.println(user);
	}
	/*
     * 静态方法,传递JavaBean这个类的class文件对象,传递Map集合封装数据
     * 自动创建JavaBean的对象,调用者不需要创建对象
     * 返回对象,让调用者使用
     * 建立对象的方式,直接new 还有一个反射
     */
	public static Object myUtils02(Class clazz,Map<String, String[]> properties){
		try{
			//反射的方式创建出对象 TODO 只能调用无参构造
			Object obj = clazz.newInstance();
			BeanUtils.populate(obj, properties);
			return obj;
		}catch(Exception ex){
//			//利用运行时异常,RuntimeException() 出现了,不要出处理,修改源代码
//			throw new RuntimeException("创建JavaBean对象失败"); TODO 真正开发中怎么可能直接抛异常
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/*
	 * 静态方法,传递JavaBean对象,传递Map集合封装数据
	 */
	public static void myUtils01(Object obj,Map<String, String[]> properties){
		try{
			BeanUtils.populate(obj, properties);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

//	@Test
//	public void demo01(){
//		User user = new User();
//		Map<String,String[]> properties = new HashMap<String, String[]>();
//		properties.put("id", new String[]{"k001"});
//		properties.put("username", new String[]{"jack"});
//		properties.put("password", new String[]{"666"});
//		properties.put("hobbies", new String[]{"读书","上网","美女","捡钱"});
//		myUtils01(user,properties);
//		System.out.println(user);
//	}

}
