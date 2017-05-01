package dbctrl.items.movie;

import java.sql.Connection;
import java.util.ArrayList;
import dbctrl.interfaces.Biz;

/**
 * 트랜젝션 처리하는 역할
 * */
public class MovieBiz extends Biz {
	MovieDao dao;
	
	public MovieBiz() {
		dao = new MovieDao();
	}

	public void register(Object obj) throws Exception {}
	public void modify(Object obj) throws Exception {}
	public void remove(Object obj) throws Exception {}

	/**
	 * Get just one movie
	 * */
	public Object get(Object obj) throws Exception {
		Object user = null;
		Connection con = getConnection();
		
		try {
			user = dao.select(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return user;
	}
	
	/**
	 * Get popular movies by high score (main.jsp)
	 * */
	public ArrayList<Object> getPopularByStar() throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectAllByStar(con);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}
	
	/**
	 * Get popular movies by count of posts (main.jsp)
	 * */
	public ArrayList<Object> getPopularByWrite() throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectAllByWrite(con);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}
	
	/**
	 * Get popular movies by count of posts (main.jsp)
	 * */
	public ArrayList<Object> getRecommendByUser(Object obj) throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectAllForRecomByUser(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}
	
	/**
	 * Get all movies
	 * */
	public ArrayList<Object> get() throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectAllByUsers(con);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}
	
	/**
	 * Get all movies by user for favorite (favorite.jsp)
	 * */
	public ArrayList<Object> getAllByUser(Object obj) throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectAllByUser(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}

	/**
	 * Get all movies by Korean (navbar.jsp)
	 * */
	public ArrayList<Object> getSearchKor(Object obj) throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectSearchKor(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}
	
	/**
	 * Get all movies by English (navbar.jsp)
	 * */
	public ArrayList<Object> getSearchEng(Object obj) throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectSearchEng(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}
	
	public ArrayList<Object> getmapcount(Object obj) throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectMapCount(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}
	
	public ArrayList<Object> getUserCount() throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try
		{
			movies = dao.selcetUserCount(con);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			con.close();
		}
		
		return movies;
	}
	
	public ArrayList<Object> getUserCountM() throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try
		{
			movies = dao.selcetUserCountM(con);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			con.close();
		}
		
		return movies;
	}
}
