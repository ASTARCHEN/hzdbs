package hzdbs;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import com.snowland.beans.Common;
import com.snowland.beans.Student;
import com.snowland.client.Client;
import com.snowland.util.GetJsonHelper;

public class TestClientStudent {
	public static void main(String[] args) {
		try {
			Client client = new Client(Common.HOST,Common.PORT);
			Student stu = new Student("cxl");
			client.setUser(stu);
			client.login("123");
			try {
				String str = client.getResponse();
				System.out.println(str);
				while(true){
				InputStream in = client.getSocket().getInputStream();
				JSONObject jo = GetJsonHelper.readStream(in);
				System.out.println(jo);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
