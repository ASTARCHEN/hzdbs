package com.snowland.server.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.snowland.beans.ExQuestion;
import com.snowland.beans.Student;

public class EndTestResult {
	public enum state {OFFLINE,UPLOADED,TESTING};
	private Map<Student,state> map = new HashMap<>();
	private ExQuestion exquestion;
	public EndTestResult(List<Student> slist){
		for(Student s:slist) {
			map.put(s, state.OFFLINE);
		}
	}
	public Map<Student, state> getMap() {
		return map;
	}
	public void setMap(Map<Student, state> map) {
		this.map = map;
	}
	public ExQuestion getExquestion() {
		return exquestion;
	}
	public void setExquestion(ExQuestion exquestion) {
		this.exquestion = exquestion;
	}
	
	
	
	
}
