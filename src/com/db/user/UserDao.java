package com.db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.db.frame.Dao;
import com.db.frame.Sql;
import com.db.vo.User;

/**
 * 디비와 연결하는 역할
 * */
public class UserDao extends Dao {

	public void insert(Connection con, Object obj) throws Exception {
		User user = (User) obj;
		PreparedStatement pstmt = con.prepareStatement(Sql.insertCust);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.executeUpdate();
		close(pstmt);
	}
	
	public void update(Connection con, Object obj) throws Exception {
		User user = (User) obj;
		PreparedStatement pstmt = con.prepareStatement(Sql.updateCust);
		pstmt.setString(1, user.getPwd());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getId());
		pstmt.executeUpdate();
		close(pstmt);
	}

	public void delete(Connection con, Object obj) throws Exception {
		//String id = (String) obj;
		PreparedStatement pstmt = con.prepareStatement(Sql.deleteCust);
		//pstmt.setString(1, id);
		pstmt.setString(1, obj.toString());
		pstmt.executeUpdate();
		close(pstmt);
	}

	public Object select(Connection con, Object obj) throws Exception {
		Object user = null;
		PreparedStatement pstmt = con.prepareStatement(Sql.selectCust);
		pstmt.setString(1, obj.toString());
		ResultSet rset = pstmt.executeQuery();
		rset.next();
		
		String id = rset.getString("ID");
		String pwd = rset.getString("PWD");
		String name = rset.getString("NAME");
		user = new User(id, pwd, name);
		
		close(rset);
		close(pstmt);
		
		return user;
	}

	public ArrayList<Object> select(Connection con) throws Exception {
		ArrayList<Object> users = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(Sql.selectAllCust);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			User user = null;
			String id = rset.getString("ID");
			String pwd = rset.getString("PWD");
			String name = rset.getString("NAME");
			user = new User(id, pwd, name);
			users.add(user);
		}
		
		close(rset);
		close(pstmt);
		
		return users;
	}

}
