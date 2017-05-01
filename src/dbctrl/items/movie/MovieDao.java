package dbctrl.items.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import dbctrl.interfaces.Dao;


/**
 * 디비와 연결하는 역할
 * */
public class MovieDao extends Dao {
	java.util.Date uDate;
	java.sql.Date sDate;

	public void insert(Connection con, Object obj) throws Exception {}
	public void update(Connection con, Object obj) throws Exception {}
	public void delete(Connection con, Object obj) throws Exception {}

	public Object select(Connection con, Object obj) throws Exception {
		Object movie = null;
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectMovie);
		pstmt.setInt(1, Integer.parseInt(obj.toString()));
		ResultSet rset = pstmt.executeQuery();
		
		if(rset.next()){
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle");
			String mEngTitle = rset.getString("mEnTitle");
			String mGenre = rset.getString("mGenre");
			Date mOpen = rset.getDate("mOpen");
			int mTotalView = rset.getInt("mTotalView");
			String mCountry = rset.getString("mCountry");
			String mPoster = rset.getString("mPoster");
			String mScore = rset.getString("mScore");
			int nCode = rset.getInt("nCode");
			
			movie = new MovieVo(mCode, mTitle, mEngTitle, mGenre, mOpen, mTotalView, mCountry, mPoster, mScore, nCode);
		}
		close(rset);
		close(pstmt);
		
