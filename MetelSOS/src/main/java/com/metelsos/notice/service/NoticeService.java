package com.metelsos.notice.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
/**
 * 
* <pre>
* com.metelsos.notice.service
*   |_ NoticeService.java
* </pre>
* 
* Desc : 공지사항 관련 서비스 인터페이스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 10:38:08
* @Version :
 */
public interface NoticeService {

	HashMap<String, Object> insertNoticeBoard(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> uploadNoticeFile(HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception;

	void selectDetailNotice(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> selectNoticeFileInfo(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> deleteNotice(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> deleteNoticeFile(HashMap<String, Object> paramMap) throws Exception;

	HashMap<String, Object> updateNotice(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> selectNoticeList(HashMap<String, String> paramMap) throws Exception;

}
