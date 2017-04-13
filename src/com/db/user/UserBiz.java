package com.db.user;

import java.sql.Connection;
import java.util.ArrayList;

import com.db.frame.Biz;
import com.db.vo.User;

/**
 * Ʈ������ ó���ϴ� ����
 * */
public class UserBiz extends Biz {
	UserDao dao;
	
	//�����ڷ� �������̿� ��ü ������ְ� ����
	public UserBiz() {
		dao = new UserDao();
	}

	public void register(Object obj) throws Exception {
		//����� �������� Ŀ�ؼ��� �׳� ȣ���ؼ� �ٷ� ���� ��������
		Connection con = getConnection();
		
		try {
			dao.insert(con, obj);	
			//��� �μ�Ʈ�� �����ϸ� Ŀ���ϰ�
			con.commit();
		} catch (Exception e) {
			//�׷��� ������ ���󺹱� -> �̰� �ٷ� Ʈ������ ó��
			con.rollback();
			throw e;
		} finally {
			//Ŀ�ؼ� ����� �ݵ�� Ŀ�ؼ� ��������� 
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
