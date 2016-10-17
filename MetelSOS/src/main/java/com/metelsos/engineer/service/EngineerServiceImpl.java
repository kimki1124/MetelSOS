package com.metelsos.engineer.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.metelsos.engineer.dao.EngineerDao;
import com.metelsos.engineer.vo.EngineerVo;

@Service("engineerService")
public class EngineerServiceImpl implements EngineerService{

	@Resource(name="engineerDao")
	private EngineerDao engineerDao;
	
	@Override
	public HashMap<String, Object> checkLogin(HashMap<String, String> paramMap, HttpServletRequest request,
			HttpSession session) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		EngineerVo engineerVo = engineerDao.checkLogin(paramMap);
		
		if(engineerVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", engineerVo.getEngineer_id());
			session.setAttribute("SESSION_LOGIN_USER_TYPE", "ENGINEER");
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("engineerName", engineerVo.getEngineer_name());
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> validateEngineerId(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		EngineerVo engineerVo = engineerDao.validateEngineerId(paramMap);
		
		if(engineerVo != null){
			returnMap.put("resultMsg", "FAILED");
		}else{
			returnMap.put("resultMsg", "SUCCESS");
		}
		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> insertEngineer(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		paramMap.put("engineerCreateDate", df.format(now));
		
		int result = engineerDao.insertUser(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

}
