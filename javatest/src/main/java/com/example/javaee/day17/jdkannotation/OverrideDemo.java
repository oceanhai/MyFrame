package com.example.javaee.day17.jdkannotation;
/*
 *  JDK1.5 自带的注解
 *  @Override  方法是重写父类的
 *  如果重写错了,编译器提示你编译失败
 *
 *  Object: toString
 *  public String toString(){}
 *
 *  JDK1.5版本,不支持接口抽象方法重写,支持父类的
 *  JDK1.6版本,支持了接口方法,和父类方法的重写
 */
public class OverrideDemo implements Runnable{
	@Override
	public void run(){

	}

	@Override
	public String toString(){
		return "";
	}
}
