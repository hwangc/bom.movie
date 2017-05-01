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

import dbctrl.items.chart.ChartBiz;
import dbctrl.items.chart.ChartVo;
import dbctrl.items.movie.MovieBiz;
import dbctrl.items.movie.MovieVo;
import dbctrl.items.user.UserVo;


@WebServlet({ "/HighServlet", "/high" })
public class HighServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       MovieBiz biz; 
       ChartBiz cbiz;
 
    public HighServlet() {
        super();
        biz = new MovieBiz();
        cbiz = new ChartBiz();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Object> list = null;
		JSONArray ja = new JSONArray();
		String cmd = request.getParameter("cmd");
		String next = "main.jsp";
//		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
	
		if(cmd.equals("woman")) {
			try {
				list = biz.getUserCount();
				
				//list.get(0)
				for(int i=0; i<10; i++){
					MovieVo m = (MovieVo)list.get(i);
					JSONObject jo = new JSONObject();
					jo.put("name", m.getMgenre());
					jo.put("y", m.getWcount());
					ja.add(jo);
				}
				
				/*
				for(Object o:list)
				{
					MovieVo m = (MovieVo) o;
					JSONObject jo = new JSONObject();
					jo.put("name", m.getMgenre());
					jo.put("y", m.getWcount());
					ja.add(jo);
				}
				*/
				response.setContentType("text/json;charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.print(ja.toJSONString());
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}else if(cmd.equals("man")) {
			try {
				list = biz.getUserCountM();
				
				for(int i=0; i<10; i++){
					MovieVo m = (MovieVo) list.get(i);
					JSONObject jo = new JSONObject();
					jo.put("name", m.getMgenre());
					jo.put("y", m.getMcount());
					ja.add(jo);
				}
				response.setContentType("text/json;charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.print(ja.toJSONString());
				out.close();	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
    }else if(cmd.equals("usermovie")) {
    	if(session.getAttribute("loginuser") == null)
    		next = "login.jsp";
    	else {
    		UserVo user = (UserVo) session.getAttribute("loginuser");
    		String uEmail = user.getEmail();
    		
	    	try {
				list = cbiz.getUserMovie(uEmail);
				
				for(int i=0; i<10; i++){
					ChartVo c = (ChartVo) list.get(i);
					JSONObject jo = new JSONObject();
					jo.put("name", c.getMgenre());
					jo.put("y", c.getGenrecount());
					ja.add(jo);
				}
				response.setContentType("text/json;charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.print(ja.toJSONString());
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
    	}
    }else if(cmd.equals("userdirector")) {
    	if(session.getAttribute("loginuser") == null)
    		next = "login.jsp";
    	else{
    		UserVo user = (UserVo) session.getAttribute("loginuser");
    		String uEmail = user.getEmail();
    		try {
				list = cbiz.getUserDirector(uEmail);
				
	    		for(int i=0; i<5; i++){
	    			ChartVo c = (ChartVo) list.get(i);
	    			JSONObject jo = new JSONObject();
					jo.put("name", c.getSname());
					jo.put("y", c.getDcount());
					ja.add(jo);
	    		}
	    		response.setContentType("text/json;charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.print(ja.toJSONString());
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}			
    	}
    }else if(cmd.equals("useractor")) {
    	if(session.getAttribute("loginuser") == null)
    		next = "login.jsp";
    	else{
	    	UserVo user = (UserVo) session.getAttribute("loginuser");
			String uEmail = user.getEmail();
			
			try {
				list = cbiz.getUserActor(uEmail);
				
				for(int i=0; i<5; i++){
					ChartVo c = (ChartVo) list.get(i);
	    			JSONObject jo = new JSONObject();
					jo.put("name", c.getAname());
					jo.put("y", c.getAcount());
					ja.add(jo);
				}
				
	    		response.setContentType("text/json;charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.print(ja.toJSONString());
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	
    }
		
		
		
    }
}
