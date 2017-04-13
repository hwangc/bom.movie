package data.naver.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIClawling {

	public static void main(String[] args) throws Exception {
		// get the json data from naver api

		// Movie detail info
		String ClientID = "TKJl4cSjK1WjO5Z64dan";
		String ClientSecret = "w5fz9r5yf4";
		
		BufferedReader bfMVDetail = null;
		String movieCd = URLEncoder.encode("미녀와 야수", "UTF-8");;
		String sMVDetailUrl = "https://openapi.naver.com/v1/search/movie.json?query="+movieCd;
		String inputLine = null;
		StringBuffer response = new StringBuffer();
		BufferedWriter out = new BufferedWriter(new FileWriter("test_naver.csv"));
		
		URL uMVDetailUrl = new URL(sMVDetailUrl);
		HttpURLConnection httpNvrSVRCon = (HttpURLConnection)uMVDetailUrl.openConnection();
        httpNvrSVRCon.setRequestMethod("GET");
        httpNvrSVRCon.setRequestProperty("X-Naver-Client-Id", ClientID);
        httpNvrSVRCon.setRequestProperty("X-Naver-Client-Secret", ClientSecret);
        
        int responseCode = httpNvrSVRCon.getResponseCode();		
        
        if(responseCode==200) { 
		    // 정상 호출
        	bfMVDetail = new BufferedReader(new InputStreamReader(httpNvrSVRCon.getInputStream()));
        } else {  
        	// 에러 발생
        	bfMVDetail = new BufferedReader(new InputStreamReader(httpNvrSVRCon.getErrorStream()));
        }
        
        while ((inputLine = bfMVDetail.readLine()) != null) {
            response.append(inputLine);
            out.write(response.toString());
		}
        
		out.close();
        bfMVDetail.close();
        System.out.println(response.toString());
	}

}
