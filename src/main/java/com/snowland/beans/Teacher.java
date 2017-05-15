package com.snowland.beans;

public class Teacher extends User {
	public Teacher(){
		this.setPart(Common.TEACHER);
	}
	public Teacher(String username){
		super(username,Common.TEACHER);
	}
}
