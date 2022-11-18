package com.ssafy.vue.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.StarDto;
import com.ssafy.vue.model.mapper.ApartmentMapper;

@Service
public class ApartmentServiceImpl implements ApartmentService {
	
	private ApartmentMapper apartmentMapper;
	
	@Autowired
	public ApartmentServiceImpl(ApartmentMapper apartmentMapper) {
		this.apartmentMapper = apartmentMapper;
	}

	@Override
	public void addStar(StarDto starDto) throws Exception {
		apartmentMapper.addStar(starDto);
		
	}

	@Override
	public List<StarDto> listStar(String userid) throws Exception {
		return apartmentMapper.listStar(userid);
	}

	@Override
	public void deleteStar(int starNo) throws Exception {
		apartmentMapper.deleteStar(starNo);
		
	}

}
