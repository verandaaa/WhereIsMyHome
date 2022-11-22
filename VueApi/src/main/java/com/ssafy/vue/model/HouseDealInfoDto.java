package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@ApiModel(value = "HouseInfoDto : 아파트정보", description = "아파트의 상세 정보를 나타낸다.")
public class HouseDealInfoDto {

	// # housedeal 테이블 칼럼
	@ApiModelProperty(value = "매매가격")
	private int dealAmount;	
	@ApiModelProperty(value = "거래년")
	private int dealYear;
	@ApiModelProperty(value = "거래월")
	private int dealMonth;
	@ApiModelProperty(value = "거래일")
	private int dealDay;
	@ApiModelProperty(value = "면적")
	private float area;
	@ApiModelProperty(value = "거래일")
	private String dealDate;
	@ApiModelProperty(value = "층수")
	private int floor;
	
	
	public int getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(int dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public String getDealDate() {
		return dealDate;
	}
	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	
//	@ApiModelProperty(value = "시도이름")
//	private String sidoName;
//	@ApiModelProperty(value = "구군이름")
//	private String gugunName;
//	@ApiModelProperty(value = "아파트이미지")
//	private String img;
	
	
	
	 
}