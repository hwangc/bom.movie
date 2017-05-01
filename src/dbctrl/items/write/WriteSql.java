package dbctrl.items.write;

import dbctrl.interfaces.Sql;

public class WriteSql extends Sql {
	public static String insertWrite = "INSERT INTO JS_WRITE (wCode, uEmail, mCode, wContent, wStar, wDate) VALUES (JS_WRITE_SEQ.NEXTVAL,?,?,?,?,SYSDATE)";
	public static String deleteWrite = "DELETE FROM JS_WRITE WHERE wCode=?";
	public static String updateWrite = "UPDATE JS_WRITE SET uEmail=?, mCode=?, wContent=?, wStar=?, wDate=SYSDATE WHERE wCode=?";
	public static String selectWrite = "SELECT * FROM JS_WRITE WHERE wCode=?";
	public static String selectWriteAfterID = "SELECT * FROM JS_WRITE WHERE wCode < ? AND ROWNUM <= ? ORDER BY wCode desc";
	public static String selectWriteAfterIDByUser = "SELECT * FROM JS_WRITE WHERE wCode < ? AND ROWNUM <= ? AND uEmail = ? ORDER BY wCode asc";
	public static String selectWriteFirstID = "SELECT wCode FROM JS_WRITE WHERE ROWNUM = 1 ORDER BY wCode ASC";
	public static String selectWriteFirstIDByUser = "select * from (SELECT wCode, uEmail FROM JS_WRITE WHERE uEmail = ? group by uEmail, wCode ORDER BY wCode asc) where rownum <=1";
	public static String selectAllWriteByUser = "SELECT * FROM JS_WRITE WHERE uEmail=? order by wCode desc";
	public static String selectAllWriteByUsers = "SELECT * FROM JS_WRITE ORDER BY wCode desc";
	
}
