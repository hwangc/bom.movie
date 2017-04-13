package web.component;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.db.user.UserBiz;
import com.db.vo.User;

@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger user_log = 
			Logger.getLogger("user");
    UserBiz biz;
    
    public LoginServlet() {
        super();
        biz = new UserBiz();
    }
    
    //LOGOUT
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null) {
			User user = (User) session.getAttribute("loginuser");
			user_log.debug("logout : " +user.getId());
			session.invalidate();
			response.sendRedirect("main.jsp");
		}
	}

	//LOGIN
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		User user = null;
		String centerView = "center";
		try {
			user = (User) biz.get(id);
			
			if(pwd.equals(user.getPwd())) {
				user_log.debug("login : " +id);
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", user);	//유저객체를 저장
				
				centerView = "loginok";
			}else {
				centerView = "loginfail";		//비번 일치하지 않음				
			}
		} catch (Exception e) {
			centerView = "loginfail";		//아이디 검색 실패
			e.printStackTrace();
		}
		request.setAttribute("center", centerView);
	
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
	}
}
