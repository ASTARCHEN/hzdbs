package com.snowland.beans;

public class User {
	private int Id;
	private String part;
	private String username;
	private String password;
	
	public User(){	
	}
	public User(String userName2,String part2) {
		Id = 0;
		username = userName2;
		part = part2;
	}
	public User(int id2, String userName2, String password, String part2) {
		Id = id2;
		username = userName2;
		this.password = password;
		part = part2;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
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
	
}
