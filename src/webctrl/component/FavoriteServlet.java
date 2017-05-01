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

import dbctrl.items.favorite.FavoriteBiz;
import dbctrl.items.favorite.FavoriteVo;
import dbctrl.items.user.UserVo;
import util.Util;

@WebServlet({ "/FavoriteServlet", "/fav" })
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FavoriteBiz biz;
	int reviewNumPerPage = 12;
	static int firstReviewID = 0;

	public FavoriteServlet() throws Exception {
		super();
		biz = new FavoriteBiz();
		firstReviewID = biz.getFirstID().getmCode();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String cmd = request.getParameter("cmd");
		String next = "main.jsp";
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		if (cmd.equals("add")) {
			UserVo user = (UserVo) session.getAttribute("loginuser");
			String uEmail = user.getEmail();
			String mCode = request.getParameter("mcode");

			FavoriteVo fav = new FavoriteVo(uEmail, Integer.parseInt(mCode));

			try {
				biz.register(fav);
				out.print(1);
				out.close();
			} catch (Exception e) {
				out.print(0);
				out.close();
				e.printStackTrace();
			}
			return;
		} else if (cmd.equals("remove")) {
			UserVo user = (UserVo) session.getAttribute("loginuser");
			String uEmail = user.getEmail();
			String mCode = request.getParameter("mcode");

			FavoriteVo fav = new FavoriteVo(uEmail, Integer.parseInt(mCode));

			try {
				biz.remove(fav);
				out.print(1);
				out.close();
			} catch (Exception e) {
				out.print(0);
				out.close();
				e.printStackTrace();
			}
			return;
		} else if (cmd.equals("update")) {

		} else if (cmd.equals("loadmore")) {
			
			// check the ajax request
			String lastMovieID = request.getParameter("mcode");
			ArrayList<Object> movieResultArray = null;

			try {
				UserVo user = (UserVo) session.getAttribute("loginuser");
				String uEmail = user.getEmail();
				
				// get the reviews after the current ID
				movieResultArray = biz.get(uEmail, lastMovieID, reviewNumPerPage);
				// set the content type
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				// generate ajax response
				PrintWriter ajaxResponse = response.getWriter();
				ajaxResponse.print(Util.genTemplate(movieResultArray, firstReviewID, request, "FavoriteVo"));
				ajaxResponse.close();
				
				return;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
}