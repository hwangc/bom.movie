package com.db.user;

import java.sql.Connection;
import java.util.ArrayList;

import com.db.frame.Biz;
import com.db.vo.User;

/**
 * 트랜젝션 처리하는 역할
 * */
public class UserBiz extends Biz {
	UserDao dao;
	
	//생성자로 유저디에이오 객체 만들어주고 시작
	public UserBiz() {
		dao = new UserDao();
	}

	public void register(Object obj) throws Exception {
		//비즈에서 만들어놓은 커넥션을 그냥 호출해서 바로 갖다 쓸수있음
		Connection con = getConnection();
		
		try {
			dao.insert(con, obj);	
			//디비에 인서트가 성공하면 커밋하고
			con.commit();
		} catch (Exception e) {
			//그렇지 않으면 원상복구 -> 이게 바로 트렌젝션 처리
			con.rollback();
			throw e;
		} finally {
			//커넥션 사용후 반드시 커넥션 끊어줘야함 
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
			users = dao.select(con);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return users;
	}

}
