package com.metelsos.exclntstf.controller;

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
import com.metelsos.exclntstf.service.ExclntstfService;
import com.metelsos.exclntstf.vo.ExclntStfVo;

@Controller
public class ExclntstfController {
	private Log log = LogFactory.getLog(ExclntstfController.class);
	
	@Resource(name="exclntstfService")
	private ExclntstfService exclntstfService;
	
	@RequestMapping(value="/deleteExclntStf.do")
	public ModelAndView deleteExclntStf(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => deleteExclntStf");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = exclntstfService.deleteExclntStf(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/setEngineerNameList.do")
	public ModelAndView setEngineerNameList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => setEngineerNameList");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = exclntstfService.setEngineerNameList(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/enrollExclntStf.do")
	public ModelAndView enrollExclntStf(ExclntStfVo vo) throws Exception{
		log.info("#operation => enrollExclntStf");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap = exclntstfService.enrollExclntStf(vo);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/getExclntStfImage.do")
	public ResponseEntity<byte[]> getExclntStfImage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getExclntStfImage");
		HashMap<String, Object> returnMap = exclntstfService.getExclntStfImage(paramMap);
		byte[] imageContent = (byte[]) returnMap.get("engineer_image");
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
}
