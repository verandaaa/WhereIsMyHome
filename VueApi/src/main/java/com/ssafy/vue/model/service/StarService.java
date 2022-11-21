package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.ssafy.vue.model.StarDto;
import com.ssafy.vue.model.StarParameterDto;

public interface StarService {

	boolean addStar(StarParameterDto starParameterDto) throws Exception;

	List<StarDto> listStar(String userid) throws Exception;

	boolean deleteStar(StarParameterDto starParameterDto) throws Exception;

	boolean getStar(StarParameterDto starParameterDto) throws SQLException;
	
}
