package com.metelsos.common;

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
	
	@RequestMapping(value="/pageMove.do")
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
}