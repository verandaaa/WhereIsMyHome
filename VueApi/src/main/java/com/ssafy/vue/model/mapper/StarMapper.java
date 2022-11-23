package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.StarDto;
import com.ssafy.vue.model.StarParameterDto;

@Mapper
public interface StarMapper {

	public int addStar(StarParameterDto starParameterDto) throws SQLException;

	public List<StarDto> listStar(String userid) throws SQLException;

	public int deleteStar(StarParameterDto starParameterDto) throws SQLException;

	public int getStar(StarParameterDto starParameterDto) throws SQLException;
	
}
