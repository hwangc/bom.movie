package webctrl.component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbctrl.items.movie.MovieBiz;
import dbctrl.items.user.UserVo;
import util.Util;

@WebServlet({ "/RecommendServlet", "/recommend" })
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieBiz biz;
	
    public RecommendServlet() {
        super();
        biz = new MovieBiz();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String next = "main.jsp";
		HttpSession session = request.getSession();
		
		if (!Util.isUserLoggedIn(request, "loginuser")) {
			request.setAttribute("userReviews", "No content for non logged in users");
			response.sendRedirect("login.bom");
			return;
		}
		
		UserVo user = null;
		ArrayList<Object> moviesByStar = null;
		ArrayList<Object> moviesByWrite = null;
		ArrayList<Object> moviesForRecomByUser = null;
		
		try {
			user = (UserVo) session.getAttribute("loginuser");
			String uEmail = user.getEmail();
			session.setAttribute("loginuser", user);

			moviesByStar = biz.getPopularByStar();
			moviesByWrite = biz.getPopularByWrite();
			moviesForRecomByUser = biz.getRecommendByUser(uEmail);
			request.setAttribute("movielistStar", moviesByStar);
			request.setAttribute("movielistWrite", moviesByWrite);
			request.setAttribute("movielistRecom", moviesForRecomByUser);
			
			next = "recommend.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
}
