package com.ssafy.vue.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseParameterDto;
import com.ssafy.vue.model.SidoGugunDongCodeDto;

public interface HouseMapService {

	List<SidoGugunDongCodeDto> getSido() throws Exception;
	List<SidoGugunDongCodeDto> getGugunInSido(String sido) throws Exception;
	List<SidoGugunDongCodeDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptByAddress(HouseParameterDto houseParmeterDto) throws Exception;
	List<HouseInfoDto> getAptByName(HouseParameterDto houseParameterDto) throws Exception;
	
}
