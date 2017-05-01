package dbctrl.items.favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import dbctrl.interfaces.Dao;
import dbctrl.items.movie.MovieVo;

/**
 * 디비와 연결하는 역할
 * */
/**
 * @author student
 *
 */
/**
 * @author student
 *
 */
public class FavoriteDao extends Dao {

	public void insert(Connection con, Object obj) throws Exception {
		FavoriteVo fav = (FavoriteVo) obj;
		
		PreparedStatement pstmt = con.prepareStatement(FavoriteSql.insertFavorite);
		pstmt.setString(1, fav.getuEmail());
		pstmt.setInt(2, fav.getmCode());
		pstmt.executeUpdate();
		close(pstmt);
	}
	
	public void update(Connection con, Object obj) throws Exception {
	}

	public void delete(Connection con, Object obj) throws Exception {
		FavoriteVo fav = (FavoriteVo) obj;
		PreparedStatement pstmt = con.prepareStatement(FavoriteSql.deleteFavorite);
		pstmt.setString(1, fav.getuEmail());
		pstmt.setInt(2, fav.getmCode());
		pstmt.executeUpdate();
		close(pstmt);
	}
	
	public Object selectFirstID(Connection con) throws Exception {
		Object wr = null;
		PreparedStatement pstmt = con.prepareStatement(FavoriteSql.selectFirstID);
		ResultSet rset = pstmt.executeQuery();
		
		rset.next();
		int mCode = rset.getInt("mCode");
		
        wr = new FavoriteVo("",mCode);
        
		close(rset);
		close(pstmt);
        
        return wr;
	}
	
	public ArrayList<Object> selectAllByUsers(Connection con) throws Exception {
		return null;
	}

	public ArrayList<Object> select(Connection con) throws Exception {
		return null;
	}

	
	/**
	 * Select after ID only number of count
	 * @param con
	 * @param ID
	 * @param count
	 * @return wrs
	 * @throws Exception
	 */
	public ArrayList<Object> select(Connection con, String user, String ID, int count) throws Exception {
		ArrayList<Object> movies = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(FavoriteSql.selectAfterID);
		pstmt.setString(1, user);
		pstmt.setString(2, ID);
		pstmt.setInt(3, count);
		ResultSet rset = pstmt.executeQuery();
		
		while(rset.next()) {
			MovieVo movie = null;
			int mCode = rset.getInt("mCode");
			String mTitle = rset.getString("mTitle").replaceAll("\"","");
			String mEngTitle = (rset.getString("mEnTitle") == null)?" ":rset.getString("mEnTitle").replaceAll("\"","");
			String mGenre = rset.getString("mGenre");
			Date mOpen = rset.getDate("mOpen");
			int mTotalView = rset.getInt("mTotalView");
			String mCountry = rset.getString("mCountry");
			String mPoster = rset.getString("mPoster");
			String mScore = rset.getString("mScore");
			int nCode = rset.getInt("nCode");
			int fCode = rset.getInt("fCode");
			movie = new MovieVo(mCode, mTitle, mEngTitle, mGenre, mOpen, mTotalView, mCountry, mPoster, mScore, nCode, fCode);
			movies.add(movie);
		}
        
		close(rset);
		close(pstmt);
        
        return movies;
	}

	@Override
	public Object select(Connection con, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
