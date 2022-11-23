package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseDealInfoDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseParameterDto;
import com.ssafy.vue.model.SidoGugunDongCodeDto;

@Mapper
public interface HouseDealMapper {

	List<HouseDealInfoDto> getYearlyPrice(long aptCode) throws SQLException;

	List<HouseDealInfoDto> getDealList(long aptCode) throws SQLException;
	
}
