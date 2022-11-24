package com.ssafy.vue.controller;

import java.util.ArrayList;
import java.util.List;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.vue.model.OpenApiParameterDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/open")
@Api("Open 컨트롤러  API V1")
public class OpenApiController {

	private final Logger logger = LoggerFactory.getLogger(OpenApiController.class);

	@Autowired
	NaverNewsSearchApi naver = new NaverNewsSearchApi();

	@ApiOperation(value = "Open Api 결과 반환", notes = "Open Api 결과 반환.", response = List.class)
	@GetMapping("/news")
	public ResponseEntity<List<JSONObject>> getNews(@ApiParam(value = "api 뉴스 결과를 얻기위한 부가정보.", required = true) OpenApiParameterDto openApiParameterDto) throws Exception {	
		logger.info("openApi - 호출 {} ", openApiParameterDto);
		
		JSONParser parser = new JSONParser();
		
		Object economicTrend = parser.parse(naver.search(openApiParameterDto.getEconomicTrend()));
		Object housing = parser.parse(naver.search(openApiParameterDto.getHousing()));
		Object office = parser.parse(naver.search(openApiParameterDto.getOffice()));
		Object realEstate = parser.parse(naver.search(openApiParameterDto.getRealEstate()));
		
		JSONObject obj1 = (JSONObject) economicTrend;
		JSONObject obj2 = (JSONObject) housing;
		JSONObject obj3 = (JSONObject) office;
		JSONObject obj4 = (JSONObject) realEstate;		
		
		List<JSONObject> list = new ArrayList<>();
		
		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		
		return new ResponseEntity<List<JSONObject>>(list, HttpStatus.OK);
	}

	
}
