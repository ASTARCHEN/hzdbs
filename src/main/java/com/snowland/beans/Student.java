package com.snowland.beans;

public class Student extends User {
	public Student(){
		this.setPart(Common.STUDENT);
	}
	public Student(String username){
		super(username,Common.STUDENT);
	}
}
