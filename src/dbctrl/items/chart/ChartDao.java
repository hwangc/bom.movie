package dbctrl.items.chart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbctrl.interfaces.Dao;

public class ChartDao extends Dao {

	@Override
	public void insert(Connection con, Object obj) throws Exception {

	}

	@Override
	public void update(Connection con, Object obj) throws Exception {

	}

	@Override
	public void delete(Connection con, Object obj) throws Exception {

	}

	@Override
	public Object select(Connection con, Object obj) throws Exception {
		return null;
	}

	@Override
	public ArrayList<Object> selectAllByUsers(Connection con) throws Exception {
		return null;
	}
	
		
	public ArrayList<Object> selectUserMovie(Connection con, Object obj) throws Exception {
		ArrayList<Object> charts = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(ChartSql.selectUserMovie);
		pstmt.setString(1, obj.toString());
		pstmt.setString(2, obj.toString());	
		ResultSet rset = pstmt.executeQuery();
			
		while(rset.next())
		{		
			ChartVo chart = null;
			String mgenre = rset.getString("MGENRE");
			int genrecount = rset.getInt("GENRECOUNT");
			chart = new ChartVo(mgenre, genrecount);
		
			charts.add(chart);
		}	
		close(rset);
		close(pstmt);
			
		return charts;
	}

	public ArrayList<Object> selectUserDirector(Connection con, Object obj) throws Exception {
		ArrayList<Object> charts = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(ChartSql.selectUserDirector);
		pstmt.setString(1, obj.toString());
		pstmt.setString(2, obj.toString());	
		ResultSet rset = pstmt.executeQuery();
	    
		while(rset.next())
		{		
			ChartVo chart = null;
			String sname = rset.getString("SNAME");
			int dcount = rset.getInt("DIRECTORCOUNT");
			int genrecount = 0;
			chart = new ChartVo(genrecount, sname, dcount);
		
			charts.add(chart);
		}	
		close(rset);
		close(pstmt);
			
		return charts;
	}

	public ArrayList<Object> selectUserActor(Connection con, Object obj) throws Exception {
		ArrayList<Object> charts = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(ChartSql.selectUserActor);
		pstmt.setString(1, obj.toString());
		pstmt.setString(2, obj.toString());	
		ResultSet rset = pstmt.executeQuery();
	    
		while(rset.next())
		{		
			ChartVo chart = null;
			String sname = null;
			String aname = rset.getString("ANAME");
			int acount = rset.getInt("ACTORCOUNT");
			chart = new ChartVo(sname, aname, acount);
		
			charts.add(chart);
		}	
		close(rset);
		close(pstmt);
			
		return charts;
	}
}
