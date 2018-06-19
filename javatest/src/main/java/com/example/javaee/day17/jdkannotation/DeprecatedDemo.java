package com.example.javaee.day17.jdkannotation;
/*
 *  JDK1.5 自带的注解
 *  @Deprecated  过时的以上
 *  加在方法上,告诉调用者,此方法已经过时,不建议使用
 *  对程序.,进行说明,告诉你过时
 */
@SuppressWarnings("all")
public class DeprecatedDemo {

	@Deprecated
	public void init(){

	}
	public static void main(String[] args) {
		new DeprecatedDemo().init();
	}
}
