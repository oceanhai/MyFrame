package com.example.javaee.day18.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public class SystemDemo {
	public static void main(String[] args)throws Exception {

		long time = System.currentTimeMillis();//当前毫秒值
		String osName = System.getProperty("os.name");//根据key获取系统信息
		double result = Math.ceil(3.3);//向上取整
		int roundResult = Math.round(3.4f);//四舍五入 返回int,入参要是float
		int absResult = Math.abs(-10);//绝对值
		Integer.parseInt("5");//字符串转换成Int

		Date date = new Date();//系统日期和时间
		Date date2 = new Date(0);//1970年
		long time1 = date.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		String s = sdf.format(new Date());
		Date date3 = sdf.parse("2020-11-11");

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);//获取日期字段
		c.set(Calendar.MARCH,2);//0-11月  设置日历
		c.set(2020,7,1);//设置日历
		c.add(Calendar.DAY_OF_MONTH, 20);//自动偏移

		int[] arr = {3,1,5,8,9,2,15};
		Arrays.sort(arr);//数组升序
		String s1 = Arrays.toString(arr);
		int index = Arrays.binarySearch(arr,3);//二分查找

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(5);
		list.add(2);
		list.add(3);
		list.add(4);
		Collections.sort(list);//List集合升序排列
		Collections.shuffle(list);//List集合元素位置随机排列
		List<Integer> list2=Collections.synchronizedList(list);//线程不安全集合变成安全集合

		System.out.println("time="+time+",osName="+osName+",result="+result+",roundResult="+roundResult+
		",absResult="+absResult+",s1="+s1+",index="+index);

		System.exit(0);
	}
}
