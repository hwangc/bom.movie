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

import dbctrl.items.user.UserVo;
import dbctrl.items.write.WriteBiz;
import dbctrl.items.write.WriteVo;
import util.Util;

@WebServlet({ "/WriteServlet", "/write" })
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WriteBiz biz;
	ArrayList<Object> reviewResultArray = null;
	int reviewNumPerPage = 3;
	static int firstReviewID = 0;;

	public WriteServlet() throws Exception {
		super();
		biz = new WriteBiz();
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String cmd = request.getParameter("cmd");
		String next = "main.jsp";
		HttpSession session = request.getSession();
		if (cmd != null) {

			if (cmd.equals("add")) {
				String uEmail = request.getParameter("email");
				int mCode = Integer.parseInt(request.getParameter("mcode"));
				String wContent = request.getParameter("content");
				int wStar = 0;
				wStar = Integer.parseInt(request.getParameter("star-input"));
				
				wContent = Util.convertKr(wContent);

				WriteVo wr = new WriteVo(uEmail, mCode, wContent, wStar);

				try {
					biz.register(wr);
					next = "write.bom?cmd=getall";
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (cmd.equals("getall")) {
				if (session.getAttribute("loginuser") == null)
					next = "login.jsp";
				else {
					UserVo user = (UserVo) session.getAttribute("loginuser");
					String uEmail = user.getEmail();

					try {
						next = "postwrite.jsp";

						// Get the Referer
						String referer = request.getHeader("Referer");

						if (referer != null) {
							referer = referer.substring(referer.lastIndexOf("/") + 1, referer.lastIndexOf("."));
						}

						// Get reviews from all users with their nickname
						reviewResultArray = biz.getUser(uEmail);
						request.setAttribute("userReviews", reviewResultArray);
						RequestDispatcher rd = request.getRequestDispatcher(next);
						rd.forward(request, response);
						return;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (cmd.equals("remove")) {
				String wCode = request.getParameter("wCode");
				try {
					biz.remove(wCode);
					next = "write.bom?cmd=getall";
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (cmd.equals("update")) {
				String wCode = request.getParameter("wCode");
				WriteVo wr = null;

				try {
					wr = (WriteVo) biz.get(wCode);
					request.setAttribute("writeupdate", wr);
					next = "postwriteupdate.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (cmd.equals("updateimpl")) {
				int wCode = Integer.parseInt(request.getParameter("wcode"));
				String uEmail = request.getParameter("email");
				int mCode = Integer.parseInt(request.getParameter("mcode"));
				String wContent = request.getParameter("content");
				int wStar = Integer.parseInt(request.getParameter("star-input"));

				wContent = Util.convertKr(wContent);

				WriteVo wr = new WriteVo(wCode, uEmail, mCode, wContent, wStar);
				try {
					biz.modify(wr);
					next = "write.bom?cmd=getall";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher(next);
			rd.forward(request, response);
		} else {

			// check the ajax request
			String curReviewID = request.getParameter("wcode");
			UserVo user = (UserVo) session.getAttribute("loginuser");
			String uEmail = user.getEmail();
			try {
				if (curReviewID != null) {
					firstReviewID = biz.getFirstID(uEmail).getwCode();
					curReviewID = curReviewID.substring(curReviewID.indexOf('-') + 1);
					// get the reviews after the current ID
					reviewResultArray = biz.getAfterID(curReviewID, reviewNumPerPage, uEmail);
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

}
