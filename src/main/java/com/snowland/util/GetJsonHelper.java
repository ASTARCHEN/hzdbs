package com.snowland.util;

import java.io.InputStream;

import org.json.JSONObject;

public class GetJsonHelper {
	/**
	 * @功能 读取流
	 * @param inStream
	 * @return String
	 * @throws Exception
	 */
	public static JSONObject readStream(InputStream in) throws Exception {
		if (null == in)
			return null;
		StringBuffer out = new StringBuffer();
		int BUFFER_SIZE = 4096;
		byte[] b = new byte[BUFFER_SIZE];
		int n = 0;

		while(true){
			n = in.read(b);
			out.append(new String(b,0,n));
			if(n < BUFFER_SIZE) {
				break;
			}
		}
		return new JSONObject(out.toString());

	}

}
