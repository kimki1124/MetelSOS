package com.metelsos.menu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.menu.service.MenuService;
import com.metelsos.menu.vo.MenuVo;

@Controller
public class MenuController {
	private Log log = LogFactory.getLog(MenuController.class);
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@RequestMapping(value="/EngineerMain.do")
	public ModelAndView setEngineerMain() throws Exception{
		log.info("#operation => setEngineerMain");
		ModelAndView modelAndView = new ModelAndView();
		List<MenuVo> list = menuService.getEngineerLeftMenuList();
		modelAndView.addObject("menuList", list);
		modelAndView.setViewName("EngineerMain");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/CustomerMain.do")
	public ModelAndView setCustomerMain() throws Exception{
		log.info("#operation => setCustomerMain");
		ModelAndView modelAndView = new ModelAndView();
		List<MenuVo> list = menuService.getCustomerLeftMenuList();
		modelAndView.addObject("menuList", list);
		modelAndView.setViewName("CustomerMain");
		
		return modelAndView;
	}
}
