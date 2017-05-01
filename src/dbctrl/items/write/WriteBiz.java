package dbctrl.items.write;

import java.sql.Connection;
import java.util.ArrayList;

import dbctrl.interfaces.Biz;

public class WriteBiz extends Biz {
	WriteDao dao;
	 
	public WriteBiz() {
		dao = new WriteDao();
	}
	
	@Override
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

	@Override
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

	@Override
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

	@Override
	public Object get(Object obj) throws Exception {
		Object wr = null;
		Connection con = getConnection();
		
		try {
			wr = dao.select(con, obj);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return wr;
	}

	public ArrayList<Object> getAfterID(String ID, int count) throws Exception {
		ArrayList<Object> wrs = null;
		Connection con = getConnection();
		
		try {
			wrs = dao.selectAfterID(con, ID, count);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return wrs;
	}

	public ArrayList<Object> getAfterID(String ID, int count, String user) throws Exception {
		ArrayList<Object> wrs = null;
		Connection con = getConnection();
		
		try {
			wrs = dao.selectAfterID(con, ID, count, user);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return wrs;
	}	
	
	public WriteVo getFirstID() throws Exception {
		WriteVo wr = null;
		Connection con = getConnection();
		
		try {
			wr = (WriteVo)dao.selectFirstID(con);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return wr;
	}
	
	public WriteVo getFirstID(String user) throws Exception {
		WriteVo wr = null;
		Connection con = getConnection();
		
		try {
			wr = (WriteVo)dao.selectFirstID(con, user);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return wr;
	}	
	@Override
	public ArrayList<Object> get() throws Exception {
		ArrayList<Object> wrs = null;
		Connection con = getConnection();
		
		try
		{
			wrs = dao.selectAllByUsers(con);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			con.close();
		}
		
		return wrs;
	}
	
	public ArrayList<Object> getUser(Object obj) throws Exception {
		ArrayList<Object> wrs = null;
		Connection con = getConnection();
		
		try
		{
			//System.out.println("obj = "+obj);
			wrs = dao.selectAllWriteByUser(con, obj);
			//System.out.println("WB 105");
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			con.close();
		}
		
		return wrs;
	}

}
