package dbctrl.items.chart;

import java.sql.Connection;
import java.util.ArrayList;

import dbctrl.interfaces.Biz;

public class ChartBiz extends Biz {
    ChartDao dao;  
    
    public ChartBiz() {
		dao = new ChartDao();
	}
	
	@Override
	public void register(Object obj) throws Exception {

	}

	@Override
	public void modify(Object obj) throws Exception {

	}

	@Override
	public void remove(Object obj) throws Exception {

	}

	@Override
	public Object get(Object obj) throws Exception {
		return null;
	}

	@Override
	public ArrayList<Object> get() throws Exception {
		return null;
	}
	
	public ArrayList<Object> getUserMovie(Object obj) throws Exception {
		ArrayList<Object> movies = null;
		Connection con = getConnection();
		
		try {
			movies = dao.selectUserMovie(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return movies;
	}

	public ArrayList<Object> getUserDirector(Object obj)  throws Exception {
		ArrayList<Object> directors = null;
		Connection con = getConnection();
		
		try {
			directors = dao.selectUserDirector(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return directors;
	}

	public ArrayList<Object> getUserActor(Object obj) throws Exception {
		ArrayList<Object> actors = null;
		Connection con = getConnection();
		
		try {
			actors = dao.selectUserActor(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return actors;
	}

}
