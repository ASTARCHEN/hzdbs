package hzdbs;

import com.snowland.beans.Common;
import com.snowland.server.Server;

public class TestServer {
	public static void main(String[] args) {
		Server server = new Server(Common.PORT);
		try {
			server.oneServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
