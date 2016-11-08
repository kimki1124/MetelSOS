package com.metelsos.support.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.support.service.SupportService;

@Controller
public class SupportController {
	private Log log = LogFactory.getLog(SupportController.class);
	
	@Resource(name="supportService")
	private SupportService supportService;
	
	@RequestMapping(value="/getAllSupportList.do")
	public ModelAndView getAllSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getAllSupportList");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.getAllSupportList(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/insertSupportRequest.do")
	public ModelAndView insertSupportRequest(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => insertSupportRequest");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.insertSupportRequest(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/uploadSupportFile.do")
	public ModelAndView uploadSupportFile(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => uploadNoticeFile");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.uploadSupportFile(paramMap, request);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
}
