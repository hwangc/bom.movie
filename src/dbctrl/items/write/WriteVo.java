package dbctrl.items.write;

import java.util.Date;

/**
 * @author student
 *
 */
/**
 * @author student
 *
 */
public class WriteVo {
	private int wCode; // 글번호
	private String uEmail; // 이메일
	private String mTitle; // 글제목
	private int mCode; // 영화코드
	private String wContent; // 내용
	private int wStar; // 별점
	private Date wDate; // 작성일
	private int pwCode; // 부모글번호
	private String wNickname; // 글쓴이 닉네임

	public WriteVo() {
	}

	public WriteVo(int wCode, String uEmail, int mCode, String wContent, int wStar, Date wDate, int pwCode) {
		this.wCode = wCode;
		this.uEmail = uEmail;
		this.mCode = mCode;
		this.wContent = wContent;
		this.wStar = wStar;
		this.wDate = wDate;
		this.pwCode = pwCode;
	}

	public WriteVo(int wCode, String uEmail, int mCode, String wContent, int wStar, Date wDate) {
		this.wCode = wCode;
		this.uEmail = uEmail;
		this.mCode = mCode;
		this.wContent = wContent;
		this.wStar = wStar;
		this.wDate = wDate;
	}

	public WriteVo(String uEmail, int mCode, String wContent, int wStar) {
		this.uEmail = uEmail;
		this.mCode = mCode;
		this.wContent = wContent;
		this.wStar = wStar;
	}

	/**
	 * @param wCode
	 * @param uEmail
	 * @param mCode
	 * @param wContent
	 * @param wStar
	 */
	public WriteVo(int wCode, String uEmail, int mCode, String wContent, int wStar) {
		this.wCode = wCode;
		this.uEmail = uEmail;
		this.mCode = mCode;
		this.wContent = wContent;
		this.wStar = wStar;
	}

	public WriteVo(int wCode, String uEmail, String mTitle, int mCode, String wContent, int wStar) {
		super();
		this.wCode = wCode;
		this.uEmail = uEmail;
		this.mTitle = mTitle;
		this.mCode = mCode;
		this.wContent = wContent;
		this.wStar = wStar;
	}

	/**
	 * @param wCode
	 * @param uEmail
	 * @param wNickname
	 * @param mCode
	 * @param wContent
	 * @param wStar
	 * @param regdate
	 * @param pwCode
	 * @param mTitle
	 * 
	 *            Used in MainSevlet for displaying the list of movies
	 */
	public WriteVo(int wCode, String uEmail, String wNickname, int mCode, String wContent, int wStar, Date regdate,
			int pwCode, String mTitle) {
		this.wCode = wCode;
		this.uEmail = uEmail;
		this.mCode = mCode;
		this.wContent = wContent;
		this.wStar = wStar;
		this.wDate = regdate;
		this.pwCode = pwCode;
		this.wNickname = wNickname;
		this.mTitle = mTitle;
	}

	public int getwCode() {
		return wCode;
	}

	public void setwCode(int wCode) {
		this.wCode = wCode;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public int getmCode() {
		return mCode;
	}

	public void setmCode(int mCode) {
		this.mCode = mCode;
	}

	public String getwContent() {
		return wContent;
	}

	public void setwContent(String wContent) {
		this.wContent = wContent;
	}

	public int getwStar() {
		return wStar;
	}

	public void setwStar(int wStar) {
		this.wStar = wStar;
	}

	public Date getwDate() {
		return wDate;
	}

	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}

	public int getPwCode() {
		return pwCode;
	}

	public void setPwCode(int pwCode) {
		this.pwCode = pwCode;
	}

	public String getwNickname() {
		return wNickname;
	}

	public void setwNickname(String wNickname) {
		this.wNickname = wNickname;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	@Override
	public String toString() {
		return "WriteVo [wCode=" + wCode + ", uEmail=" + uEmail + ", wNickname= " + wNickname + ", mCode=" + mCode
				+ ", wContent=" + wContent + ", wStar=" + wStar + ", wDate=" + wDate + ", pwCode=" + pwCode
				+ ", mTitle=" + mTitle + "]";
	}

}
