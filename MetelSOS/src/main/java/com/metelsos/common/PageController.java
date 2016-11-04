package com.metelsos.common;

import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.menu.service.MenuService;

@Controller
public class PageController {
	private Log log = LogFactory.getLog(PageController.class);
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@RequestMapping(value="/leftMenuPageMove.do")
	public ModelAndView movePage(@RequestParam HashMap<String, String> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => movePage");
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		returnMap.put("userId", request.getSession().getAttribute("SESSION_LOGIN_USER_ID"));
		modelAndView.addAllObjects(returnMap);
		String menuPath = String.valueOf(returnMap.get("menuPath"));
		String path = "/"+paramMap.get("userType")+menuPath;
		modelAndView.setViewName(path);
		return modelAndView;
	}
	
	@RequestMapping(value="/profilePageMove.do")
	public ModelAndView moveProfilePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveProfilePage");
		ModelAndView modelAndView = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setProfileViewItems(returnMap, paramMap);
		
		modelAndView.addAllObjects(returnMap);
		String menuPath = String.valueOf(returnMap.get("menuPath"));
		String path = "/"+paramMap.get("userType")+menuPath;
		modelAndView.setViewName(path);
		return modelAndView;
	}
	
	@RequestMapping(value="/leavePageMove.do")
	public ModelAndView moveLeavePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveLeavePage");
		ModelAndView modelAndView = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setLeavePageItems(returnMap, paramMap);
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName(String.valueOf(returnMap.get("menuPath")));
		return modelAndView;
	}
	
	@RequestMapping(value="/noticePageMove.do")
	public ModelAndView noticePageMove(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => noticePageMove ");
		ModelAndView modelAndView = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setNoticePageItems(returnMap, paramMap);
		
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/admin/notice/NoticeList");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/writeNoticePageMove.do")
	public ModelAndView writeNoticePageMove(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => writeNoticePageMove");
		ModelAndView modelAndView= new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setWriteNoticePageItems(returnMap, paramMap);	
		
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/admin/notice/NoticeForm");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/moveManageExclntStfPage.do")
	public ModelAndView moveManageExclntStfPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveManageExclntStfPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setManageExclntStfPageItem(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/exclntstf/YearMonthList");
		return model;
	}
	
	@RequestMapping(value="/moveExclntStfListPage.do")
	public ModelAndView moveExclntStfListPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveExclntStfListPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setExclntStfListItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/exclntstf/ExclntStfList");
		return model;
	}
	
	@RequestMapping(value="/moveManageNewEmplydPage.do")
	public ModelAndView moveManageNewEmplydPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveManageNewEmplydPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setManageNewEmplydPageItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/newemplyd/YearMonthList");
		return model;
	}
	
	@RequestMapping(value="/moveNewEmplydListPage.do")
	public ModelAndView moveNewEmplydListPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveNewEmplydListPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setNewEmplydListItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/newemplyd/NewEmplydList");
		return model;
	}
	
}
