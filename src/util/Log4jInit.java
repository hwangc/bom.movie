package util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

@WebServlet("/Log4jInit")
public class Log4jInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
	 String prefix =  config.getServletContext().getRealPath("/");
     String file = config.getInitParameter("log4j-init-file");
  
     // if the log4j-init-file context parameter is not set, then no point in trying
     if(file != null){
      PropertyConfigurator.configure(prefix+file);
      System.out.println("Log4J Logging started: " + prefix+file);
     }
     else{
      System.out.println("Log4J Is not configured for your Application: " + prefix + file);
     }     
	}

}
