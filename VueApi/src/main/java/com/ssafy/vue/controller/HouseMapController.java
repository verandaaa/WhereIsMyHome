package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseParameterDto;
import com.ssafy.vue.model.SidoGugunDongCodeDto;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService houseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDongCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "시도에 해당하는 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDongCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출 {}: sidoCode: ", sido);
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동정보", notes = "구군에 해당하는 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunDongCodeDto>> dong(
			@RequestParam("gugun") @ApiParam(value = "동코드.", required = true) String gugun) throws Exception {
		logger.info("dong - 호출  {} gugunCode:", gugun);
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 목록", notes = "동코드를 기준으로 아파트별 최근 거래목록을 반환한다.", response = List.class)
	@GetMapping("/house")
	public ResponseEntity<List<HouseInfoDto>> getAptByAddress(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) HouseParameterDto houseParameterDto) throws Exception {	
		logger.info("house - 호출 {} ", houseParameterDto);
		
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptByAddress(houseParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 목록", notes = "아파트이름 기준으로 아파트별 최근 거래목록을 반환한다.", response = List.class)
	@GetMapping("/house2")
	public ResponseEntity<List<HouseInfoDto>> getAptByName(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) HouseParameterDto houseParameterDto) throws Exception {	
		logger.info("house - 호출 {} ", houseParameterDto);
		
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptByName(houseParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "hosueInfo DB 전부 반환", notes = "아파트 정보를 반환한다.", response = List.class)
	@GetMapping("/houseinfo")
	public ResponseEntity<List<HouseInfoDto>> getHouseInfoList() throws Exception {	
		logger.info("houseInfoList - 호출 {} ");
		
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getHouseInfoList(), HttpStatus.OK);
	}

	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptInDong(dong), HttpStatus.OK);
	}

}
