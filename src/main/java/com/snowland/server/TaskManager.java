package com.snowland.server;

import java.util.ArrayList;
import java.util.List;

import com.snowland.beans.Common;

public class TaskManager {
	private static TaskManager single=null; 

	public static TaskManager getInstance() { 
        if (single == null) {    
            synchronized (TaskManager.class) {    
               if (single == null) {         		  
            	   TaskManager.teacherList = new ArrayList<Task>();
            	   TaskManager.studentList = new ArrayList<Task>();
               }    
            }    
        }    
        return single;   
    }
	
	
	private static List<Task> teacherList;
	private static List<Task> studentList;
	
	public TaskManager() {
		single = TaskManager.getInstance();
		
	}
	
	public static boolean add(String part, Task t) {
		if(part.equals(Common.TEACHER)) {
			synchronized (teacherList) {
				return teacherList.add(t);
			}
			
		} else if(part.equals(Common.STUDENT)) {
			synchronized (studentList) {
				return studentList.add(t);
			}
		} else {
			// TODO ERROR PAGE
			return false;
		}
	}
	
	public boolean remove(String part, Task t) {
		if(part.equals(Common.TEACHER)) {
			synchronized (teacherList) {
				return teacherList.remove(t);
			}
			
		} else if(part.equals(Common.STUDENT)) {
			synchronized (studentList) {
				return studentList.remove(t);
			}			
		} else {
			// TODO ERROR PAGE
			return false;
		}
	}

	public static List<Task> getTeacherList() {
		return teacherList;
	}

	public static List<Task> getStudentList() {
		return studentList;
	}


}
