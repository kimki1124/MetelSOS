package com.metelsos.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.customer.service.CustomerService;
import com.metelsos.customer.vo.CustomerVo;
import com.metelsos.engineer.service.EngineerService;
import com.metelsos.user.service.UserService;

@Controller
public class UserController {
	private Log log = LogFactory.getLog(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@Resource(name="engineerService")
	private EngineerService engineerService;
	
	@RequestMapping(value="/login.do")
	public ModelAndView loginUser(@RequestParam HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		log.info("#operation => loginUser");
		//ModelAndView modelAndView = new ModelAndView();
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("Engineer".equals(paramMap.get("userType"))){
			//엔지니어가 로그인 하는 경우
			returnMap = engineerService.checkLogin(paramMap, request, session);
		}else{
			//고객이 로그인 하는 경우
			returnMap = customerService.checkLogin(paramMap, request, session);
		}
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/logout.do")
	public ModelAndView logoutUser(HttpServletRequest request, HttpSession session) throws Exception{
		log.info("#operation => logout");
		ModelAndView modelAndView = new ModelAndView();
		request.getSession().invalidate();
		modelAndView.setViewName("redirect:login.jsp");
		
		return modelAndView;
	}
}
