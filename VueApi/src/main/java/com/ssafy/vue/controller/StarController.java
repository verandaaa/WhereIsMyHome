package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.StarDto;
import com.ssafy.vue.model.StarParameterDto;
import com.ssafy.vue.model.service.StarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/star")
@Api("관심목록 컨트롤러  API V1")
public class StarController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private StarService starService;
	
	@ApiOperation(value = "관심매물 목록", notes = "해당 회원의 관심매물 목록을 반환한다.", response = List.class)
	@GetMapping("/list/{userid}")
	public ResponseEntity<List<StarDto>> listStar(@PathVariable("userid") @ApiParam(value = "관심목록을 얻고자 하는 아이디", required = true) String userid) throws Exception {
		logger.info("listStar - 호출" + userid);
		return new ResponseEntity<List<StarDto>>(starService.listStar(userid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 관심매물에 대한 정보 조회 ", notes = "관심매물 목록에 있는 특정 매물 정보를 반환한다.", response = List.class)
	@GetMapping("/one/{aptCode}")
	public ResponseEntity<StarDto> getStarByAptCode(@PathVariable("aptCode") @ApiParam(value = "관심매물의 아파트코드", required = true) String aptCode) throws Exception {
		
		logger.info("listStar - 호출 {} " , aptCode);
		
		return new ResponseEntity<StarDto> (starService.getStarByAptCode(Long.parseLong(aptCode)), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "관심매물 추가", notes = "관심매물 추가한다. 그리고 DB 추가 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> addStar(@RequestBody @ApiParam(value ="관심목록 삭제할 회원 id, 아파트코드", required = true) StarParameterDto starParameterDto) throws Exception {
		logger.info("addStar - 호출");
		
		if (starService.addStar(starParameterDto)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심매물 삭제", notes = "번호에 해당하는 관심매물 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> deleteStar(@RequestParam @ApiParam(value ="관심목록 삭제할 회원 id, 아파트코드", required = true) long aptCode,
			@RequestParam @ApiParam(value ="관심목록 삭제할 회원 id, 아파트코드", required = true)String userId) throws Exception {
		logger.info("deleteStar - 호출");
		StarParameterDto starParameterDto = new StarParameterDto();
		starParameterDto.setUserId(userId);
		starParameterDto.setAptCode(aptCode);
		
		if (starService.deleteStar(starParameterDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심매물 조회", notes = "유저의 관심목록에 번호에 해당하는 관심매물이 있는지 조회한다. 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/one")
	public ResponseEntity<String> getStar(@RequestBody @ApiParam(value ="관심목록 조회할 회원 id, 아파트코드", required = true) StarParameterDto starParameterDto) throws Exception {
		logger.info("getStar - 호출 {}", starParameterDto );
		System.out.println(starParameterDto);
		
		if (starService.getStar(starParameterDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
}

