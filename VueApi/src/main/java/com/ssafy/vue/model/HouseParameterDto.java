package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseParameterDto : 아파트 실거래 파라미터 정보", description = "아파트 목록을 얻기위한 부가적인 파라미터정보.")
public class HouseParameterDto {
	
	@ApiModelProperty(value = "시도코드")
	private String sidoCode;
	@ApiModelProperty(value = "구군코드")
	private String gugunCode;
	@ApiModelProperty(value = "동코드")
	private String dongCode;
	@ApiModelProperty(value = "아파트이름")
	private String aptName;
	@ApiModelProperty(value = "동이름")
	private String dongName;
	@ApiModelProperty(value = "거래년도")
	private String dealYear;
	@ApiModelProperty(value = "거래월")
	private String dealMonth;
	
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	@Override
	public String toString() {
		return "HouseParameterDto [sidoCode=" + sidoCode + ", gugunCode=" + gugunCode + ", dongCode=" + dongCode
				+ ", aptName=" + aptName + ", dongName=" + dongName + ", dealYear=" + dealYear + ", dealMonth="
				+ dealMonth + "]";
	}
	
	
	
	
}
