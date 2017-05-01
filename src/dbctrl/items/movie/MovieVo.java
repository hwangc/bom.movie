package dbctrl.items.movie;

import java.util.Date;

public class MovieVo {
	private int mCode;				// 영화진흥원 코드
	private String mTitle;			// 제목
	private String mEngTitle;		// 원래제목
	private String mGenre;			// 장르코드
	private Date mOpen;				// 개봉날짜
	private int mTotalView;			// 관객수
	private String mCountry;		// 나라
	private String mPoster;			// 이미지
	private String mScore;			// 리뷰별점
	private int nCode;				// 네이버 코드
	private int fCode;				// 찜 코드
	private int wstar;

	private String mgenre;
	private int wcount = 0;
	private int mcount = 0;
	private int countrycount ;
	private String countrycode;
	
	public int getWstar() {
		return wstar;
	}
	
	public void setWstar(int wstar) {
		this.wstar = wstar;
	}
	
	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public MovieVo(int countrycount, String countrycode) {
		super();
		this.countrycode = countrycode;
		this.countrycount = countrycount;
	}

	public MovieVo() {}

	public MovieVo(int mCode) {
		super();
		this.mCode = mCode;
	}

	public MovieVo(String mgenre, int wcount, int mcount) {
		this.mgenre = mgenre;
		this.wcount = wcount;
		this.mcount = mcount;
	}
	
	public MovieVo(int mCode, String mTitle, String mEngTitle, Date mOpen) {
		super();
		this.mCode = mCode;
		this.mTitle = mTitle;
		this.mEngTitle = mEngTitle;
		this.mOpen = mOpen;
	}

	public MovieVo(int mCode, String mTitle, String mEngTitle, String mGenre, Date mOpen, int mTotalView,
			String mCountry, String mPoster, String mScore, int nCode) {
		super();
		this.mCode = mCode;
		this.mTitle = mTitle;
		this.mEngTitle = mEngTitle;
		this.mGenre = mGenre;
		this.mOpen = mOpen;
		this.mTotalView = mTotalView;
		this.mCountry = mCountry;
		this.mPoster = mPoster;
		this.mScore = mScore;
		this.nCode = nCode;
	}
	
	public MovieVo(int mCode, String mTitle, String mEngTitle, String mGenre, Date mOpen, int mTotalView,
			String mCountry, String mPoster, String mScore, int nCode, int fCode) {
		super();
		this.mCode = mCode;
		this.mTitle = mTitle;
		this.mEngTitle = mEngTitle;
		this.mGenre = mGenre;
		this.mOpen = mOpen;
		this.mTotalView = mTotalView;
		this.mCountry = mCountry;
		this.mPoster = mPoster;
		this.mScore = mScore;
		this.nCode = nCode;
		this.fCode = fCode;
	}

	public int getcountrycount() {
		return countrycount;
	}

	public void setcountrycount(int countrycount) {
		this.countrycount = countrycount;
	}
	
	public String getMgenre() {
		return mgenre;
	}

	public void setMgenre(String mgenre) {
		this.mgenre = mgenre;
	}

	public int getWcount() {
		return wcount;
	}

	public void setWcount(int wcount) {
		this.wcount = wcount;
	}

	public int getMcount() {
		return mcount;
	}

	public void setMcount(int mcount) {
		this.mcount = mcount;
	}

	public int getmCode() {
		return mCode;
	}

	public void setmCode(int mCode) {
		this.mCode = mCode;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmEngTitle() {
		return mEngTitle;
	}

	public void setmEngTitle(String mEngTitle) {
		this.mEngTitle = mEngTitle;
	}

	public String getmGenre() {
		return mGenre;
	}

	public void setmGenre(String mGenre) {
		this.mGenre = mGenre;
	}

	public Date getmOpen() {
		return mOpen;
	}

	public void setmOpen(Date mOpen) {
		this.mOpen = mOpen;
	}

	public int getmTotalView() {
		return mTotalView;
	}

	public void setmTotalView(int mTotalView) {
		this.mTotalView = mTotalView;
	}

	public String getmCountry() {
		return mCountry;
	}

	public void setmCountry(String mCountry) {
		this.mCountry = mCountry;
	}

	public String getmPoster() {
		return mPoster;
	}

	public void setmPoster(String mPoster) {
		this.mPoster = mPoster;
	}

	public String getmScore() {
		return mScore;
	}

	public void setmScore(String mScore) {
		this.mScore = mScore;
	}

	public int getnCode() {
		return nCode;
	}

	public void setnCode(int nCode) {
		this.nCode = nCode;
	}

	public int getfCode() {
		return fCode;
	}

	public void setfCode(int fCode) {
		this.fCode = fCode;
	}

	@Override
	public String toString() {
		return "MovieVo [mCode=" + mCode + ", mTitle=" + mTitle + ", mEngTitle=" + mEngTitle + ", mGenre=" + mGenre
				+ ", mOpen=" + mOpen + ", mTotalView=" + mTotalView + ", mCountry=" + mCountry + ", mPoster=" + mPoster
				+ ", mScore=" + mScore + ", nCode=" + nCode + ", fCode=" + fCode + ", mgenre=" + mgenre + ", wcount="
				+ wcount + ", mcount=" + mcount + ", countrycount=" + countrycount + ", countrycode=" + countrycode
				+ "]";
	}
}
