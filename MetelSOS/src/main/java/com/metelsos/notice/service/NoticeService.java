package com.metelsos.notice.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface NoticeService {

	HashMap<String, Object> insertNoticeBoard(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> uploadNoticeFile(HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception;

	void selectDetailNotice(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> selectNoticeFileInfo(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> deleteNotice(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> deleteNoticeFile(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> updateNotice(HashMap<String, String> paramMap) throws Exception;

}
