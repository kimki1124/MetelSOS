package com.metelsos.menu.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.metelsos.menu.service.MenuService;
/**
 * 
* <pre>
* com.metelsos.menu.controller
*   |_ MenuController.java
* </pre>
* 
* Desc : LEFTMENU 관련 컨트롤러 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 9:18:11
* @Version :
 */
@Controller
public class MenuController {
	private Log log = LogFactory.getLog(MenuController.class);
	
	@Resource(name="menuService")
	private MenuService menuService;
}
