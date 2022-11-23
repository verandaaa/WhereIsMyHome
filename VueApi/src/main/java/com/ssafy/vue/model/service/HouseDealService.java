package com.ssafy.vue.model.service;


import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.HouseDealInfoDto;


public interface HouseDealService {
	
	public Map<String, List<Integer>> getYearlyPrice(long aptCode) throws Exception;

	public List<HouseDealInfoDto> getDealList(long aptCode) throws Exception;
	
	
}
