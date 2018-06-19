package com.example.javaee.day18.beanutils;

import com.example.javaee.day18.beans.User;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/*
 *  Apache工具类 BeanUtils
 *  封装JavaBean对象的数据
 */
public class BeanUtilsDemo {
	/*
	 * BeanUtils工具类的静态方法
	 * populate(Object obj, Map<String,String[]> property)
	 * Map集合参数:
	 *   键: String,JavaBean中的成员变量名
	 *   值: String数组, 存储的实际数据
	 *
	 *   int[] a = new int[2]
	 *   int[] a = new int[]{1,2,3}
	 *   int[] a = {1};
	 */
	@Test
	public void demo03()throws Exception{
		User user = new User();
		//创建Map集合,键String,值是String[]
		Map<String, String[]> property = new HashMap<String, String[]>();
		//Map集合存储键值对,方法put
		property.put("id", new String[]{"u001"});
		property.put("username", new String[]{"rose"});
		property.put("password", new String[]{"123"});
		property.put("hobbies", new String[]{"抽烟","喝酒","烫头"});

		BeanUtils.populate(user, property);
		System.out.println(user);
	}

	/*
	 * BeanUtils类的静态方法
	 * getProperty(Object obj,String name)
	 * 获取JavaBean对象中的属性值
	 * obj : 哪个JavaBean的对象
	 * name: 成员变量
	 */
	@Test
	public void demo02()throws Exception{
		User user = new User();
		user.setId("uid001");
		user.setUsername("admin");
		user.setPassword("123");
		System.out.println(user);

		String id = BeanUtils.getProperty(user, "id");
		String username = BeanUtils.getProperty(user, "username");
		String password = BeanUtils.getProperty(user, "password");
		System.out.println(id);
		System.out.println(username);
		System.out.println(password);
	}

	/*
	 * BeanUtils类的静态方法
	 * 数据存储到指定的JavaBean对象中去
	 * setProperty(Object obj,String name,Object value)
	 * obj : 要封装的,自定义的JavaBean对象
	 * name: 存储的是哪个成员变量,名字
	 * value : 存储的具体数值
	 */
	@Test
	public void demo01(){
		//创建JavaBean对象,User
		User user = new User();
		//使用工具类BeanUtils,方法setProperty设置数据
		try {
			BeanUtils.setProperty(user, "id", "001");
			BeanUtils.setProperty(user, "username", "rose");
			BeanUtils.setProperty(user, "password", "123");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println(user);
	}
}



