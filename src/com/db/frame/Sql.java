package com.db.frame;

public class Sql {
	public static String insertCust = "INSERT INTO TB_USER VALUES (?,?,?)";
	public static String deleteCust = "DELETE FROM TB_USER WHERE ID=?";
	public static String updateCust = "UPDATE TB_USER SET PWD=?,NAME=? WHERE ID=?";
	public static String selectCust = "SELECT * FROM TB_USER WHERE ID=?";
	public static String selectAllCust = "SELECT * FROM TB_USER ORDER BY 1";
	// Static: Ŭ������ ���������� �ʰ�, Ŭ�����̸�.������ ������ �� �ֱ� ������ static ���

	public static String insertProduct = "INSERT INTO PRODUCT VALUES (PSEQ.NEXTVAL, ?, ?, SYSDATE, ?)";
	public static String udpateProduct = "UPDATE PRODUCT SET NAME=?, PRICE=?, IMG=? WHERE ID=?";
	public static String deleteProduct = "DELETE FROM PRODUCT WHERE ID=?";
	public static String selectProduct = "SELECT * FROM PRODUCT WHERE ID=?";
	public static String selectAllProduct = "SELECT * FROM PRODUCT ORDER BY 1";

}
