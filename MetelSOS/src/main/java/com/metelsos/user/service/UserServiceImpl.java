package com.metelsos.user.service;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.metelsos.user.controller.UserController;
import com.metelsos.user.dao.UserDao;
import com.metelsos.user.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private Log log = LogFactory.getLog(UserServiceImpl.class); 
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	/**
	 * 로그인 시 유저가 존재하는지 체크
	 */
	public HashMap<String, Object> loginUser(HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		UserVo userVo = userDao.checkUser(paramMap);
		
		if(userVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", paramMap.get("userID"));
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("userName", userVo.getUser_name());
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}
	
	/**
	 * 회원가입 할 때 아이디 중복확인 체크
	 */
	public HashMap<String, Object> validateId(HashMap<String, String> paramMap) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		UserVo userVo = userDao.checkUserById(paramMap);
		
		if(userVo != null){
			//아이디가 존재할 시
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			//아이디가 존재하지 않을 시 중복확인 성공
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}
	
	/**
	 * 회원가입 
	 */
	public HashMap<String, Object> insertUser(HashMap<String, String> paramMap) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//한글 깨짐 문제 해결
		String userName = paramMap.get("userName");
		log.info("#userName ===== >>>>>"+userName);
		//paramMap.put("userName", new String(userName.getBytes("iso-8859-1"), "utf-8"));
		
		//본사 소속이면 user_dept도 Head로 세팅
		if("Head".equals(paramMap.get("userCode"))){
			paramMap.put("userDept", "Head");
		}
		
		//회원 생성일 값 세팅 
		paramMap.put("USER_CREATE_DATE", df.format(now));
		//마지막 접속일 값 00000000000000으로 세팅
		paramMap.put("LAST_CONNECT_DATE", "00000000000000");
		
		int result = userDao.insertUser(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
				
		
		return returnMap;
	}
}
