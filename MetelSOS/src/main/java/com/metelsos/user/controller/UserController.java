package com.metelsos.user.controller;

import java.util.HashMap;

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
import com.metelsos.user.service.UserService;

@Controller
public class UserController {
	private Log log = LogFactory.getLog(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value="/login.do")
	public ModelAndView loginUser(@RequestParam HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		log.info("#operation => loginUser");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = userService.loginUser(paramMap, request, session);
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
	
	@RequestMapping(value="/register.do")
	public ModelAndView registerUser(@RequestParam HashMap<String, String> paramMap){
		log.info("#operation => registerUser");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:login.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/validateId.do")
	public ModelAndView validateId(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => validateId");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = userService.validateId(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
}
