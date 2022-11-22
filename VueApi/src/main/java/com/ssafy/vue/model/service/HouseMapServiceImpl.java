package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseParameterDto;
import com.ssafy.vue.model.SidoGugunDongCodeDto;
import com.ssafy.vue.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunDongCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunDongCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunDongCodeDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getAptByAddress(HouseParameterDto houseParameterDto) throws Exception {
		
		return sqlSession.getMapper(HouseMapMapper.class).getAptByAddress(houseParameterDto);
	}

	@Override
	public List<HouseInfoDto> getAptByName(HouseParameterDto houseParameterDto) throws Exception {
		
		return sqlSession.getMapper(HouseMapMapper.class).getAptByName(houseParameterDto);
	}

}
