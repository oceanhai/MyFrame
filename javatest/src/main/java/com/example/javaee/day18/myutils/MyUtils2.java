package com.example.javaee.day18.myutils;

import com.example.javaee.day18.beans.User;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
 *  实现工具类,封装数据
 *  传递是哪个类,返回哪个类
 */
public class MyUtils2 {
	@Test
	public void demo01(){
		Map<String,String[]> properties = new HashMap<String, String[]>();
		properties.put("id", new String[]{"k001"});
		properties.put("username", new String[]{"jack"});
		properties.put("password", new String[]{"666"});
		properties.put("hobbies", new String[]{"读书","上网","美女","捡钱"});
		User u = myUtils(User.class,properties);
		System.out.println(u);
	}
	/*
	 *  利用泛型实现
	 *  E,K,V 都是变量,传递什么类,就是什么类型
	 */
	public static<T> T myUtils(Class<T> clazz,Map<String, String[]> properties){
		try{
			//反射的方式创建出对象
			T obj = clazz.newInstance();
			BeanUtils.populate(obj, properties);
			return obj;
		}catch(Exception ex){
			//利用运行时异常,RuntimeException() 出现了,不要出处理,修改源代码
			throw new RuntimeException("创建JavaBean对象失败");
		}
	}
}
