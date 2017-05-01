package dbctrl.items.write;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import dbctrl.interfaces.Dao;
import dbctrl.interfaces.Sql;
import dbctrl.items.movie.MovieSql;
import dbctrl.items.user.UserSql;
import dbctrl.items.user.UserVo;


/**
 * ���� �����ϴ� ����
 * */
public class WriteDao extends Dao {
	java.util.Date wDate;
	java.sql.Date sDate;

	@Override
	public void insert(Connection con, Object obj) throws Exception {
		WriteVo wr = (WriteVo) obj;
		
		PreparedStatement pstmt = con.prepareStatement(WriteSql.insertWrite);
		pstmt.setString(1, wr.getuEmail());
		pstmt.setInt(2, wr.getmCode());
		pstmt.setString(3, wr.getwContent());
		pstmt.setInt(4, wr.getwStar());
		pstmt.executeUpdate();
		close(pstmt);
	}

	@Override
	public void update(Connection con, Object obj) throws Exception {
		WriteVo wr = (WriteVo) obj;
		
		PreparedStatement pstmt = con.prepareStatement(WriteSql.updateWrite);
		pstmt.setString(1, wr.getuEmail());
		pstmt.setInt(2, wr.getmCode());
		pstmt.setString(3, wr.getwContent());
		pstmt.setInt(4, wr.getwStar());
		pstmt.setInt(5, wr.getwCode());
		pstmt.executeUpdate();
		close(pstmt);
	}

	@Override
	public void delete(Connection con, Object obj) throws Exception {
		String wCode =  (String) obj;
		PreparedStatement pstmt = con.prepareStatement(WriteSql.deleteWrite);
		pstmt.setString(1, wCode);
		//pstmt.setString(1, obj.toString());
		pstmt.executeUpdate();
		close(pstmt);
	}

	@Override
	public Object select(Connection con, Object obj) throws Exception {
		Object wr = null;
		PreparedStatement pstmt = con.prepareStatement(WriteSql.selectWrite);
		pstmt.setString(1, obj.toString());
		ResultSet rset = pstmt.executeQuery();
		
		rset.next();
		int wCode = rset.getInt("wCode");
		String uEmail = rset.getString("uEmail");
		int mCode = rset.getInt("mCode");
		String wContent = rset.getString("wContent");
		int wStar = rset.getInt("wStar");
		pstmt.executeUpdate();
		
		PreparedStatement mpstmt = con.prepareStatement(MovieSql.selectMovie);
		mpstmt.setInt(1, mCode);
		ResultSet mrset = mpstmt.executeQuery();
		
		mrset.next();
		String mTitle = mrset.getString("mTitle");
		
		mpstmt.executeUpdate();
		
		close(mrset);
		close(mpstmt);
		
    wr = new WriteVo(wCode, uEmail, mTitle, mCode, wContent, wStar);
		
		close(rset);
		close(pstmt);
        
    return wr;
	}
	
	public Object selectFirstID(Connection con) throws Exception {
		Object wr = null;
		PreparedStatement pstmt = con.prepareStatement(WriteSql.selectWriteFirstID);
		ResultSet rset = pstmt.executeQuery();
		
		rset.next();
		int wCode = rset.getInt("wCode");
		
        wr = new WriteVo(wCode, "", 0, "", 0);
        
		close(rset);
		close(pstmt);
        
        return wr;
	}
	
	public Object selectFirstID(Connection con, String user) throws Exception {
		Object wr = null;
		PreparedStatement pstmt = con.prepareStatement(WriteSql.selectWriteFirstIDByUser);
		pstmt.setString(1, user);
		ResultSet rset = pstmt.executeQuery();
		
		rset.next();
		int wCode = rset.getInt("wCode");
		
        wr = new WriteVo(wCode, "", 0, "", 0);
        
		close(rset);
		close(pstmt);
        
        return wr;
	}
	
	public ArrayList<Object> selectAfterID(Connection con, String ID, int count) throws Exception {
		ArrayList<Object> wrs = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(WriteSql.selectWriteAfterID);
		pstmt.setString(1, ID);
		pstmt.setInt(2, count);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			WriteVo wr = null;
			int wCode = rset.getInt("wcode");
			String uEmail = rset.getString("uemail");
			int mCode = rset.getInt("mcode");
			String wContent = rset.getString("wcontent");
			int wStar = rset.getInt("wstar");
			Date regdate = rset.getDate("wdate");
			int pwCode = rset.getInt("pwCode");

			PreparedStatement userPstmt = con.prepareStatement(UserSql.selectUser);
			userPstmt.setString(1, uEmail);
			ResultSet rsUser = userPstmt.executeQuery();
			rsUser.next();
			String wNickname = rsUser.getString("UNICKNAME");
			close(rsUser);
			close(userPstmt);
			
			PreparedStatement moviePstmt = con.prepareStatement(MovieSql.selectMovie);
			moviePstmt.setInt(1, mCode);
			ResultSet rsMovie = moviePstmt.executeQuery();
			String mTitle = null;
			rsMovie.next();
			mTitle = rsMovie.getString("MTITLE");
			close(rsMovie);
			close(moviePstmt);
			
			wr = new WriteVo(wCode, uEmail, wNickname, mCode, wContent, wStar, regdate, pwCode, mTitle);
			wrs.add(wr);

		}
		
