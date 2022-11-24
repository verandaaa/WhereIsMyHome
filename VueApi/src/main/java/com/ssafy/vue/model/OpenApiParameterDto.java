package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "OpenApiParameterDto : api 서비스 파라미터 정보", description = "뉴스 검색 결과를 얻기 위한 부가적인 파라미터정보.")
public class OpenApiParameterDto {
	
	@ApiModelProperty(value = "부동산")
	String realEstate; //검색어: 부동산
	@ApiModelProperty(value = "주택")
	String housing; //검색어: 주택
	@ApiModelProperty(value = "사무실")
	String office; //검색어: 사무실
	@ApiModelProperty(value = "경제동향")
	String economicTrend; //검색어: 경제동향
	
	public String getRealEstate() {
		return realEstate;
	}
	public void setRealEstate(String realEstate) {
		this.realEstate = realEstate;
	}
	public String getHousing() {
		return housing;
	}
	public void setHousing(String housing) {
		this.housing = housing;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getEconomicTrend() {
		return economicTrend;
	}
	public void setEconomicTrend(String economicTrend) {
		this.economicTrend = economicTrend;
	}
	
	
	
}
