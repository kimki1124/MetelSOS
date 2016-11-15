package com.metelsos.engineer.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.metelsos.common.aes.AesUtil;
import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.engineer.dao.EngineerDao;
import com.metelsos.engineer.vo.EngineerVo;
/**
 * 
* <pre>
* com.metelsos.engineer.service
*   |_ EngineerServiceImpl.java
* </pre>
* 
* Desc : 엔지니어 회원관련 서비스 구현 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 6:10:40
* @Version :
 */
@Service("engineerService")
public class EngineerServiceImpl implements EngineerService{

	@Resource(name="engineerDao")
	private EngineerDao engineerDao;
	
	/**
	 * 
	 * Desc : 입력받은 ID와 비밀번호로 DB에 조회해서 회원이 있으면 세션 부여하고 로그인 처리
	 * @Method Name : checkLogin
	 * @param paramMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> checkLogin(HashMap<String, String> paramMap, HttpServletRequest request,
			HttpSession session) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		AesUtil aesUtil = new AesUtil();
		String securityPasswd = aesUtil.encrypt(paramMap.get("engineerPasswd"));
		paramMap.put("engineerPasswd", securityPasswd);
		
		EngineerVo engineerVo = engineerDao.checkLogin(paramMap);
		
		if(engineerVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", engineerVo.getEngineer_id());
			session.setAttribute("SESSION_LOGIN_USER_NAME", engineerVo.getEngineer_name());
			session.setAttribute("SESSION_LOGIN_USER_TYPE", "engineer");
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("engineerName", engineerVo.getEngineer_name());
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 엔지니어회원 ID validation
	 * @Method Name : validateEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * Desc : 엔지니어 회원 가입 시 회원가입 폼에 받은 정보 DB에 INSERT
	 * @Method Name : insertEngineer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> insertEngineer(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//유저생성일 set
		paramMap.put("engineerCreateDate", df.format(now));
		
		//유저 패스워드 암호화 후 set
		AesUtil aesUtil = new AesUtil();
		String securityPasswd = aesUtil.encrypt(paramMap.get("engineerPasswd"));
		paramMap.put("engineerPasswd", securityPasswd);
		
		int result = engineerDao.insertUser(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 엔지니어 회원 아이디 찾기
	 * @Method Name : findEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> findEngineerId(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<EngineerVo> list = engineerDao.findEngineerId(paramMap);
		
		returnMap.put("userType", paramMap.get("userType"));
		returnMap.put("findUserList", list);
		returnMap.put("userCount", list.size());
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 엔지니어 회원에게 이메일로 임시 비밀번호 보내기
	 * @Method Name : sendTempEngineerPasswd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> sendTempEngineerPasswd(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		//1. 아이디를 토대로 EngineerVo를 찾아냄
		EngineerVo vo = engineerDao.findEngineer(paramMap);
		
		//2. 리턴되는 EngineerVo가 있으면 임시 비밀번호 생성, 없으면 실패 메시지 put해서 리턴
		if(vo != null){
			MetelSOSUtil util = new MetelSOSUtil();
			AesUtil aesUtil = new AesUtil();
			String tempPasswd = util.generateTempPasswd("P", 20);
			
			//3. EngineerVo의 비밀번호를 임시 비밀번호로 변경해서 METELSOS_ENGINEER 테이블에 UPDATE
			vo.setEngineer_passwd(aesUtil.encrypt(tempPasswd));
			engineerDao.updateEngineerPasswd(vo);
			
			//4. EngineerVo의 이메일로 임시 비밀번호 전송
			String title = "임시 비밀번호입니다.";
			StringBuffer content = new StringBuffer();
			content.append("<h1>임시 비밀번호입니다.</h1><br />");
			content.append("<strong>"+tempPasswd+"</strong>");
			content.append("<br /><br />");
			content.append("임시 비밀번호로 접속 후 마이프로필에서 비밀번호를 변경해 주시기 바랍니다. <br />");
			content.append("from. MetelSOS ADMIN");
			util.sendEmail(vo.getEngineer_email(), title, content.toString());
			returnMap.put("tempPasswd", tempPasswd);
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 엔지니어회원 개인정보 수정 
	 * @Method Name : updateEngineerInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateEngineerInfo(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		AesUtil aesUtil = new AesUtil();
		paramMap.put("engineerPasswd", aesUtil.encrypt(paramMap.get("currPasswd")));
		EngineerVo vo = engineerDao.checkLogin(paramMap);
		
		if(vo != null){
			String changePwd = aesUtil.encrypt(paramMap.get("newPasswd"));
			vo.setEngineer_passwd(changePwd);
			vo.setEngineer_dept(paramMap.get("engineerDept"));
			vo.setEngineer_email(paramMap.get("engineerEmail"));
			vo.setEngineer_phone(paramMap.get("engineerPhone"));
			
			engineerDao.updateEngineerInfo(vo);
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("userType", "engineer");
			returnMap.put("userId", vo.getEngineer_id());
		}else{
			returnMap.put("resultMsg", "FAILED");
			returnMap.put("errMsg", "기존 비밀번호가 틀렸습니다. 다시 확인해주세요.");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 엔지니어 회원 탈퇴 시 해당 회원의 정보 DB에서 DELETE
	 * @Method Name : deleteEngineerAccount
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> deleteEngineerAccount(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		AesUtil aesUtil = new AesUtil();
		paramMap.put("userPasswd", aesUtil.encrypt(paramMap.get("inputPasswd")));
		
		int result = engineerDao.deleteEngineerAccount(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

}
