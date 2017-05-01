package dbctrl.items.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dbctrl.interfaces.Dao;

/**
 * 디비와 연결하는 역할
 * */
public class UserDao extends Dao {
	java.util.Date uDate;
	java.sql.Date sDate;

	public void insert(Connection con, Object obj) throws Exception {
		UserVo user = (UserVo) obj;
		
		//Convert from java.util.Date to java.sql.Date
		uDate = user.getBdate();
		sDate = new java.sql.Date(uDate.getTime());
		
		PreparedStatement pstmt = con.prepareStatement(UserSql.insertUser);
		pstmt.setString(1, user.getEmail());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getNickname());
		pstmt.setInt(4, user.getGender());
		pstmt.setDate(5, sDate);
		pstmt.executeUpdate();
		close(pstmt);
	}
	
	public void update(Connection con, Object obj) throws Exception {
		UserVo user = (UserVo) obj;
		
		//Convert from java.util.Date to java.sql.Date
		uDate = user.getBdate();
		sDate = new java.sql.Date(uDate.getTime());
		
		PreparedStatement pstmt = con.prepareStatement(UserSql.updateUser);
		pstmt.setString(1, user.getPwd());
		pstmt.setString(2, user.getNickname());
		pstmt.setInt(3, user.getGender());
		pstmt.setDate(4, sDate);
		pstmt.setString(5, user.getEmail());
		pstmt.executeUpdate();
		close(pstmt);
	}

/*	public void updateimg(Connection con, Object obj) throws Exception {
		UserVo user = (UserVo) obj;
				
		PreparedStatement pstmt = con.prepareStatement(UserSql.updateimage);
		pstmt.setInt(1, user.getUserid());
		pstmt.setString(2, user.getEmail());
		pstmt.executeUpdate();
		close(pstmt);
	}*/
	public void delete(Connection con, Object obj) throws Exception {
		String email = (String) obj;
		PreparedStatement pstmt = con.prepareStatement(UserSql.deleteUser);
		pstmt.setString(1, email);
		pstmt.setString(1, obj.toString());
		pstmt.executeUpdate();
		close(pstmt);
	}

	public Object select(Connection con, Object obj) throws Exception {
		Object user = null;
		PreparedStatement pstmt = con.prepareStatement(UserSql.selectUser);
		pstmt.setString(1, obj.toString());
		ResultSet rset = pstmt.executeQuery();
		rset.next();
				
		String email = rset.getString("uemail");
		String pwd = rset.getString("upwd");
		String nickname = rset.getString("unickname");
		int gender = rset.getInt("ugender");
		Date bdate = rset.getDate("ubdate");
		
		user = new UserVo(email, pwd, nickname, gender, bdate);
		
		close(rset);
		close(pstmt);
		
		return user;
	}

	public ArrayList<Object> selectAllByUsers(Connection con) throws Exception {
		ArrayList<Object> users = new ArrayList<>();
		/*PreparedStatement pstmt = con.prepareStatement(UserSql.selectAllUser);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			UserVo user = null;
			String id = rset.getString("ID");
			String pwd = rset.getString("PWD");
			String name = rset.getString("NAME");
			user = new UserVo(id, pwd, name);
			users.add(user);
		}
		
		close(rset);
		close(pstmt);*/
		
		return users;
	}

}
