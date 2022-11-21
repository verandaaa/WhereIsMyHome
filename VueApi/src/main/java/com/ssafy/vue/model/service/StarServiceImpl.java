package com.ssafy.vue.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.StarDto;
import com.ssafy.vue.model.mapper.StarMapper;

@Service
public class StarServiceImpl implements StarService {
	
	private StarMapper starMapper;
	
	@Autowired
	public StarServiceImpl(StarMapper starMapper) {
		this.starMapper = starMapper;
	}

	@Override
	public void addStar(StarDto starDto) throws Exception {
		starMapper.addStar(starDto);
	}

	@Override
	public List<StarDto> listStar(String userid) throws Exception {
		return starMapper.listStar(userid);
	}

	@Override
	@Transactional
	public boolean deleteStar(int starNo) throws Exception {
		return starMapper.deleteStar(starNo) == 1;
	}
	
	

}
