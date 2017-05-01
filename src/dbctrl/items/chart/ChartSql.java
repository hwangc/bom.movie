package dbctrl.items.chart;

import dbctrl.interfaces.Sql;

public class ChartSql extends Sql {
	public static String selectUserMovie = "select mgenre, count(*) genrecount "
			+ "from(select DISTINCT * "
			+ "from( select a.uemail, a.mcode,b.mtitle, b.mentitle, b.mopen,b.mcountry, b.mgenre,b.mtotalview, b.mposter,b.mscore "
			+ "from (select uemail,mcode from tb_favorite where uemail=?) a "
			+ "LEFT OUTER JOIN tb_movie2 b ON a.mcode=b.mcode "
			+ "UNION "
			+ "select a.uemail, a.mcode,b.mtitle, b.mentitle, b.mopen,b.mcountry, b.mgenre,b.mtotalview, b.mposter,b.mscore "
			+ "from (select uemail,mcode from js_write where uemail=?) a "
			+ "LEFT OUTER JOIN tb_movie2 b ON a.mcode=b.mcode )) "
			+ "where mgenre != '0' group by mgenre order by genrecount desc";
	
	public static String selectUserDirector = "select sname, count(*) as directorcount "
			+ "from (select DISTINCT * "
			+ "from (select a.mcode, b.scode,b.SROLE,b.SNAME "
			+ "from(select mcode from tb_favorite where uemail=?) a "
			+ "left outer join tb_staff b on a.mcode=b.mcode where b.srole='감독' "
			+ "union "
			+ "select a.mcode, b.scode,b.sRole,b.Sname "
			+ "from(select mcode from js_write where uemail=?) a "
			+ "left outer join tb_staff b on a.mcode=b.mcode where b.srole='감독' )) "
			+ "group by sname order by directorcount desc";
	
	public static String selectUserActor = "select sname aname, count(*) as actorcount "
			+ "from (select DISTINCT * "
			+ "from (select a.mcode, b.scode,b.SROLE,b.SNAME "
			+ "from(select mcode from tb_favorite where uemail=?) a "
			+ "left outer join tb_staff b on a.mcode=b.mcode where b.srole='배우' "
			+ "union "
			+ "select a.mcode, b.scode,b.sRole,b.Sname "
			+ "from(select mcode from js_write where uemail=?) a "
			+ "left outer join tb_staff b on a.mcode=b.mcode where b.srole='배우' )) "
			+ "group by sname order by actorcount desc";
}
