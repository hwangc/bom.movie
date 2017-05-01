package dbctrl.items.favorite;

public class FavoriteSql {
	public static String insertFavorite = "INSERT INTO TB_FAVORITE VALUES (TB_FAVORITE_SEQ.NEXTVAL,?,?)";
	public static String deleteFavorite = "DELETE FROM TB_FAVORITE WHERE uemail=? and mcode=?";
	public static String selectFirstID = "SELECT mCode FROM TB_MOVIE2 WHERE ROWNUM <= 1 ORDER BY mCode ASC";
	public static String selectAfterID = "SELECT * FROM (SELECT M.MCODE, M.MTITLE, M.MENTITLE, M.MOPEN, M.MCOUNTRY, M.MGENRE, M.MTOTALVIEW, M.MPOSTER, M.MSCORE, M.NCODE, F.FCODE FROM TB_MOVIE2 M, TB_FAVORITE F WHERE F.UEMAIL(+) = ? AND M.MCODE = F.MCODE(+) AND M.MCODE < ? AND M.NCODE IS NOT NULL AND M.MTOTALVIEW >= '2000' ORDER BY M.MCODE DESC) WHERE ROWNUM <= ?";
}