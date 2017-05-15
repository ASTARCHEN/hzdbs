package com.snowland.server;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.snowland.beans.Common;
import com.snowland.beans.Student;
import com.snowland.beans.Teacher;
import com.snowland.database.LoginAction;
import com.snowland.server.action.StartTest;
import com.snowland.util.GetJsonHelper;

public class Server {
	private List<Teacher> teacherList;
	private List<Student> studentList;

	private ServerSocket server = null;
	public Server(int port){
		teacherList = new ArrayList<>();
		studentList = new ArrayList<>();
		try {
			server = new ServerSocket(port);
			// b)指定绑定的端口，并监听此端口。
			System.out.println("服务器启动成功");
			// 创建一个ServerSocket在端口5209监听客户请求
		} catch (Exception e) {
			System.out.println("没有启动监听：" + e);
			e.printStackTrace();
			// 出错，打印出错信息
		}
		TaskManager.getInstance();
	}
	
	public Server(){
		this(Common.PORT);
	}
	// 搭建服务器端
	public static void main(String[] args) throws Exception {
		Server socketService = new Server();
		// 1、a)创建一个服务器端Socket，即SocketService
		socketService.oneServer();
	}
// TODO user try catch 
	public void oneServer() throws Exception {
		
		Socket socket = null;
		while (true) {
			
				while (socket == null) {
					socket = server.accept();
				}
				
				Task task = new Task(socket);
				task.start();
				socket = null;
		}
	}
}

