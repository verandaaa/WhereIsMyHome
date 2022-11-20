package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseParameterDto;
import com.ssafy.vue.model.SidoGugunDongCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunDongCodeDto> getSido() throws SQLException;
	List<SidoGugunDongCodeDto> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunDongCodeDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptList(HouseParameterDto houseParameterDto) throws SQLException;
	
}
