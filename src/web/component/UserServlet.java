package web.component;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.user.UserBiz;
import com.db.vo.User;

import web.dispatcher.Util;

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
		
		if(cmd.equals("add")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			
			name = Util.convertKr(name);
			
			User user = new User(id, pwd, name);
			try {
				biz.register(user);
				request.setAttribute("adduser", user);
				request.setAttribute("center", "user/addok");
			} catch (Exception e) {
				request.setAttribute("center", "user/addfail");
				e.printStackTrace();
			}
		}else if (cmd.equals("remove")) {
			String id = request.getParameter("id");
			try {
				biz.remove(id);
				next = "user.big?cmd=getall";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (cmd.equals("getall")) {
			ArrayList<Object> users = null;
			try {
				users = biz.get();
				request.setAttribute("userlist", users);
				request.setAttribute("center", "user/list");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (cmd.equals("detail")) {
			String id = request.getParameter("id");
			User user = null;
			try {
				user = (User) biz.get(id);
				request.setAttribute("userdetail", user);
				request.setAttribute("center", "user/detail");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (cmd.equals("update")) {
			String id = request.getParameter("id");
			User user = null;
			try {
				user = (User) biz.get(id);
				request.setAttribute("userupdate", user);
				request.setAttribute("center", "user/update");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (cmd.equals("updateimpl")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			
			name = Util.convertKr(name);
			
			User user = new User(id, pwd, name);
			try {
				biz.modify(user);
				next = "user.big?cmd=detail&id="+id;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

}
