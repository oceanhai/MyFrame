package com.myframe.www.testdb;

import com.myframe.www.retrofit.Course;

import java.util.List;

public class Student {

	private String name;
	private int age;
	private String height;
	private List<Course> courses;

	public Student() {
	}

	public Student(String name, int age, String height) {
		this.name = name;
		this.age = age;
		this.height = height;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", age=" + age +
				", height='" + height + '\'' +
				'}';
	}
}
