package com.metelsos.support.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface SupportService {

	HashMap<String, Object> insertSupportRequest(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> uploadSupportFile(HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception;

	void setSupportDetailPageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> selectSupportFileInfo(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> deleteSupportFile(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> updateSupportRequest(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> deleteSupportHistory(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> getUnSupportList(HashMap<String, String> paramMap) throws Exception;

	void setUnsupportDetailItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> acceptSupport(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> getAcceptSupportList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> getSupportingList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> getCompleteSupportList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> getCusSupportList(HashMap<String, String> paramMap) throws Exception;

	void setUpdateSupportingFormItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> updateSupportState(HashMap<String, String> paramMap) throws Exception;

	void setUpdateCompleteSupportFormItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> updateSupportingState(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> getCompleteSupDetail(HashMap<String, String> paramMap) throws Exception;

}
