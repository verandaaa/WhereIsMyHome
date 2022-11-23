package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.StarDto;
import com.ssafy.vue.model.StarParameterDto;
import com.ssafy.vue.model.mapper.StarMapper;

@Service
public class StarServiceImpl implements StarService {
	
	private StarMapper starMapper;
	
	@Autowired
	public StarServiceImpl(StarMapper starMapper) {
		this.starMapper = starMapper;
	}


	@Override
	public List<StarDto> listStar(String userid) throws Exception {
		return starMapper.listStar(userid);
	}

	@Override
	@Transactional
	public boolean deleteStar(StarParameterDto starParameterDto) throws Exception {
		return starMapper.deleteStar(starParameterDto) == 1;
	}

	@Override
	public int addStar(StarParameterDto starParameterDto) throws Exception {
		return starMapper.addStar(starParameterDto);
		
	}

	@Override
	public boolean getStar(StarParameterDto starParameterDto) throws SQLException {
		
		return starMapper.getStar(starParameterDto) == 1;
	}

	
	
	

}
