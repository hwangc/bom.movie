package dbctrl.items.favorite;

public class FavoriteVo {
	private int fCode;
	private String uEmail;
	private int mCode;
	
	public FavoriteVo() {}

	public FavoriteVo(String uEmail, int mCode) {
		super();
		this.uEmail = uEmail;
		this.mCode = mCode;
	}

	public int getfCode() {
		return fCode;
	}

	public void setfCode(int fCode) {
		this.fCode = fCode;
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

	@Override
	public String toString() {
		return "FavoriteVo [fCode=" + fCode + ", uEmail=" + uEmail + ", mCode=" + mCode + "]";
	}
}
