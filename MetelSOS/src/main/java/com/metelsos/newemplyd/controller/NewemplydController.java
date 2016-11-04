package com.metelsos.newemplyd.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.newemplyd.service.NewemplydService;
import com.metelsos.newemplyd.vo.NewemplydVo;

@Controller
public class NewemplydController {
	private Log log = LogFactory.getLog(NewemplydController.class);
	
	@Resource(name="newemplydService")
	private NewemplydService newemplydService;
	
	@RequestMapping(value="/deleteNewEmplyd.do")
	public ModelAndView deleteNewEmplyd(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => deleteNewEmplyd");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = newemplydService.deleteNewEmplyd(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/setNetEmplydNameList.do")
	public ModelAndView setNetEmplydNameList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => deleteNewEmplyd");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = newemplydService.setNewEmplydNameList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/enrollNewEmplyd.do")
	public ModelAndView enrollNewEmplyd(NewemplydVo vo) throws Exception{
		log.info("#operation => enrollNewEmplyd");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap = newemplydService.enrollNewEmplyd(vo);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/getNewEmplydImage.do")
	public ResponseEntity<byte[]> getNewEmplydImage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getNewEmplydImage");
		HashMap<String, Object> returnMap = newemplydService.getNewEmplydImage(paramMap);
		byte[] imageContent = (byte[]) returnMap.get("engineer_image");
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
}
