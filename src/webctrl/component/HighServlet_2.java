package webctrl.component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

/**
 * Servlet implementation class HighServlet_2
 */
@WebServlet({ "/HighServlet_2", "/high2" })
public class HighServlet_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieBiz biz; 

    public HighServlet_2() {
        super();
        biz = new MovieBiz();
  
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArrayList<Object> list = null;
		JSONArray ja = new JSONArray();
		HttpSession session = request.getSession();
    	UserVo user = (UserVo) session.getAttribute("loginuser");
    	String uEmail = user.getEmail();
		
		
		try {
			list = biz.getmapcount(uEmail);
			for(Object o:list){
				MovieVo m = (MovieVo) o;
				JSONObject jo = new JSONObject();
				jo.put("code", m.getCountrycode());
				jo.put("z", m.getcountrycount());
				ja.add(jo);
			}
			response.setContentType("text/json;charset=euc-kr"); // text -> json 으로 / char = 한글사용
			PrintWriter out = response.getWriter();
			out.print(ja.toJSONString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
