package webctrl.component;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import dbctrl.items.movie.MovieBiz;
import dbctrl.items.write.WriteBiz;
import dbctrl.items.write.WriteVo;
import util.Util;

@WebServlet({ "/MainServlet", "/main" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WriteBiz reviewController;
	String whereToGoNext = "main.jsp";
	static int firstReviewID = 0;

	public MainServlet() throws Exception {
		super();
		reviewController = new WriteBiz();
		firstReviewID = reviewController.getFirstID().getwCode();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Object> reviewResultArray = null;
		int reviewNumPerPage = 3;

		try {
			// check login
			if (!Util.isUserLoggedIn(request, "loginuser")) {
				request.setAttribute("userReviews", "No content for non logged in users");
				response.sendRedirect("main.jsp");
				return;
			}
			// Get the Referer
			String referer = request.getHeader("Referer");

			if (referer != null) {
				referer = referer.substring(referer.lastIndexOf("/") + 1, referer.lastIndexOf("."));
			}

			// is the user coming from login page?
			if (!referer.equals("main")) {
				// Get reviews from all users with their nickname
				reviewResultArray = reviewController.get();
				request.setAttribute("userReviews", reviewResultArray);
				RequestDispatcher rd = request.getRequestDispatcher(whereToGoNext);
				rd.forward(request, response);
			}
			
			// check the ajax request
			String curReviewID = request.getParameter("wcode");

			if (curReviewID != null) {
				curReviewID = curReviewID.substring(curReviewID.indexOf('-') + 1);
				// get the reviews after the current ID
				reviewResultArray = reviewController.getAfterID(curReviewID, reviewNumPerPage);
				// set the content type
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				// generate ajax response
				PrintWriter ajaxResponse = response.getWriter();
				ajaxResponse.print(Util.genTemplate(reviewResultArray, firstReviewID, request, "WriteVo"));
				ajaxResponse.close();
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
