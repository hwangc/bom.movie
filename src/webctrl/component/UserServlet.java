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


import com.oreilly.servlet.MultipartRequest;

import dbctrl.items.user.UserBiz;
import dbctrl.items.user.UserVo;
import util.Util;


@WebServlet({ "/UserServlet", "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserBiz biz;
       
    public UserServlet() {
        super();
        biz = new UserBiz();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String next = "main.jsp";
	    String dir = "C:\\bombom3\\web\\img\\userimage"; // img 저장 위치
	    int size = 1024 * 1024 * 1024; // 파일 사이즈 제한
	    
		if(cmd.equals("add")) {
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String nickname = request.getParameter("nickname");
			String gender = request.getParameter("gender");
			String date = request.getParameter("bdate");
			
			nickname = Util.convertKr(nickname);
			
			//Convert from String type to Date type
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date bdate = null;
			try {
				bdate = format.parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			UserVo user = new UserVo(email, pwd, nickname, Integer.parseInt(gender), bdate);
			
			try {
				biz.register(user);
				request.setAttribute("adduser", user);
				request.setAttribute("center", "user/addok");
			} catch (Exception e) {
				request.setAttribute("center", "user/addfail");
				e.printStackTrace();
			}
		}else if (cmd.equals("remove")) {
			String email = request.getParameter("email");
			try {
				biz.remove(email);
				next = "tologin.bom";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (cmd.equals("update")) {
			String email = request.getParameter("email");
			UserVo user = null;
			try {
				user = (UserVo) biz.get(email);
				request.setAttribute("userupdate", user);
				next="userupdate.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (cmd.equals("updateimpl")) {
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String nickname = request.getParameter("nickname");
			int gender = Integer.parseInt(request.getParameter("gender"));
			String date = request.getParameter("bdate");
			
			//Convert from String type to Date type
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date bdate = null;
			try {
				bdate = format.parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	
			nickname = Util.convertKr(nickname);
			UserVo user = new UserVo(email, pwd, nickname, gender, bdate);
			try {
				biz.modify(user);
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", user);
				next = "main.bom";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (cmd.equals("imgupdateimpl")) {
//			PrintWriter out = response.getWriter();
	        MultipartRequest mp = new MultipartRequest(request, dir, size, "EUC-KR");
			String email = mp.getParameter("email").trim();
			String image = mp.getOriginalFileName("uploadfile").trim();
			int userid = Integer.parseInt(mp.getParameter("userid"));
			
			UserVo user = null;
			user = new UserVo(email,userid);
	
            try {
				//biz.modifyimg(user);
				next = "mypage.bom";
				request.setAttribute("loginuserimp", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
            	
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

}