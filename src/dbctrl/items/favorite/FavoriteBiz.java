package dbctrl.items.favorite;

import java.sql.Connection;
import java.util.ArrayList;
import dbctrl.interfaces.Biz;

/**
 * 트랜젝션 처리하는 역할
 * */
public class FavoriteBiz extends Biz {
	FavoriteDao dao;
	
	public FavoriteBiz() {
		dao = new FavoriteDao();
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

	public void modify(Object obj) throws Exception {}

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
		return null;
	}

	public ArrayList<Object> get() throws Exception {
		return null;
	}
	
	/**
	 * Get movies only number of count
	 * @param ID
	 * @param page
	 * @return favoriteArray
	 * @throws Exception
	 */
	public ArrayList<Object> get(String user, String ID, int count) throws Exception {
		
		ArrayList<Object> favoriteArray = null;
		Connection con = getConnection();
		
		try{
			favoriteArray = dao.select(con, user, ID, count);
		} catch (Exception e){
			throw e;
		} finally {
			close(con);
		}
		
		return favoriteArray;
	}
	
	public FavoriteVo getFirstID() throws Exception {
		FavoriteVo wr = null;
		Connection con = getConnection();
		
		try {
			wr = (FavoriteVo)dao.selectFirstID(con);			
		} catch (Exception e) {
			throw e;
		} finally {
			close(con);
		}
		
		return wr;
	}
}
