package com.metelsos.customer.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CustomerService {

	HashMap<String, Object> checkLogin(HashMap<String, String> paramMap, HttpServletRequest request,
			HttpSession session) throws Exception;

	HashMap<String, Object> validateCustomerId(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> insertCustomer(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> findCustomerId(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> sendTempCustomerPasswd(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> updateCustomerInfo(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> deleteCustomerAccount(HashMap<String, String> paramMap)throws Exception;


}
