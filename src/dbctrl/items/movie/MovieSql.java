package dbctrl.items.movie;

public class MovieSql {
	public static String selectMovie = "SELECT * FROM TB_MOVIE2 WHERE MCODE=?";
	public static String selectAllMovie = "SELECT * FROM TB_MOVIE2 WHERE ORDER BY 1";
	
	public static String selectAllMoviesByStar = "select * from"
			+ "(select * from tb_movie2 where mtotalview>0 and MSCORE IS NOT NULL order by MSCORE desc) "
			+ "where rownum<=6";
	
	public static String selectAllMoviesByWrite = "select * from "
			+ "(select a.mCode, a.mcount, b.mtitle, b.mentitle, b.mopen,b.mcountry, b.mgenre,"
			+ "b.mtotalview, b.mposter,b.mscore,b.ncode from "
			+ "(select mcode,count(*) as mcount from JS_WRITE group by mcode) "
			+ "a LEFT OUTER JOIN tb_movie2 b ON a.mCode=b.mCode order by a.mcount desc) "
			+ "where rownum<=6"; 
	
	public static String selectGetSerachMovieEng = 
			"select * from tb_movie2 where upper(mentitle) like lower(?) or upper(mentitle) like upper(?) order by mentitle";
	
	public static String selectGetSerachMovieKor = 
			"select * from tb_movie2 where mtitle like ? order by 1";
	//select * from tb_movie2 where mtitle like lower('a%') or mtitle like upper('a%') order by 1;
	//select * from tb_movie2 where mtitle like 'a%';
	
	public static String SelectAllMoviesWithFav = 
			"select * from (select m.mCode, m.mTitle, m.mEntitle, m.mOpen, m.mCountry, m.mGenre, "
			+"m.mTotalview, m.mPoster, m.mScore, m.nCode, f.fCode from tb_movie2 m, tb_favorite f "
			+"where m.mopen<=sysdate and m.mtotalview>2000 and f.uemail(+) = ? and m.mCode = f.mCode(+) "
			+"ORDER BY m.mOpen desc) where rownum<=100";
	
	public static String selectAllMoviesForRecom = 
			"select distinct * "
			+ "from (select mcode,mscore from tb_movie2 where mtotalview>2000 and mgenre = (select mgenre "
			+ "from (select distinct c.mgenre, count(*) genrecount "
			+ "from (select a.mcode, b.MGENRE  from (select mcode from tb_favorite where uemail=?) a "
			+ "LEFT JOIN tb_movie2 b ON a.mcode=b.mcode) c "
			+ "LEFT JOIN ( select mcode from tb_favorite where uemail=?) d "
			+ "ON c.mcode=d.mcode where c.mgenre !='0' and c.mgenre != '기타' group by mgenre order by genrecount desc) "
			+ "where rownum<=1) "
			+ "union select a.mcode,b.mscore "
			+ "from (select mcode from tb_staff where scode=("
			+ "select scode from (select distinct d.scode, count(*) scodecount "
			+ "from (select q.mcode, w.MGENRE  from (select mcode from tb_favorite where uemail=?) q "
			+ "LEFT JOIN tb_movie2 w ON q.mcode=w.mcode) c "
			+ "LEFT JOIN (select q.mcode, w.scode,w.srole  from (select mcode from tb_favorite where uemail=?) q "
			+ "LEFT JOIN tb_staff w ON q.mcode=w.mcode where w.srole='감독' or w.srole='배우') d "
			+ "ON c.mcode=d.mcode where d.scode is not null group by scode order by scodecount desc) "
			+ "where rownum <=1)) a "
			+ "inner join tb_movie2 b on a.mcode=b.mcode and b.mtotalview>2000) where mscore is not null order by mscore desc"; 

	public static String selectAllWatchedMovies = 
			"select distinct c.mcode "
			+ "from (select a.mcode, b.MGENRE, b.mopen "
			+ "from (select mcode from tb_favorite where uemail=?) a "
			+ "LEFT JOIN tb_movie2 b ON a.mcode=b.mcode) c "
			+ "LEFT JOIN (select a.mcode, b.scode, b.srole "
			+ "from (select mcode from tb_favorite where uemail=?) a "
			+ "LEFT JOIN tb_staff b ON a.mcode=b.mcode where b.srole='감독' or b.srole='배우') d "
			+ "ON c.mcode=d.mcode where c.mcode is not null "; 
			
	public static String selectUserCount = 
			"select mgenre, womancount, mancount "
			+ "from ((select e.mgenre, e.womancount,f.mancount "
			+ "from (select mgenre, count(*) womancount "
			+ "from(select c.ugender, d.MGENRE, d.mtitle,d.mcode "
			+ "from (select a.uemail, a.mcode, b.ugender "
			+ "from(select uemail, mcode from tb_favorite) "
			+ "a left outer join tb_user b ON a.UEMAIL=b.uemail) "
			+ "c LEFT OUTER JOIN  TB_movie2 d ON c.mcode=d.mcode ) "
			+ "where ugender='1' group by mgenre order by womancount desc) "
			+ "e INNER JOIN (select mgenre, count(*) mancount "
			+ "from(select c.ugender, d.MGENRE, d.mtitle,d.mcode "
			+ "from (select a.uemail, a.mcode, b.ugender "
			+ "from(select uemail, mcode from tb_favorite) a "
			+ "left outer join tb_user b ON a.UEMAIL=b.uemail) "
			+ "c LEFT OUTER JOIN  TB_movie2 d ON c.mcode=d.mcode ) "
			+ "where ugender='0' group by mgenre order by mancount desc) f ON e.mgenre = f.mgenre))"
			+ "where mgenre!='0'";
	
	public static String selectMapCount = "select e.countrycount,f.countrycode "
			+ "from (select mcountry, count(*) countrycount "
			+ "from(select DISTINCT * "
			+ "from( select a.uemail, a.mcode,b.mtitle, b.mentitle, b.mopen,b.mcountry, b.mgenre,b.mtotalview, b.mposter,b.mscore "
			+ "from (select uemail,mcode from test_favorite where uemail=?) a "
			+ "LEFT OUTER JOIN tb_movie2 b ON a.mcode=b.mcode "
			+ "UNION "
			+ "select a.uemail, a.mcode,b.mtitle, b.mentitle, b.mopen,b.mcountry, b.mgenre,b.mtotalview, b.mposter,b.mscore "
			+ "from (select uemail,mcode from js_write where uemail=?) a "
			+ "LEFT OUTER JOIN tb_movie2 b ON a.mcode=b.mcode )) "
			+ "where mcountry != '0' or mcountry !='기타' group by mcountry order by countrycount desc) e "
			+ "INNER JOIN tb_country f ON e.mcountry=f.countryname";
}
