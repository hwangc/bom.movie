package web.dispatcher;

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
	/*private Logger work_log = 
			Logger.getLogger("work");
	private Logger data_log = 
			Logger.getLogger("data");*/

	
	public DispatcherServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		//work_log.debug("test logger:"+path);

		path = path.substring(1, path.lastIndexOf("."));
		//data_log.debug(path);
		
		String next = "main.jsp";
		if(path.equals("main")){
			String page = request.getParameter("page");
			
			if(page != null){
				build(request, page);
			}
			next = "main.jsp";
		}else{
			next = path;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
	
	private void build(HttpServletRequest request, String page){
		if(page.equals("login")) {
			request.setAttribute("center", page);
		}else if(page.equals("register")) {
			request.setAttribute("center", page);
		}else if(page.equals("useradd")) {
			request.setAttribute("center", "user/add");
		}else if(page.equals("productadd")) {
			request.setAttribute("center", "product/add");
		}else {
			request.setAttribute("center", page);
		}
		
	}
}





