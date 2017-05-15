package com.snowland.server;

import org.json.JSONObject;

public class GetJSONInfo {
	private JSONObject json;
	public GetJSONInfo(String s){
		json = new JSONObject(s);
	}
	public GetJSONInfo(JSONObject s){
		json = s;
	}
	
	public GetJSONInfo(org.json.simple.JSONObject s){
		json = new JSONObject(s.toJSONString());
	}
	public String getUser(){
		return json.getString("user");
	}
	public String getUserType(){
		return json.getString("userType");
	}
	public String getDos(){
		return json.getString("dos");
	}
	public String getPart(){
		return json.getString("part");
	}
	public JSONObject getJson() {
		return json;
	}
	
}
