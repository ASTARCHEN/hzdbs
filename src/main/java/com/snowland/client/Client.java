package com.snowland.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

import com.snowland.beans.Common;
import com.snowland.beans.User;
import com.snowland.util.GetJsonHelper;
import com.snowland.util.SendHelper;

public class Client {
	private Socket socket;
	User user = null;
	public Client(String string, int i) throws UnknownHostException, IOException {
		socket = new Socket(string,i);
	}
	public Client(String string, int i,User u) throws UnknownHostException, IOException {
		socket = new Socket(string,i);
		user = u;
	}
	public void requestPaper(int value) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", value);	
		ClientJsonCreater creater = new ClientJsonCreater(user.getUsername(),user.getPart(),Common.START_TEST, jsonObject);
		JSONObject json = creater.createJson();
		SendHelper.send(socket, json);
	}
	public Client() throws UnknownHostException, IOException {
		socket = new Socket(Common.HOST,Common.PORT);
	}

	
	public void setUser(User u){
		user = u;
	}
	public void login(String password) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("password", password);	
		ClientJsonCreater creater = new ClientJsonCreater(user.getUsername(),user.getPart(),Common.LOGIN, jsonObject);
		JSONObject json = creater.createJson();
		SendHelper.send(socket, json);
	}
	
	public void exit() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();	
		ClientJsonCreater creater = new ClientJsonCreater(user.getUsername(),user.getPart(),Common.EXIT, jsonObject);
		JSONObject json = creater.createJson();
		SendHelper.send(socket, json);
	}
	
	public JSONObject getResponse() throws Exception {
		InputStream inStream = socket.getInputStream();
		JSONObject jsonObject = GetJsonHelper.readStream(inStream);
		return jsonObject;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
