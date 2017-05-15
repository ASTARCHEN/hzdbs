package com.snowland.server.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.snowland.beans.Student;

public class EndTest{
	private List<Student> stulist;
	private int []flags;
	private Date date;
	public EndTest(){
		stulist = new ArrayList<Student>();
		date = Calendar.getInstance().getTime();
		flags = new int [stulist.size()];
	}
	public EndTest(List<Student> list){
		stulist = list;
		date = Calendar.getInstance().getTime();
		flags = new int [stulist.size()];
	}
	public EndTest(List<Student> list,Date date){
		this.stulist = list;
		this.date = date;
		flags = new int [stulist.size()];
	}

	public int run() {
		// TODO Auto-generated method stub
		while(true){
			Calendar cal = Calendar.getInstance();
			Date now = cal.getTime();
			if(now.before(date)) {
				// TODO 开始答题
			} else {
				// TODO 强制收卷
			}
		}
	}
	
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		System.out.println(cal.getTime().getHours() + " " + cal.getTime().getMinutes());
		
	}
}
