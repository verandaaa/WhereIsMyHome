package com.ssafy.vue.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.service.HouseDealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/deal")
@Api("Deal 컨트롤러  API V1")
public class HouseDealController {

	private final Logger logger = LoggerFactory.getLogger(HouseDealController.class);

	@Autowired
	private HouseDealService houseDealService;

	
	@ApiOperation(value = "아파트 목록", notes = "아파트코드를 기준으로 연도별 평균 거래금액을 반환한다.", response = List.class)
	@GetMapping("/yearlyPrice/{aptCode}")
	public ResponseEntity<Map<String, List<Integer>>> getAptByAddress(@PathVariable("aptCode") @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) long aptCode) throws Exception {	
		logger.info("yearlyPrice - 호출 {} ");
		
		return new ResponseEntity<Map<String, List<Integer>>>(houseDealService.getYearlyPrice(aptCode), HttpStatus.OK);
	}


}
