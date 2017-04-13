package com.db.frame;

public class Sql {
	public static String insertCust = "INSERT INTO TB_USER VALUES (?,?,?)";
	public static String deleteCust = "DELETE FROM TB_USER WHERE ID=?";
	public static String updateCust = "UPDATE TB_USER SET PWD=?,NAME=? WHERE ID=?";
	public static String selectCust = "SELECT * FROM TB_USER WHERE ID=?";
	public static String selectAllCust = "SELECT * FROM TB_USER ORDER BY 1";
	// Static: 클래스를 생성하지도 않고, 클래스이름.변수로 접근할 수 있기 때문에 static 사용

	public static String insertProduct = "INSERT INTO PRODUCT VALUES (PSEQ.NEXTVAL, ?, ?, SYSDATE, ?)";
	public static String udpateProduct = "UPDATE PRODUCT SET NAME=?, PRICE=?, IMG=? WHERE ID=?";
	public static String deleteProduct = "DELETE FROM PRODUCT WHERE ID=?";
	public static String selectProduct = "SELECT * FROM PRODUCT WHERE ID=?";
	public static String selectAllProduct = "SELECT * FROM PRODUCT ORDER BY 1";

}
