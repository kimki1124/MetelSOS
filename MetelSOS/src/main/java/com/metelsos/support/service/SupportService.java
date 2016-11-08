package com.metelsos.support.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface SupportService {

	HashMap<String, Object> getAllSupportList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> insertSupportRequest(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> uploadSupportFile(HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception;

}
