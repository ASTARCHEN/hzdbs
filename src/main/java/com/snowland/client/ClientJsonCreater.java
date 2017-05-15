package com.snowland.client;

import org.json.JSONObject;

public class ClientJsonCreater {
	private String user;
	private String usertype;
	private String request;
	private JSONObject part;
	
	public ClientJsonCreater(){
	}
	public ClientJsonCreater(String user,String usertype,String request,JSONObject part){
		this.user = user;
		this.usertype = usertype;
		this.request= request;
		this.part = part;
	}
	public JSONObject createJson(){
		return createJson(user,usertype,request,part);
	}

	public JSONObject createJson(String user,String usertype,String request,JSONObject part){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user", user);
		jsonObject.put("userType", usertype);
		jsonObject.put("dos", request);
		jsonObject.put("part", part.toString());
		return jsonObject;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public JSONObject getPart() {
		return part;
	}
	public void setPart(JSONObject part) {
		this.part = part;
	}
	
	
}