		close(rset);
		close(pstmt);
		
		return wrs;
	}	
	
	public ArrayList<Object> selectAfterID(Connection con, String ID, int count, String user) throws Exception {
		ArrayList<Object> wrs = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(WriteSql.selectWriteAfterIDByUser);
		pstmt.setString(1, ID);
		pstmt.setInt(2, count);
		pstmt.setString(3, user);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			WriteVo wr = null;
			int wCode = rset.getInt("wcode");
			String uEmail = rset.getString("uemail");
			int mCode = rset.getInt("mcode");
			String wContent = rset.getString("wcontent");
			int wStar = rset.getInt("wstar");
			Date regdate = rset.getDate("wdate");
			int pwCode = rset.getInt("pwCode");

			PreparedStatement userPstmt = con.prepareStatement(UserSql.selectUser);
			userPstmt.setString(1, uEmail);
			ResultSet rsUser = userPstmt.executeQuery();
			rsUser.next();
			String wNickname = rsUser.getString("UNICKNAME");
			close(rsUser);
			close(userPstmt);
			
			PreparedStatement moviePstmt = con.prepareStatement(MovieSql.selectMovie);
			moviePstmt.setInt(1, mCode);
			ResultSet rsMovie = moviePstmt.executeQuery();
			String mTitle = null;
			rsMovie.next();
			mTitle = rsMovie.getString("MTITLE");
			close(rsMovie);
			close(moviePstmt);
			
			wr = new WriteVo(wCode, uEmail, wNickname, mCode, wContent, wStar, regdate, pwCode, mTitle);
			wrs.add(wr);

		}
		
		close(rset);
		close(pstmt);
		
		return wrs;
	}
	
	public ArrayList<Object> selectAllWriteByUser(Connection con, Object obj) throws Exception {
		String uEmail1 =  (String) obj;
		ArrayList<Object> wrs = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(WriteSql.selectAllWriteByUser);
		pstmt.setString(1, uEmail1);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			WriteVo wr = null;
			int wCode = rset.getInt("wcode");
			String uEmail = rset.getString("uemail");
			int mCode = rset.getInt("mcode");
			String wContent = rset.getString("wcontent");
			int wStar = rset.getInt("wstar");
			Date regdate = rset.getDate("wdate");
			int pwCode = rset.getInt("pwCode");

			PreparedStatement userPstmt = con.prepareStatement(UserSql.selectUser);
			userPstmt.setString(1, uEmail);
			ResultSet rsUser = userPstmt.executeQuery();
			rsUser.next();
			String wNickname = rsUser.getString("UNICKNAME");
			close(rsUser);
			close(userPstmt);
			
			PreparedStatement moviePstmt = con.prepareStatement(MovieSql.selectMovie);
			moviePstmt.setInt(1, mCode);
			ResultSet rsMovie = moviePstmt.executeQuery();
			String mTitle = null;
			rsMovie.next();
			mTitle = rsMovie.getString("MTITLE");
			close(rsMovie);
			close(moviePstmt);
			
			wr = new WriteVo(wCode, uEmail, wNickname, mCode, wContent, wStar, regdate, pwCode, mTitle);
			wrs.add(wr);

		}
		
		close(rset);
		close(pstmt);
		
		return wrs;
	}
	
	@Override
	public ArrayList<Object> selectAllByUsers(Connection con) throws Exception {
		ArrayList<Object> wrs = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(WriteSql.selectAllWriteByUsers);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			WriteVo wr = null;
			int wCode = rset.getInt("wcode");
			String uEmail = rset.getString("uemail");
			int mCode = rset.getInt("mcode");
			String wContent = rset.getString("wcontent");
			int wStar = rset.getInt("wstar");
			Date regdate = rset.getDate("wdate");
			int pwCode = rset.getInt("pwCode");

			PreparedStatement userPstmt = con.prepareStatement(UserSql.selectUser);
			userPstmt.setString(1, uEmail);
			ResultSet rsUser = userPstmt.executeQuery();
			rsUser.next();
			String wNickname = rsUser.getString("UNICKNAME");
			close(rsUser);
			close(userPstmt);
			
			PreparedStatement moviePstmt = con.prepareStatement(MovieSql.selectMovie);
			moviePstmt.setInt(1, mCode);
			ResultSet rsMovie = moviePstmt.executeQuery();
			String mTitle = null;
			rsMovie.next();
			mTitle = rsMovie.getString("MTITLE");
			close(rsMovie);
			close(moviePstmt);
			
			wr = new WriteVo(wCode, uEmail, wNickname, mCode, wContent, wStar, regdate, pwCode, mTitle);
			wrs.add(wr);

		}
		
		close(rset);
		close(pstmt);
		
		return wrs;
		
	}
}
