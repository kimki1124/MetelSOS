package com.metelsos.menu.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.metelsos.menu.service.MenuService;

@Controller
public class MenuController {
	private Log log = LogFactory.getLog(MenuController.class);
	
	@Resource(name="menuService")
	private MenuService menuService;
}
