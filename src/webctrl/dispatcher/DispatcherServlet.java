package webctrl.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/DispatcherServlet", "/dispatcher" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
        super();
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		path = path.substring(1, path.lastIndexOf("."));
		String next = "main.jsp";
		String view = request.getParameter("view");
		
		if(view != null){
			build(request, view);
		}
		
		switch(path) {
	  		case "login":
	  			next = "login.jsp";
	  			break;
	  		case "register":
	  			next = "register.jsp";
	  			break;
	  		case "mypage":
	       		next = "mypage.jsp";
	       		break;
	       	case "postwrite":
	       		next = "postwrite.jsp";
	       		break;
	       	case "favorite":
	       		next = "favorite.jsp";
	       		break;
	       	case "search":
	       		next = "search.jsp";
	       		break;
	       	case "postwriteupdate":
	       		next = "postwriteupdate.jsp";
	       		break;
	       	case "analysis":
	       		next = "analysis2.jsp";
	       		break;
	       	case "box_chart1":
	       		next = "box_chart1.jsp";
	       		break;
	       	case "box_chart2":
	       		next = "box_chart2.jsp";
	       		break;
	       	case "chart01_boxoffice_1":
	       		next = "chart01_boxoffice_1.jsp";
	       		break;
	       	case "chart01_boxoffice_2":
	       		next = "chart01_boxoffice_2.jsp";
	       		break;
	       	case "chart01_boxoffice_3":
	       		next = "chart01_boxoffice_3.jsp";
	       		break;
	       	case "chart01_boxoffice_4":
	       		next = "chart01_boxoffice_4.jsp";
	       		break;
	       	case "chart02_usermovie_1":
	       		next = "chart02_usermovie_1.jsp";
	  		default:
	  			next = path;
	  			break;
	  	}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	
	}
	
	/**
	 * @param request
	 * @param view
	 */
	private void build(HttpServletRequest request,
			String view){
		if(view.equals("box_chart1")){
			request.setAttribute("center", view);
		}
	}
}





