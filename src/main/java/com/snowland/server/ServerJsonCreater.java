package com.snowland.server;

import org.json.JSONObject;

public class ServerJsonCreater {
	private String user;
	private String usertype;
	private JSONObject response;
	public ServerJsonCreater(String user,String usertype,JSONObject response){
		this.user = user;
		this.usertype = usertype;
		this.response = response;
	}

	public ServerJsonCreater(){
	}
	public JSONObject createJson(String user,String usertype,JSONObject response){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user", user);
		jsonObject.put("userType", usertype);
		jsonObject.put("response", response.toString());
		return jsonObject;
	}
	
	public JSONObject createJson(){
		return createJson(user,usertype,response);
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

	public JSONObject getResponse() {
		return response;
	}

	public void setResponse(JSONObject response) {
		this.response = response;
	}
	
	
}
