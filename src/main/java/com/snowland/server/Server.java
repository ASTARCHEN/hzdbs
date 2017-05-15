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
			// b)ָ���󶨵Ķ˿ڣ��������˶˿ڡ�
			System.out.println("�����������ɹ�");
			// ����һ��ServerSocket�ڶ˿�5209�����ͻ�����
		} catch (Exception e) {
			System.out.println("û������������" + e);
			e.printStackTrace();
			// ������ӡ������Ϣ
		}
		TaskManager.getInstance();
	}
	
	public Server(){
		this(Common.PORT);
	}
	// ���������
	public static void main(String[] args) throws Exception {
		Server socketService = new Server();
		// 1��a)����һ����������Socket����SocketService
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

