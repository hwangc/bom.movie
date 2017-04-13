package web.dispatcher;

import java.io.UnsupportedEncodingException;

public class Util {
	public static String convertKr(String str){
		String result = "";
		try {
			result = 
			new String(str.getBytes("8859_1"),"KSC5601");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
