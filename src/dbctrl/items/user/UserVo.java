package dbctrl.items.user;

import java.util.Date;

public class UserVo {
	private String email;
	private String pwd;
	private String nickname;
	private int gender;
	private Date bdate;
	private int userid;
	
	public UserVo() {}

	public UserVo(String email, int userid) {
		super();
		this.email = email;
		this.userid = userid;
	}

	public UserVo(String email, String pwd, String nickname, int gender, Date bdate, int userid) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.gender = gender;
		this.bdate = bdate;
		this.userid = userid;
	}

	public UserVo(String email, String pwd, String nickname, int gender, Date bdate) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.gender = gender;
		this.bdate = bdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "UserVo [email=" + email + ", pwd=" + pwd + ", nickname=" + nickname + ", gender=" + gender + ", bdate="
				+ bdate + ", userid=" + userid + "]";
	}
}
