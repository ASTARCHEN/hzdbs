package hzdbs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.snowland.beans.Common;
import com.snowland.beans.ExQuestion;
import com.snowland.beans.Paper;
import com.snowland.beans.Student;
import com.snowland.client.Client;
import com.snowland.client.ClientJsonCreater;
import com.snowland.util.GetJsonHelper;
import com.snowland.util.SendHelper;

public class TestClientStudent {
	public static void main(String[] args) {
		try {
			Client client = new Client(Common.HOST, Common.PORT);
			Student stu = new Student("cxl");
			client.setUser(stu);
			client.login("123");
			try {
				String str = client.getResponse();
				System.out.println(str);
				while (true) {
					InputStream in = client.getSocket().getInputStream();
					JSONObject jo = GetJsonHelper.readStream(in);
					System.out.println(jo);
					JSONObject response = new JSONObject((String)jo.get("response"));
					if (response.get("tag").equals("paper")) {
						JSONArray stemArray = new JSONArray(response.get("stem").toString());
						List<ExQuestion> exlist = new ArrayList<>();
						for(Object o : stemArray) {
							ExQuestion ex = new ExQuestion();
							ex.setStem((String)o);
							// TODO
							ex.setSelfAnsaw("2");
							exlist.add(ex);
						}
						JSONObject part = new JSONObject();
						part.put("stem_and_ansaw", exlist.toString());
						ClientJsonCreater creater = new ClientJsonCreater(stu.getUsername(), stu.getPart(), Common.UPLOAD_PAPER, part);
						SendHelper.send(client.getSocket(), creater.createJson());
					}
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
