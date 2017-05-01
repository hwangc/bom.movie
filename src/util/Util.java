package util;

import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import dbctrl.items.movie.MovieVo;
import dbctrl.items.write.WriteVo;

public class Util {
	
	public static String convertKr(String str) {
		String result = "";
		try {
			result = new String(str.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Check if the user is logged in
	 * 
	 * @param request
	 * @param sessionID
	 * @return
	 * @author David
	 * @since 2017.04.27
	 */
	public static boolean isUserLoggedIn(HttpServletRequest request, String sessionID) {

		boolean result = false;
		HttpSession session = request.getSession();

		try {
			if (session.getAttribute(sessionID) != null) {
				result = true;
			} else {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static JSONObject genTemplate(ArrayList<Object> resultArray, int firstID, HttpServletRequest req,
			String vo) throws JSONException {

		String result = "";
		String isEnd = "false";
		JSONObject resultJSON = new JSONObject();
		String reviewTemplate = req.getParameter("reviewTemplate");
		Format formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		
		if(vo == "WriteVo") {
			
			for (Object resultItem : resultArray) {
				
				// replace text with new value from the array
				String template = reviewTemplate;
				WriteVo movie = (WriteVo) resultItem;
				template = template.replaceFirst(req.getParameter("wcode"), "review-" + Integer.toString(movie.getwCode()));
				template = template.replaceFirst(req.getParameter("mcode"), Integer.toString(movie.getmCode()));
				template = template.replaceFirst(req.getParameter("wdate"), formatter.format(movie.getwDate()));
				template = template.replaceFirst(req.getParameter("wnickname"), movie.getwNickname());
				template = template.replaceFirst(req.getParameter("mtitle"), movie.getmTitle());
				template = template.replaceFirst(req.getParameter("wcontent"), movie.getwContent());
				template = template.replaceFirst(req.getParameter("wstar"), Integer.toString(movie.getwStar())+"Á¡");
				
				result += template;
				
				if (firstID == movie.getwCode()) {
					isEnd = "true";
				}
			}
		}
		
		if(vo == "FavoriteVo") {
			
			for (Object resultItem : resultArray) {
				
				// replace text with new value from the array
				String template = reviewTemplate;
				MovieVo movie = (MovieVo) resultItem;
				
				template = template.replaceAll(req.getParameter("mcode"), Integer.toString(movie.getmCode()));
				template = template.replaceAll(req.getParameter("ncode"), Integer.toString(movie.getnCode()));
				template = template.replaceAll(req.getParameter("mtitle"), movie.getmTitle());
				template = template.replaceFirst(req.getParameter("mengtitle"), (movie.getmEngTitle() == null)?" ":movie.getmEngTitle());
				template = template.replaceFirst(req.getParameter("mopen"), formatter.format(movie.getmOpen()));
				template = template.replaceFirst(req.getParameter("mgenre"), movie.getmGenre());
				template = template.replaceFirst(req.getParameter("mcountry"), movie.getmCountry());
				template = template.replaceFirst("mscore=\""+req.getParameter("mscore")+"\"", "mscore=\""+movie.getmScore()+"\"");

				if(movie.getfCode() == 0) {					
					template = template.replaceFirst(req.getParameter("mfavbtn"), "img/heart-btn.png");
					template = template.replaceFirst(req.getParameter("mfavdata"), "true");
				} else {
					template = template.replaceFirst(req.getParameter("mfavbtn"), "img/heart-btn-in.png");
					template = template.replaceFirst(req.getParameter("mfavdata"), "false");
				}
				
				result += template;
				
				if (firstID == movie.getmCode()) {
					isEnd = "true";
				}
			}
		}

		resultJSON.put("review", result);
		resultJSON.put("end", isEnd);

		return resultJSON;
	}
}