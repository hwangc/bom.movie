package chrome;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import chrome.NaverImg.whichOS;

public class MovieInfo {
	
	private static int movieCount = 40000;
	
	protected static class Data {
		private static final String ClientID = "TKJl4cSjK1WjO5Z64dan";
		private static final String ClientSecret = "w5fz9r5yf4";
		private static String APIUrl = "https://openapi.naver.com/v1/search/movie.json?";
		private static String IMGUrl = "http://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode=";
		private static String title = null;
		private static int movieCD = 0;
		private static String openDT = null;
		private static String resultQuery = null;
		private static String resultQueryLink = null;
		private static String resultQueryDirector = null;
		private static String resultQueryUserRating = null;
		private static String resultQueryMoviceCDQuery = null;
		
		private static String insertQuery = "INSERT INTO TB_IMAGE(MCODE, MPOSTER, MSCORE, NCODE, MTITLE) "
				+ "SELECT ?,?,?,?,? FROM dual "
				+ "WHERE NOT EXISTS (SELECT * FROM TB_IMAGE WHERE (MCODE = ?))";
		private static String selectQuery = "SELECT MOVIECD,MOVIENM,OPENDT,DIRECTORS FROM NAVER WHERE ROWNUM<=? ORDER BY MOVIECD DESC";
		private static String resumePointQuery = "SELECT MCODE FROM (SELECT MCODE FROM TB_IMAGE ORDER BY MCODE ASC) WHERE ROWNUM = 1";
		private static String resumeSelectQuery = "SELECT MOVIECD,MOVIENM,OPENDT,DIRECTORS FROM NAVER WHERE MOVIECD < ? AND ROWNUM<= ? ORDER BY MOVIECD DESC";
		
		public static String getWhichPosterPath() {
			String result = "";
			
			if((NaverImg.os == whichOS.WINDOWS)) {
				result = "C:\\Projects\\poster\\";
			} else if((NaverImg.os == whichOS.MAC)) {			
				result = "img/";
			} else if((NaverImg.os == whichOS.LINUX)) {
				result = "/home/bom_david/Documents/posters/";
			}
			
			return result;
		}
		
		public static String getResultQueryLink() {
			return resultQueryLink;
		}
		public static void setResultQueryLink(String resultQueryLink) {
			Data.resultQueryLink = resultQueryLink;
		}
		public static String getResultQueryDirector() {
			return resultQueryDirector;
		}
		public static void setResultQueryDirector(String resultQueryDirector) {
			Data.resultQueryDirector = resultQueryDirector;
		}
		public static String getResultQueryUserRating() {
			return resultQueryUserRating;
		}
		public static void setResultQueryUserRating(String resultQueryUserRating) {
			Data.resultQueryUserRating = resultQueryUserRating;
		}
		public static String getResultQuery() {
			return resultQuery;
		}
		public static void setResultQuery(String resultQuery) {
			Data.resultQuery = resultQuery;
		}
		public static int getMovieCD() {
			return movieCD;
		}
		public static void setMovieCD(int movieCD) {
			Data.movieCD = movieCD;
		}
		public static String getTitle() {
			return title;
		}
		public static void setTitle(String title) {
			Data.title = title.replace("'", "''");
		}
		public static String getOpenDT() {
			return openDT;
		}
		public static void setOpenDT(String openDT) {
			Data.openDT = openDT.substring(0, 4);
		}
		public static String getIMGUrl() {
			return IMGUrl;
		}
		public static void setIMGUrl(String iMGUrl) {
			IMGUrl = iMGUrl;
		}
		public static String getResultQueryMovieCD() {
			return resultQueryMoviceCDQuery;
		}
		public static void setResultQueryMovieCD(String resultQueryMoviceCD) {
			Data.resultQueryMoviceCDQuery = resultQueryMoviceCD;
		}
	}
	
