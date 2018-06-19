package com.example.javaee.day18.beans;

import java.util.Arrays;

public class User {
	private String id;
	private String username;
	private String password;
	private String[] hobbies;

	public User() {
	}

	public User(String id, String username, String password, String[] hobbies) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.hobbies = hobbies;
	}

	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", hobbies="
				+ Arrays.toString(hobbies) + "]";
	}

	
}
