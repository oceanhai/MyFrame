package com.example.javaee.day17.jdkannotation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 *  JDK1.5 自带的注解
 *  @SuppressWarnings 抑制警告
 *  写在方法上,类上,代码上
 *
 *  @SuppressWarnings 注解中,明确抑制的警告,是哪种警告
 *  @SuppressWarnings(抑制的类型是什么,写1个,写多个)
 *  注解中的参数,实际上是一个String[]
 *
 */
//@SuppressWarnings({"rawtypes","unchecked","unused","null","serial","deprecation"})
/*
 *  自己写功能,类和方法,可能会出现不同的警告
 *  抑制警告, 参数all 所有警告全抑制
 */
@SuppressWarnings("all")
public class SuppressWarningsDemo implements Serializable{
	public void demo01(){
		List list = new ArrayList();
		list.add(123);

		int a = 1;

		new Thread().stop();

		String s = null;
		System.out.println(s.length());
	}
}