		return movie;
	}
	
	public ArrayList<Object> selectAllByStar(Connection con) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectAllMoviesByStar);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle");
			String mEngTitle = rset.getString("mEnTitle");
			String mGenre = rset.getString("mGenre");
			Date mOpen = rset.getDate("mOpen");
			int mTotalView = rset.getInt("mTotalView");
			String mCountry = rset.getString("mCountry");
			String mPoster = rset.getString("mPoster");
			String mScore = rset.getString("mScore");
			int nCode = rset.getInt("nCode");
			movie = new MovieVo(mCode, mTitle, mEngTitle, mGenre, mOpen, mTotalView, mCountry, mPoster, mScore, nCode);
			movies.add(movie);
		}
		close(rset);
		close(pstmt);
		
		return movies;
	}
	
	public ArrayList<Object> selectAllByWrite(Connection con) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectAllMoviesByWrite);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle");
			String mEngTitle = rset.getString("mEnTitle");
			String mGenre = rset.getString("mGenre");
			Date mOpen = rset.getDate("mOpen");
			int mTotalView = rset.getInt("mTotalView");
			String mCountry = rset.getString("mCountry");
			String mPoster = rset.getString("mPoster");
			String mScore = rset.getString("mScore");
			int nCode = rset.getInt("nCode");
			movie = new MovieVo(mCode, mTitle, mEngTitle, mGenre, mOpen, mTotalView, mCountry, mPoster, mScore, nCode);
			movies.add(movie);
		}
		close(rset);
		close(pstmt);
		
		return movies;
	}
	
	public ArrayList<Object> selectAllForRecomByUser(Connection con, Object obj) throws Exception {
	      ArrayList<Object> moviesIncluded = new ArrayList<>();
	      PreparedStatement pstmt = con.prepareStatement(MovieSql.selectAllMoviesForRecom);
	      pstmt.setString(1, obj.toString());
	      pstmt.setString(2, obj.toString());
	      pstmt.setString(3, obj.toString());
	      pstmt.setString(4, obj.toString());
	      ResultSet rset = pstmt.executeQuery();
	      
	      while(rset.next()) {
	         MovieVo movie = null;
	         int mCode = rset.getInt("mCode");
	         moviesIncluded.add(mCode);
	      }
	      close(rset);
	      close(pstmt);
	      
	      ArrayList<Object> moviesWatched = new ArrayList<>();
	      pstmt = con.prepareStatement(MovieSql.selectAllWatchedMovies);
	      pstmt.setString(1, obj.toString());
	      pstmt.setString(2, obj.toString());
	      rset = pstmt.executeQuery();
	      
	      while(rset.next()) {
	         MovieVo movie = null;
	         int mCode = rset.getInt("mCode");
	         moviesWatched.add(mCode);
	      }
	      close(rset);
	      close(pstmt);
	      
	      for(Object value1 : moviesIncluded) {
	         for(Object value2 : moviesWatched) {
	            if(value1 == value2) {
	               moviesIncluded.remove(value1);
	            }
	         }
	      }
	      
	      ArrayList<Object> movies = new ArrayList<>();
	      for(int i=0; i<30; i++) {
	         MovieVo movie = (MovieVo) select(con, moviesIncluded.get(i));
	         movies.add(movie);
	      }
	    
	      return movies;
	   }
	
	public ArrayList<Object> selectAllByUser(Connection con, Object obj) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.SelectAllMoviesWithFav);
		pstmt.setString(1, obj.toString());
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle").replaceAll("\"","");
			String mEngTitle = (rset.getString("mEnTitle") == null)?" ":rset.getString("mEnTitle").replaceAll("\"","");
			String mGenre = rset.getString("mGenre");
			Date mOpen = rset.getDate("mOpen");
			int mTotalView = rset.getInt("mTotalView");
			String mCountry = rset.getString("mCountry");
			String mPoster = rset.getString("mPoster");
			String mScore = rset.getString("mScore");
			int nCode = rset.getInt("nCode");
			int fCode = rset.getInt("fCode");
			movie = new MovieVo(mCode, mTitle, mEngTitle, mGenre, mOpen, mTotalView, mCountry, mPoster, mScore, nCode, fCode);
			movies.add(movie);
		}
		close(rset);
		close(pstmt);
		
		return movies;
	}
	
	@Override
	public ArrayList<Object> selectAllByUsers(Connection con) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectAllMoviesByStar);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle");
			String mEngTitle = rset.getString("mEnTitle");
			String mGenre = rset.getString("mGenre");
			Date mOpen = rset.getDate("mOpen");
			int mTotalView = rset.getInt("mTotalView");
			String mCountry = rset.getString("mCountry");
			String mPoster = rset.getString("mPoster");
			String mScore = rset.getString("mScore");
			int nCode = rset.getInt("nCode");
			int fCode = rset.getInt("fCode");
			movie = new MovieVo(mCode, mTitle, mEngTitle, mGenre, mOpen, mTotalView, mCountry, mPoster, mScore, nCode, fCode);
			movies.add(movie);
		}
		close(rset);
		close(pstmt);
		
		return movies;
	}

	public ArrayList<Object> selectSearchKor(Connection con, Object obj) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectGetSerachMovieKor);
		pstmt.setString(1, obj.toString()+"%");
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle");
			String mEngTitle = rset.getString("mEntitle");
			Date mOpen = rset.getDate("mOpen");
			movie = new MovieVo(mCode, mTitle, mEngTitle, mOpen);
			movies.add(movie);
		}
		close(rset);
		close(pstmt);
		
		return movies;
	}
	
	public ArrayList<Object> selectSearchEng(Connection con, Object obj) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectGetSerachMovieEng);
		pstmt.setString(1, obj.toString()+"%");
		pstmt.setString(2, obj.toString()+"%");
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle");
			String mEngTitle = rset.getString("mEntitle");
			Date mOpen = rset.getDate("mOpen");
			movie = new MovieVo(mCode, mTitle, mEngTitle, mOpen);
			movies.add(movie);
		}
		close(rset);
		close(pstmt);
		
		return movies;
	}
	
	public ArrayList<Object> selectMapCount(Connection con, Object obj) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		String email = (String) obj;
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectMapCount);
		pstmt.setString(1, email);
		pstmt.setString(2, email);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int countrycount = rset.getInt("COUNTRYCOUNT");
			String countrycode = rset.getString("COUNTRYCODE");
			movie = new MovieVo(countrycount,countrycode);
			movies.add(movie);
		}
		close(rset);
		close(pstmt);
		
		return movies;
	}
	
	public ArrayList<Object> selcetUserCount(Connection con) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectUserCount);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			MovieVo movie = null;
			String mgenre = rset.getString("MGENRE");
			int wcount = rset.getInt("WOMANCOUNT");
			int mcount = rset.getInt("MANCOUNT");
			movie = new MovieVo(mgenre, wcount, mcount);
			
			movies.add(movie);
		}
		return movies;
	}
	
	public ArrayList<Object> selcetUserCountM(Connection con) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(MovieSql.selectUserCount);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			MovieVo movie = null;
			String mgenre = rset.getString("MGENRE");
			int wcount = rset.getInt("WOMANCOUNT");
			int mcount = rset.getInt("MANCOUNT");
			movie = new MovieVo(mgenre, wcount, mcount);
			
			movies.add(movie);
		}
		return movies;
	}
}
