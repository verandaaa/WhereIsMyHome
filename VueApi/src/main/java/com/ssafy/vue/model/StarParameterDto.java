package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StarParameterDto : 관심목록 추가 파라미터 정보", description = "관심목록 추가 시 발생하는 파라미터정보.")
public class StarParameterDto {
	
	@ApiModelProperty(value = "회원 id")
	private String userId;
	
	@ApiModelProperty(value = "아파트코드")
	private long aptCode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public String toString() {
		return "StarParameterDto [userId=" + userId + ", aptCode=" + aptCode + "]";
	}

	

			
	
	
	
}
