package dbctrl.items.user;

import java.sql.Connection;
import java.util.ArrayList;
import dbctrl.interfaces.Biz;

/**
 * 트랜젝션 처리하는 역할
 * */
public class UserBiz extends Biz {
	UserDao dao;
	
	public UserBiz() {
		dao = new UserDao();
	}

	public void register(Object obj) throws Exception {
		Connection con = getConnection();
		
		try {
			dao.insert(con, obj);	
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		} finally {
			close(con);			
		}
	}

	public void modify(Object obj) throws Exception {
		Connection con = getConnection();
		
		try {
			dao.update(con, obj);	
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		} finally { 
			close(con);			
		}
	}

/*
	public void modifyimg(Object obj) throws Exception {
		Connection con = getConnection();
		
		try {
			dao.updateimg(con, obj);	
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		} finally { 
			close(con);			
		}
	}*/
	public void remove(Object obj) throws Exception {
		Connection con = getConnection();
		
		try {
			dao.delete(con, obj);	
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		} finally { 
			close(con);			
		}
	}

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

	public ArrayList<Object> get() throws Exception {
		ArrayList<Object> users = null;
		Connection con = getConnection();
		
		try {
			users = dao.selectAllByUsers(con);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return users;
	}

}
