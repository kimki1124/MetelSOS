package com.metelsos.customer.service;

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
import com.metelsos.customer.dao.CustomerDao;
import com.metelsos.customer.vo.CustomerVo;
/**
 * 
* <pre>
* com.metelsos.customer.service
*   |_ CustomerServiceImpl.java
* </pre>
* 
* Desc : 고객사 고객 관련 서비스 구현 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 5:53:44
* @Version :
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Resource(name="customerDao")
	private CustomerDao customerDao;

	/**
	 * 
	 * Desc : 로그인 비즈니스 로직 수행, 로그인 성공 시 세션 생성 후 세션에 속성 값 세팅
	 * @Method Name : checkLogin
	 * @param paramMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> checkLogin(HashMap<String, String> paramMap, HttpServletRequest request,
			HttpSession session) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		AesUtil aesUtil = new AesUtil();
		String securityPasswd = aesUtil.encrypt(paramMap.get("customerPasswd"));
		paramMap.put("customerPasswd", securityPasswd);
		
		CustomerVo customerVo = customerDao.checkLogin(paramMap);
		
		if(customerVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", customerVo.getCustomer_id());
			session.setAttribute("SESSION_LOGIN_USER_NAME", customerVo.getCustomer_name());
			session.setAttribute("SESSION_LOGIN_USER_TYPE", "customer");
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("customerName", customerVo.getCustomer_name());
			returnMap.put("customerId", customerVo.getCustomer_id());
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 고객회원가입 시 ID validation 검사
	 * @Method Name : validateCustomerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> validateCustomerId(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		CustomerVo customerVo = customerDao.validateCustomerId(paramMap);
		
		if(customerVo != null){
			returnMap.put("resultMsg", "FAILED");
		}else{
			returnMap.put("resultMsg", "SUCCESS");
		}
		return returnMap;
	}

	/**
	 * 
	 * Desc : 고객회원가입 폼에 작성한 고객정보 INSERT
	 * @Method Name : insertCustomer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> insertCustomer(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		paramMap.put("customerCreateDate", df.format(now));
		
		//유저 패스워드 암호화 후 set
		AesUtil aesUtil = new AesUtil();
		String securityPasswd = aesUtil.encrypt(String.valueOf(paramMap.get("customerPasswd")));
		paramMap.put("customerPasswd", securityPasswd);
		
		int result = customerDao.insertUser(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 고객회원 ID 찾기
	 * @Method Name : findCustomerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> findCustomerId(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<CustomerVo> list = customerDao.findCustomerId(paramMap);
		
		returnMap.put("userType", paramMap.get("userType"));
		returnMap.put("findUserList", list);
		returnMap.put("userCount", list.size());
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 이메일로 고객회원 임시 비밀번호 전송 
	 * @Method Name : sendTempCustomerPasswd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> sendTempCustomerPasswd(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		CustomerVo vo = customerDao.findCustomer(paramMap);
		
		if(vo != null){
			MetelSOSUtil util = new MetelSOSUtil();
			AesUtil aesUtil = new AesUtil();
			String tempPasswd = util.generateTempPasswd("P", 20);
			
			vo.setCustomer_passwd(aesUtil.encrypt(tempPasswd));
			customerDao.updateCustomerPasswd(vo);
			
			String title = "임시 비밀번호입니다.";
			StringBuffer content = new StringBuffer();
			content.append("<h1>임시 비밀번호입니다.</h1><br />");
			content.append("<strong>"+tempPasswd+"</strong>");
			content.append("<br /><br />");
			content.append("임시 비밀번호로 접속 후 마이프로필에서 비밀번호를 변경해 주시기 바랍니다. <br />");
			content.append("from. MetelSOS ADMIN");
			util.sendEmail(vo.getCustomer_email(), title, content.toString());
			returnMap.put("tempPasswd", tempPasswd);
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 고객회원 개인정보 수정 시 업데이트 
	 * @Method Name : updateCustomerInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateCustomerInfo(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		AesUtil aesUtil = new AesUtil();
		paramMap.put("customerPasswd", aesUtil.encrypt(paramMap.get("currPasswd")));
		CustomerVo vo = customerDao.checkLogin(paramMap);
		
		if(vo != null){
			String changePwd = aesUtil.encrypt(paramMap.get("newPasswd"));
			vo.setCustomer_passwd(changePwd);
			vo.setCompany_name(paramMap.get("companyName"));
			vo.setCustomer_position(paramMap.get("customerPosition"));
			vo.setCustomer_email(paramMap.get("customerEmail"));
			vo.setCustomer_phone(paramMap.get("customerPhone"));
			
			customerDao.updateCustomerInfo(vo);
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("userType", "customer");
			returnMap.put("userId", vo.getCustomer_id());
		}else{
			returnMap.put("resultMsg", "FAILED");
			returnMap.put("errMsg", "기존 비밀번호가 틀렸습니다. 다시 확인해주세요.");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 고객회원탈퇴 시 DB에서 해당 고객회원정보 DELETE
	 * @Method Name : deleteCustomerAccount
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> deleteCustomerAccount(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		AesUtil aesUtil = new AesUtil();
		paramMap.put("userPasswd", aesUtil.encrypt(paramMap.get("inputPasswd")));
		
		int result = customerDao.deleteCustomerAccount(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

}
