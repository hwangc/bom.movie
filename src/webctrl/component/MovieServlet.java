package webctrl.component;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dbctrl.items.movie.MovieBiz;
import dbctrl.items.movie.MovieVo;
import dbctrl.items.user.UserVo;
import util.Util;

@WebServlet({ "/MovieServlet", "/movie" })
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieBiz biz;
   
    public MovieServlet() {
        super();
        biz = new MovieBiz();
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        
		String cmd = request.getParameter("cmd");
		String next = "main.jsp";
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		/**
		 * 검색창 아래 검색어와 같은 리스트 가져오는 함수
		 * */
		if(cmd.equals("searchBar")) {
			ArrayList<Object> movies = null;
			String keyWord = request.getParameter("keyWord");
			
			try {
				if((int)keyWord.charAt(0) >= 65 && (int)keyWord.charAt(0) <= 122)
					movies = biz.getSearchEng(keyWord);
				else
					movies = biz.getSearchKor(keyWord);
				
				ArrayList<Object> movieTitles = new ArrayList<Object>();
				for(int i=0; i<movies.size(); i++){
					MovieVo movie = new MovieVo();
					movie = (MovieVo) movies.get(i);
					movieTitles.add(movie.getmCode());
					movieTitles.add(movie.getmTitle());
					if(movie.getmEngTitle() != null)
						movieTitles.add(movie.getmEngTitle());
					else
						movieTitles.add("");
					movieTitles.add((movie.getmOpen().toString()).substring(0, 4));
				}
				
				JSONArray ja = new JSONArray();
				for(int i=0; i<movieTitles.size(); i++){
					if(i % 4 == 1) {
						if(((String) movieTitles.get(i)).startsWith(keyWord)){
							JSONObject jo = new JSONObject();
							jo.put("id", movieTitles.get(i-1));
							jo.put("title", movieTitles.get(i));
							jo.put("open", movieTitles.get(i+2));
							ja.add(jo);
						}
					}else if(i % 4 == 2) {
						if(((String) movieTitles.get(i)).toUpperCase().startsWith(keyWord.toUpperCase())){
							JSONObject jo = new JSONObject();
							jo.put("id", movieTitles.get(i-2));
							jo.put("title", movieTitles.get(i));
							jo.put("open", movieTitles.get(i+1));
							ja.add(jo);
						}
					}
				}
				out.print(ja.toJSONString());
			    out.close();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		/**
		 * 검색결과 가져오는 함수
		 * */
		}else if (cmd.equals("getSearch")) {
			if(session.getAttribute("loginuser") == null)
				next = "login.jsp";
			else {
				String mCode = request.getParameter("mcode");
				MovieVo movie = null;
				try {
					movie = (MovieVo) biz.get(mCode);
					System.out.println(movie);
					request.setAttribute("searchlist", movie);
					next="search.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		/**
		 * 사용자가 모든 영화의 별점 매기기위해 영화리스트 가져오는 함수
		 * */
		}else if (cmd.equals("getall")) {
			if(session.getAttribute("loginuser") == null)
				next = "login.jsp";
			else {
				UserVo user = (UserVo) session.getAttribute("loginuser");
				String uEmail = user.getEmail();
				ArrayList<Object> movies = null;
				
				try {
					movies = biz.getAllByUser(uEmail);
					request.setAttribute("movielist", movies);
					next = "favorite.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
}
