package hzdbs;

import java.io.IOException;

import com.snowland.beans.Common;
import com.snowland.beans.Teacher;
import com.snowland.client.Client;

public class TestClientTeacher {
	public static void main(String[] args) throws Exception {
		try {
			Client client = new Client(Common.HOST,Common.PORT);
			Teacher teacher = new Teacher("admin");
			client.setUser(teacher);
			client.login("admin");
			String str = client.getResponse().toString();
			System.out.println(str);
			client.requestPaper(1);
			str = client.getResponse().toString();
			System.out.println(str);
			while(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
