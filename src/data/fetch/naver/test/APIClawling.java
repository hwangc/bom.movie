package data.fetch.naver.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
import java.net.URL;

public class APIClawling {

	public static void main(String[] args) throws Exception {
		// get the json data from kofic api

		// Movie detail info
//		String ClientID = "TKJl4cSjK1WjO5Z64dan";
//		String ClientSecret = "w5fz9r5yf4";
		// TODO Access Movie with Naver API  
		String movieCd = "¹Ì³à";
		String sMVDetailUrl = "https://openapi.naver.com/v1/search/movie.json?query=";
		// Staff detail info
		// String sMVStaffDetailUrl =
		// "http://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleInfo.json?key=430156241533f1d058c603178cc3ca0e&peopleCd=20164556";
		String temp = null;
		String sMVDetailTempUrl = null;
		StringBuffer sbMVDetailUrl = new StringBuffer();
		BufferedWriter out = new BufferedWriter(new FileWriter("test_naver.csv"));

		for (int i = 1; i < 2; i++) {
			sMVDetailTempUrl = sMVDetailUrl + movieCd;
			URL uMVDetailUrl = new URL(sMVDetailTempUrl);
			BufferedReader bfMVDetailUrl = new BufferedReader(
					new InputStreamReader(uMVDetailUrl.openStream(), "UTF-8"));
			temp = bfMVDetailUrl.readLine();
			sbMVDetailUrl.append(temp);

			out.write(temp);
		}
		out.close();
		System.out.println(sbMVDetailUrl);

	}

}
