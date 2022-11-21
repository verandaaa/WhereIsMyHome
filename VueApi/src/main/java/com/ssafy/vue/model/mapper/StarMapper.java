package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.StarDto;

@Mapper
public interface StarMapper {

	public void addStar(StarDto starDto) throws SQLException;

	public List<StarDto> listStar(String userid) throws SQLException;

	public int deleteStar(int starNo) throws SQLException;
	
}
