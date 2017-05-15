package com.snowland.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

public class SendHelper {
	public static void send(Socket socket, JSONObject json){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			writer.print(json);
			writer.flush();
			System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
