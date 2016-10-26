package com.metelsos.common;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.menu.service.MenuService;

@Controller
public class PageController {
	private Log log = LogFactory.getLog(PageController.class);
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@RequestMapping(value="/leftMenuPageMove.do")
	public ModelAndView movePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => movePage");
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
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
		ModelAndView modelAndView = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setLeavePageItems(returnMap, paramMap);
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName(String.valueOf(returnMap.get("menuPath")));
		return modelAndView;
	}
}
