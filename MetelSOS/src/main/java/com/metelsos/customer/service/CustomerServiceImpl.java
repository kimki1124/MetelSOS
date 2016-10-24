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

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Resource(name="customerDao")
	private CustomerDao customerDao;

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
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

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

	@Override
	public HashMap<String, Object> findCustomerId(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<CustomerVo> list = customerDao.findEngineerId(paramMap);
		
		returnMap.put("userType", paramMap.get("userType"));
		returnMap.put("findUserList", list);
		returnMap.put("userCount", list.size());
		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> sendTempCustomerPasswd(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		CustomerVo vo = customerDao.findEngineer(paramMap);
		
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
			util.sendEmail(vo.getCustomer_email(), title, content.toString(), tempPasswd);
			returnMap.put("tempPasswd", tempPasswd);
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

}
