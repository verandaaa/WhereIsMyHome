package com.ssafy.vue.model;

public class StarDto {

	private int starNo;
	private String dongCode;
	private String year;
	private String month;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String userId;
	
	public int getStarNo() {
		return starNo;
	}
	public void setStarNo(int starNo) {
		this.starNo = starNo;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "StarDto [starNo=" + starNo + ", dongCode=" + dongCode + ", year=" + year + ", month=" + month
				+ ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName=" + dongName + ", userId="
				+ userId + "]";
	}
	
	

}
