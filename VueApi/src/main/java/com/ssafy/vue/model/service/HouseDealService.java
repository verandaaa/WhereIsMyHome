package com.ssafy.vue.model.service;


import java.util.List;
import java.util.Map;


public interface HouseDealService {
	
	public Map<String, List<Integer>> getYearlyPrice(long aptCode) throws Exception;
	
	
}
