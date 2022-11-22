package com.ssafy.vue.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HouseDealInfoDto;
import com.ssafy.vue.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public Map<String, List<Integer>> getYearlyPrice(long aptCode) throws Exception {
		List<HouseDealInfoDto> yearlyDealAmount = sqlSession.getMapper(HouseDealMapper.class).getYearlyPrice(aptCode);
		int size = yearlyDealAmount.size();
		List<Integer> year = new ArrayList<>();
		List<Integer> avgPrice = new ArrayList<>();
		
		for(HouseDealInfoDto hdif : yearlyDealAmount) {
			year.add(hdif.getDealYear());
			avgPrice.add(hdif.getDealAmount());
		}
		
		Map<String, List<Integer>> map = new HashMap<>();
		map.put("year", year);
		map.put("avgPrice", avgPrice);
		
		return map;
	}




}
