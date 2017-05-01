package dbctrl.items.user;

public class UserSql {
	public static String insertUser = "INSERT INTO TB_USER(uemail,upwd,unickname,ugender,ubdate,userid) VALUES (?,?,?,?,?,TB_USER_SEQ.NEXTVAL)";
	public static String deleteUser = "DELETE FROM TB_USER WHERE UEMAIL=?";
	public static String updateUser = "UPDATE TB_USER SET UPWD=?, UNICKNAME=?, UGENDER=?, UBDATE=? WHERE UEMAIL=?";
	public static String selectUser = "SELECT * FROM TB_USER WHERE UEMAIL=?";
	public static String selectAllUser = "SELECT * FROM TB_USER ORDER BY 1";
	/*public static String updateimage = "UPDATE TB_USER SET userid=? WHERE EMAIL=?";*/
}
