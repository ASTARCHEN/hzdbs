package com.snowland.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.snowland.beans.Question;
import com.snowland.beans.QuestionStore;
import com.snowland.server.action.StartTest;
import com.snowland.util.GetJsonHelper;

public class MyHandler implements Runnable {

	private Socket socket;

	public MyHandler(Socket socket) {
			this.socket = socket;
		}

	public void run() {
		try {
			System.out.println("新连接:" + socket.getInetAddress() + ":" + socket.getPort());
			InputStream inStream = socket.getInputStream();
			JSONObject jsonObject = GetJsonHelper.readStream(inStream);
//			int number = jsonObject.getInt("num");
//
//			String str = post(number);
//			socket.getOutputStream().write(str.getBytes());
			;
			// socket.set
			GetJSONInfo jsoninfo = new GetJSONInfo(jsonObject);
			String user = jsoninfo.getUser();
			String usertype = jsoninfo.getUserType();
			String request = jsoninfo.getDos();
			switch (usertype) {
			case "teacher":
				switch (request) {
				case "scorelist":
					break;
				case "start":
					StartTest start = new StartTest();
					start.run();
					break;
				case "end":
					break;
				case "login":
					break;
				default:
					break;
				}
				break;
			case "student":
			default:
				switch (request) {
				case "lx":
					break;
				case "ks":
					break;
				case "jj":
					break;
				case "login":
					break;
				default:
					break;
				}
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("关闭连接:" + socket.getInetAddress() + ":" + socket.getPort());
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String post(int number) {
		QuestionStore store = new QuestionStore();
		List<Question> list = store.getStore();
		List<Question> ready = new ArrayList<Question>();
		int[] index = randperm(list.size(), number);
		for (int i : index) {
			ready.add(list.get(i));
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(ready);
		return jsonArray.toString();
	}

	public int[] randperm(int n, int num) {
		double[] a = new double[n];
		for (int i = 0; i < n; ++i) {
			a[i] = Math.random();
		}

		int[] b = psort(a);
		return Arrays.copyOf(b, num);
	}

	private int[] psort(double a[]) {
		int n = a.length;
		int[] b = new int[n];
		int i, j;
		double temp;
		for (j = 0; j < n - 1; j++) {
			b[j] = j;
			for (i = j + 1; i < n; i++) {
				if (a[j] > a[i]) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
					b[j] = i;
				}
			}
		}
		return b;
	}

}
