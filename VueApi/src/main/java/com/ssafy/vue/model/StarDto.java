package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StarDto : 아파트정보", description = "관심목록에 저장한 거래 정보를 나타낸다.")
public class StarDto extends HouseInfoDto {
	
	@ApiModelProperty(value = "관심목록번호")
	private int starNo;
	
	@ApiModelProperty(value = "시도이름")
	private String sidoName;
	
	@ApiModelProperty(value = "구군이름")
	private String gugunName;
	
	
	@ApiModelProperty(value = "소재지")
	private String lawArea;
	
	@ApiModelProperty(value = "상세주소")
	private String address;
	

	public String getLawArea() {
		return lawArea;
	}

	public void setLawArea(String lawArea) {
		this.lawArea = lawArea;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getStarNo() {
		return starNo;
	}

	public void setStarNo(int starNo) {
		this.starNo = starNo;
	}
	
	
	
}
