package webctrl.component;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import dbctrl.items.user.UserBiz;
import dbctrl.items.user.UserVo;

@WebServlet({ "/LoginServlet", "/tologin" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger user_log = 
			Logger.getLogger("user");
    UserBiz biz = null;
    
    public LoginServlet() {
        super();
        biz = new UserBiz();
    }
    
    //LOGOUT
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null) {
			UserVo user = (UserVo) session.getAttribute("loginuser");
			user_log.debug("logout : " +user.getEmail());
			session.invalidate();
			response.sendRedirect("main.jsp");
		}
	}

	//LOGIN
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		UserVo user = null;
		
		try {
			user = (UserVo) biz.get(email);
			
			if(pwd.equals(user.getPwd())) {
				//user_log.debug("login : " +email);
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", user);	//유저객체를 저장
				response.sendRedirect("main.bom");
			}else {
				response.sendRedirect("login.bom");
			}
		} catch (Exception e) {
			response.sendRedirect("login.bom");
			e.printStackTrace();
		}
	}
}
