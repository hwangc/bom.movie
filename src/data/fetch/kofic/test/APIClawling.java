package data.fetch.kofic.test;

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
		int movieCd = 20120001;
		String sMVDetailUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=430156241533f1d058c603178cc3ca0e&movieCd=";
		// Staff detail info
		// String sMVStaffDetailUrl =
		// "http://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleInfo.json?key=430156241533f1d058c603178cc3ca0e&peopleCd=20164556";
		String temp = null;
		String sMVDetailTempUrl = null;
		StringBuffer sbMVDetailUrl = new StringBuffer();
		BufferedWriter out = new BufferedWriter(new FileWriter("test.csv"));

		for (int i = 1; i <= 2; i++) {
			sMVDetailTempUrl = sMVDetailUrl + movieCd;
			URL uMVDetailUrl = new URL(sMVDetailTempUrl);
			BufferedReader bfMVDetailUrl = new BufferedReader(
					new InputStreamReader(uMVDetailUrl.openStream(), "UTF-8"));
			temp = bfMVDetailUrl.readLine();
			sbMVDetailUrl.append(temp);

			movieCd++;
			out.write(temp);
		}
		out.close();
		System.out.println(sbMVDetailUrl);

	}

}
