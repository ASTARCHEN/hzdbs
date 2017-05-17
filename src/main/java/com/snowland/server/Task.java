package com.snowland.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import org.json.JSONObject;

import com.snowland.beans.Common;
import com.snowland.beans.Paper;
import com.snowland.beans.User;
import com.snowland.database.LoginAction;
import com.snowland.util.GetJsonHelper;
import com.snowland.util.SendHelper;

public class Task extends Thread {
	private User user;
	private Socket socket;

	public Task(Socket s) {
		socket = s;
		user = null;
	}

	public Task() {
		socket = null;
		user = null;
	}

	public Task(Socket s, User u) {
		socket = s;
		user = u;
	}
	/**
	 * 开始测试
	 * @param jsoninfo
	 * @return
	 */
	public boolean testPaper(GetJSONInfo jsoninfo) {

		List<Task> list = TaskManager.getStudentList();
		boolean flag = true;
		JSONObject part = new JSONObject(jsoninfo.getPart());
		int value = part.getInt("value");
		for (Task stuTask : list) {
			flag &= stuTask.postPapar(value);
		}
		JSONObject res = new JSONObject();
		part.put("tag", Common.START_TEST);
		part.put("value", flag);
		ServerJsonCreater jsonCreater = new ServerJsonCreater(user.getUsername(), user.getPart(), res);
		JSONObject response = jsonCreater.createJson();
		SendHelper.send(socket, response);
		return flag;
	}
	/**
	 * 发送试卷给学生
	 * @param value
	 * @return
	 */
	public boolean postPapar(int value) {
		Paper paper = new Paper();
		paper.createPaper(value);
		JSONObject part = new JSONObject();
		part.put("stem", paper.getStemList());
		part.put("tag", "paper");
		ServerJsonCreater creater = new ServerJsonCreater(user.getUsername(), user.getPart(), part);
		JSONObject response = creater.createJson();
		SendHelper.send(socket, response);
		return true;
	}

	public void login(GetJSONInfo jsoninfo) throws IOException{
		user = new User(jsoninfo.getUser(), jsoninfo.getUserType());
		LoginAction login = new LoginAction();
		JSONObject part = new JSONObject(jsoninfo.getPart());
		User tag = login.getLogin(user.getUsername(), part.getString("password"));
		if(tag == null){
			user = null;
			return;
		}
		JSONObject response = new JSONObject();
		TaskManager.add(user.getPart(),this);
		response.put("tag", "login");
		response.put("state", tag.getPart());
		ServerJsonCreater creater = new ServerJsonCreater(user.getUsername(), user.getPart(), part);
		OutputStream outStream = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(outStream);
		writer.print(creater.createJson(user.getUsername(), user.getPart(), response));
		writer.flush();
	}
	public void run() {
		try {
			InputStream inStream = null;
			while (true) {
				inStream = socket.getInputStream();
				JSONObject jsonObject = GetJsonHelper.readStream(inStream);
				// socket.set
				GetJSONInfo jsoninfo = new GetJSONInfo(jsonObject);
				String user = jsoninfo.getUser();
				String usertype = jsoninfo.getUserType();
				String request = jsoninfo.getDos();
				switch (usertype) {
				case Common.TEACHER:
					switch (request) {
					case "scorelist":
						break;
					case Common.START_TEST:{
						testPaper(jsoninfo);
						break;
					}
					case Common.HANDLE_PAPER:
						handlePaper(jsoninfo);
						break;
					case Common.LOGIN: {
						login(jsoninfo);
						break;
					}
					case Common.EXIT:{
						exit(jsoninfo);
						break;
					}
					default:
						break;
					}
					break;
				case Common.STUDENT:
				default:
					switch (request) {
					case "lx":
						break;
					case "ks":
						break;
					case Common.UPLOAD_PAPER: {
						uploadPaper(jsoninfo);
						
						break;
					}
					case Common.LOGIN: {
						login(jsoninfo);
						break;
					}
					default:
						break;
					}
					break;
				}
			}
		} catch (Exception e) {
			if(null !=socket)
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			System.out.println(user.getPart());
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("deprecation")
	private void exit(GetJSONInfo jsoninfo) {
		try {
			// NOTO 下面两句顺序不能颠倒！
			TaskManager.remove(user.getPart(),this);
			if(null != socket){
				this.socket.close();
			}
			super.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 交卷
	 * @param jsoninfo
	 */
	public boolean handlePaper(GetJSONInfo jsoninfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 上传试卷
	 * @param jsoninfo
	 * @return
	 */
	private boolean uploadPaper(GetJSONInfo jsoninfo) {	
		
		for(Task t : TaskManager.getTeacherList()){
			JSONObject part = new JSONObject(jsoninfo.getPart());			
			part.put("stem_and_ansaw", part.get("stem_and_ansaw"));
			ServerJsonCreater teacherCreater = new ServerJsonCreater(t.user.getUsername(), t.user.getPart(), jsoninfo.getJson());
			SendHelper.send(t.socket,teacherCreater.createJson());
		}
		
		String tag = "okay";
		JSONObject part = new JSONObject();
		part.put("tag", tag);
		ServerJsonCreater stuCreater = new ServerJsonCreater(user.getUsername(), user.getPart(), part);		
		JSONObject response = stuCreater.createJson();
		SendHelper.send(socket, response);
		return true;
		
	}

	public void start() {
		super.start();
	}
}
