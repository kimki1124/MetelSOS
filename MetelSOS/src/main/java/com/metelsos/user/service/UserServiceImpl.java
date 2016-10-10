package com.metelsos.user.service;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.metelsos.user.dao.UserDao;
import com.metelsos.user.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	public HashMap<String, Object> loginUser(HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		UserVo userVo = userDao.checkUser(paramMap);
		
		if(userVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", paramMap.get("userId"));
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("user", userVo);
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}
}
