package chrome;

public class NaverImg {

	public static final boolean AmIAtHome = false;
	public static final boolean IsTest = false;
	public static final boolean IsResume = true;
	
	public static void main(String[] args) {
		if(!IsTest) {
			MovieInfo.StartFetchImage();
		} else  {
			try {
				MovieImg.GetFromWebByID_test();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
