package com.ssafy.vue.model.service;

import java.util.List;
import java.util.Map;
import com.ssafy.vue.model.StarDto;

public interface ApartmentService {

	void addStar(StarDto starDto) throws Exception;

	List<StarDto> listStar(String userid) throws Exception;

	void deleteStar(int starNo) throws Exception;
	
}