	public static void StartFetchImage() {
		
		String posterImgSrc = "";
		String userRate = "";
		String naverCode = "";
		String movieTitle = "";
		int movieCode = 0;
		Connection insertCon = null;
		Connection selectCon = null;
		ResultSet rsFromMovieDB = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			selectCon = GetSelectCon();

			rsFromMovieDB = GetResultSet(selectCon);
			
			while (rsFromMovieDB.next()) {
				
				MovieInfo.Data.setTitle(rsFromMovieDB.getString("MOVIENM"));
				MovieInfo.Data.setMovieCD(rsFromMovieDB.getInt("MOVIECD"));
				MovieInfo.Data.setOpenDT(rsFromMovieDB.getString("OPENDT"));
				
				System.out.println("\n\n========= "+MovieInfo.Data.getTitle()+" ========");
				
				if( MovieInfo.GetJSONFromApi() ) {
					
					// Store Image
					MovieInfo.SetJSONFromAPI();
					
					if(MovieImg.GetFromWebByID("targetImage")) {					
						System.out.println( "= [SUCCESS] 이미지가 저장 되었습니다!");
						// Insert ORA
						insertCon = GetInsertCon();
						posterImgSrc = MovieInfo.Data.getResultQueryMovieCD()+".jpg";
						movieTitle = MovieInfo.Data.getTitle();
						userRate = MovieInfo.Data.getResultQueryUserRating();
						movieCode = MovieInfo.Data.getMovieCD();
						naverCode = MovieInfo.Data.getResultQueryMovieCD();
						PreparedStatement movieInsertStmt = insertCon.prepareStatement(MovieInfo.Data.insertQuery);
						String insertQuery = "INSERT INTO TB_IMAGE(MCODE, MPOSTER, MSCORE, NCODE, MTITLE) "
								+ "SELECT '"+movieCode+"','"+posterImgSrc+"','"+userRate+"','"+naverCode+"','"+movieTitle+"' FROM dual "
								+ "WHERE NOT EXISTS (SELECT * FROM TB_IMAGE WHERE (MCODE = '"+movieCode+"'))";
						
						movieInsertStmt.setInt(1, movieCode);
						movieInsertStmt.setString(2, posterImgSrc);
						movieInsertStmt.setString(3, userRate);
						movieInsertStmt.setString(4, naverCode);
						movieInsertStmt.setString(5, movieTitle);
						movieInsertStmt.setInt(6, movieCode);
						
						System.out.println("Query: "+insertQuery);
						movieInsertStmt.executeUpdate();
						
						System.out.println("= [SUCCESS] DB가 저장 되었습니다!");
						insertCon.close();
					} else {
						System.out.println("= [ERROR] API 정보는 있지만 이미지가 없습니다!");
					}
					// Close the insert connection					
				} else {
					
					System.out.println("= [ERROR] API 정보가 없습니다!");
				}
				System.out.println("-------------------------------------------------\n\n");
			}
			// Close the select connection			
			selectCon.close();
			System.out.println("---------------------------All Done!----------------------\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet GetFromOra() {

		Connection selectCon = null;
		ResultSet rs = null;
		
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	    	
	    	selectCon = GetSelectCon();
	    	
			rs = GetResultSet(selectCon);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	    
	    
	    return rs;
	}

	private static ResultSet GetResultSet(Connection selectCon) throws SQLException {
		
		ResultSet rsMovieSelect = null;
		ResultSet rsResumePoint = null;
		PreparedStatement movieSelectstmt = null;
		Statement resumePointQuery = null;
		
		if(NaverImg.IsResume) {
			
			resumePointQuery = selectCon.createStatement();
			rsResumePoint = resumePointQuery.executeQuery(MovieInfo.Data.resumePointQuery);
			rsResumePoint.next();
			
			movieSelectstmt = selectCon.prepareStatement(MovieInfo.Data.resumeSelectQuery);
			movieSelectstmt.setString(1, rsResumePoint.getString("MCODE"));
			movieSelectstmt.setInt(2, MovieInfo.movieCount);
			rsMovieSelect = movieSelectstmt.executeQuery();
			
		} else {
			movieSelectstmt = selectCon.prepareStatement(MovieInfo.Data.selectQuery);
			movieSelectstmt.setInt(1, MovieInfo.movieCount);
			rsMovieSelect = movieSelectstmt.executeQuery();	
		}
		
		return rsMovieSelect;
	}

	private static Connection GetInsertCon() throws SQLException {
		Connection insertCon;
		if(!NaverImg.AmIAtHome) {
			insertCon = DriverManager.getConnection("jdbc:oracle:thin:@ambari.bom.movie:1521:XE","bom_ora","Bommovie123");
		} else {
			insertCon = DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.4:1521:XE","dave","dave");
		}
		return insertCon;
	}

	private static Connection GetSelectCon() throws SQLException {
		Connection selectCon;
		if(!NaverImg.AmIAtHome) {
			selectCon = DriverManager.getConnection("jdbc:oracle:thin:@ambari.bom.movie:1521:XE","bom_ora","Bommovie123");
		} else {
			selectCon = DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.4:1521:XE","dave","dave");
		}
		return selectCon;
	}
	
	public static boolean GetJSONFromApi() {
		
		boolean result = true;
		BufferedReader queryResult = null;
		String oneLine = null;
		
		try {
			String movieQuery = URLEncoder.encode(MovieInfo.Data.getTitle(), "UTF-8");
			int year = Integer.parseInt(MovieInfo.Data.getOpenDT());
			StringBuffer response = new StringBuffer();
			URL url = null;
			
			if(year == 2099) {				
				url = new URL(MovieInfo.Data.APIUrl+"query="+movieQuery);
			} else {
//				url = new URL(MovieInfo.Data.APIUrl+"query="+movieQuery+"&yearfrom="+year+"&yearto="+year);
				url = new URL(MovieInfo.Data.APIUrl+"query="+movieQuery);
			}
			
			HttpURLConnection httpCon = (HttpURLConnection)url.openConnection();
			httpCon.setRequestMethod("GET");
			httpCon.setRequestProperty("X-Naver-Client-Id", MovieInfo.Data.ClientID);
			httpCon.setRequestProperty("X-Naver-Client-Secret", MovieInfo.Data.ClientSecret);
			
			int responseCode = httpCon.getResponseCode();		
			
			if(responseCode==200) { 
				// �젙�긽 �샇異�
				queryResult = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			} else {  
				result = false;
			}
			
			while ((oneLine = queryResult.readLine()) != null) {
				response.append(oneLine);
			}
			
			MovieInfo.Data.setResultQuery(response.toString());
			
			if( MovieInfo.GetMovieJson() == null) {
				result = false;
			}
					
			queryResult.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static void SetJSONFromAPI() {
		JSONObject item = null;
		String movieCD = null;
		
		try {
			item = MovieInfo.GetMovieJson();

			MovieInfo.Data.setResultQueryUserRating(item.getString("userRating"));
			MovieInfo.Data.setResultQueryDirector(item.getString("director"));
			movieCD = MovieInfo.parseMovieCDFromQuery(item.getString("link"));
			MovieInfo.Data.setResultQueryMovieCD(movieCD);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static JSONObject GetMovieJson() {
		
		JSONObject movieJson = null;
		JSONObject item = null;
		JSONArray items = null;
		
		try {
			movieJson = new JSONObject(MovieInfo.Data.getResultQuery());
			items = movieJson.getJSONArray("items");
			
			if(items != null && items.length() > 0) {				
				item = movieJson.getJSONArray("items").getJSONObject(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	private static String parseMovieCDFromQuery(String query) {
		
		String movieCD = query.substring(query.lastIndexOf("=") + 1);

		return movieCD;
	}

}