package com.metelsos.customer.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

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
		
		CustomerVo customerVo = customerDao.checkLogin(paramMap);
		
		if(customerVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", customerVo.getCustomer_id());
			session.setAttribute("SESSION_LOGIN_USER_TYPE", "CUSTOMER");
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
		
		int result = customerDao.insertUser(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

}
