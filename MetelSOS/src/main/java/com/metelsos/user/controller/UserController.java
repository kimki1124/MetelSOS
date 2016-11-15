package com.metelsos.user.controller;

import java.net.URLDecoder;
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
import com.metelsos.customer.service.CustomerService;
import com.metelsos.engineer.service.EngineerService;
/**
 * 
* <pre>
* com.metelsos.user.controller
*   |_ UserController.java
* </pre>
* 
* Desc : 엔지니어, 고객사 공통으로 사용하는 회원 관련 컨트롤러 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오후 9:08:04
* @Version :
 */
@Controller
public class UserController {
	private Log log = LogFactory.getLog(UserController.class);
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@Resource(name="engineerService")
	private EngineerService engineerService;
	/**
	 * 
	 * Desc : 로그인
	 * @Method Name : loginUser
	 * @param paramMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 로그아웃
	 * @Method Name : logoutUser
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout.do")
	public ModelAndView logoutUser(HttpServletRequest request, HttpSession session) throws Exception{
		log.info("#operation => logout");
		ModelAndView modelAndView = new ModelAndView();
		request.getSession().invalidate();
		modelAndView.setViewName("redirect:login.jsp");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 아이디 찾기
	 * @Method Name : findIdUser
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findId.do")
	public ModelAndView findIdUser(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => findIdUser");
		//MetelSOSJsonModel jsonModel = null;
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		paramMap.put("userName", URLDecoder.decode(paramMap.get("userName"), "UTF-8"));
		paramMap.put("userEmail", URLDecoder.decode(paramMap.get("userEmail"), "UTF-8"));
		
		if("Engineer".equals(paramMap.get("userType"))){
			//본사 엔지니어 아이디 찾는 경우
			returnMap = engineerService.findEngineerId(paramMap);
		}else{
			//고객사 고객 아이디 찾는 경우
			returnMap = customerService.findCustomerId(paramMap);
		}
		
		//jsonModel = new MetelSOSJsonModel(returnMap);
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/find/FindIdResult");
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 패스워드 찾기 페이지로 이동할때 필요한 데이터 SET
	 * @Method Name : moveFindPasswdPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findPasswdPageMove.do")
	public ModelAndView moveFindPasswdPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveFindPasswdPage");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(paramMap);
		modelAndView.setViewName("/find/FindPasswd");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 임시 비밀번호 이메일로 전송
	 * @Method Name : sendTempUserPasswd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sendTempPasswd.do")
	public ModelAndView sendTempUserPasswd(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => sendTempUserPasswd");
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("Engineer".equals(paramMap.get("userType"))){
			//본사 엔지니어 비밀번호 찾는 경우
			returnMap = engineerService.sendTempEngineerPasswd(paramMap);
		}else{
			//고객사 고객 비밀번호 찾는 경우
			returnMap = customerService.sendTempCustomerPasswd(paramMap);
		}
		
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/find/FindPasswdResult");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 회원 탈퇴
	 * @Method Name : deleteUserAccount
	 * @param paramMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteUser.do")
	public ModelAndView deleteUserAccount(@RequestParam HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		log.info("#operation => deleteUserAccount");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		if("engineer".equals(paramMap.get("userType"))){
			returnMap = engineerService.deleteEngineerAccount(paramMap);
		}else{
			returnMap = customerService.deleteCustomerAccount(paramMap);
		}
		
		if("SUCCESS".equals(returnMap.get("resultMsg"))){
			request.getSession().invalidate();
		}

		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
}

