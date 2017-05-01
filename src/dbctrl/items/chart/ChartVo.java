package dbctrl.items.chart;

public class ChartVo {
	private String mgenre;
	private int genrecount = 0;
	private String sname;
	private int dcount;
	private String aname;
	private int acount = 0;
	

	public ChartVo() {
	}


	public ChartVo(String mgenre, int genrecount) {
		this.mgenre = mgenre;
		this.genrecount = genrecount;
	}

	
	public ChartVo(String sname, String aname, int acount) {
		this.sname = sname;
		this.aname = aname;
		this.acount = acount;
	}


	public ChartVo(int genrecount, String sname, int dcount) {
		this.genrecount = genrecount;
		this.sname = sname;
		this.dcount = dcount;
	}


	public String getAname() {
		return aname;
	}


	public void setAname(String aname) {
		this.aname = aname;
	}


	public int getAcount() {
		return acount;
	}


	public void setAcount(int acount) {
		this.acount = acount;
	}


	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getDcount() {
		return dcount;
	}


	public void setDcount(int dcount) {
		this.dcount = dcount;
	}

	public String getMgenre() {
		return mgenre;
	}

	public void setMgenre(String mgenre) {
		this.mgenre = mgenre;
	}

	public int getGenrecount() {
		return genrecount;
	}
	
	public void setGenrecount(int genrecount) {
		this.genrecount = genrecount;
	}
	
}
