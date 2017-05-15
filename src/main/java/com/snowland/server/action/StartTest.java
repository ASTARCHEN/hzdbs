package com.snowland.server.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.snowland.beans.Student;

public class StartTest implements Runnable{
	private List<Student> stulist;
	private Date date;
	public StartTest(){
		stulist = new ArrayList<Student>();
		date = Calendar.getInstance().getTime();
	}
	public StartTest(List<Student> list){
		stulist = list;
		date = Calendar.getInstance().getTime();
	}
	public StartTest(List<Student> list,Date date){
		this.stulist = list;
		this.date = date;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			Calendar cal = Calendar.getInstance();
			Date now = cal.getTime();
			if(now.after(date)) {
				// TODO ¿ªÊ¼´ðÌâ
			} else {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		System.out.println(cal.getTime().getHours() + " " + cal.getTime().getMinutes());
		
	}
}
