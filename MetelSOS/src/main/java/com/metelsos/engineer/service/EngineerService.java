package com.metelsos.engineer.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface EngineerService {

	HashMap<String, Object> checkLogin(HashMap<String, String> paramMap, HttpServletRequest request,
			HttpSession session) throws Exception;

	HashMap<String, Object> validateEngineerId(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> insertEngineer(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> findEngineerId(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> sendTempEngineerPasswd(HashMap<String, String> paramMap) throws Exception;

}
