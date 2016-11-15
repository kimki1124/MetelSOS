package com.metelsos.engineer.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * 
* <pre>
* com.metelsos.engineer.service
*   |_ EngineerService.java
* </pre>
* 
* Desc : 엔지니어회원 관련 서비스 인터페이스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 6:10:29
* @Version :
 */
public interface EngineerService {

	HashMap<String, Object> checkLogin(HashMap<String, String> paramMap, HttpServletRequest request,
			HttpSession session) throws Exception;

	HashMap<String, Object> validateEngineerId(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> insertEngineer(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> findEngineerId(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> sendTempEngineerPasswd(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> updateEngineerInfo(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> deleteEngineerAccount(HashMap<String, String> paramMap) throws Exception;


}
