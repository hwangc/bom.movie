package dbctrl.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Dao {
	
	public void close(PreparedStatement pstmt){
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(ResultSet rset){
		if(rset != null){
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract void insert(Connection con,Object obj) throws Exception;
	public abstract void update(Connection con,Object obj) throws Exception;
	public abstract void delete(Connection con,Object obj) throws Exception;
	public abstract Object select(Connection con,Object obj) throws Exception;
	public abstract ArrayList<Object> selectAllByUsers(Connection con) throws Exception;
	
}