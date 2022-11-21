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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.StarDto;
import com.ssafy.vue.model.service.StarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
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
	@GetMapping("/{userid}")
	public ResponseEntity<List<StarDto>> listArticle(@PathVariable("userid") @ApiParam(value = "관심목록을 얻고자 하는 아이디", required = true) String userid) throws Exception {
		logger.info("listStar - 호출" + userid);
		return new ResponseEntity<List<StarDto>>(starService.listStar(userid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심매물 삭제", notes = "번호에 해당하는 관심매물 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{starno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("starno") @ApiParam(value = "삭제할 관심목록 번호.", required = true) int starno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (starService.deleteStar(starno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
