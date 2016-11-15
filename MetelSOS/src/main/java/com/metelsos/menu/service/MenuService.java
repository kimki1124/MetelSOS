package com.metelsos.menu.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.metelsos.menu.vo.MenuVo;
/**
 * 
* <pre>
* com.metelsos.menu.service
*   |_ MenuService.java
* </pre>
* 
* Desc : LEFTMENU 관련 서비스 인터페이스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 9:18:47
* @Version :
 */
public interface MenuService {

	List<MenuVo> getEngineerLeftMenuList() throws Exception;

	List<MenuVo> getCustomerLeftMenuList() throws Exception;

	HashMap<String, Object> getMainPanelItems(HashMap<String, String> paramMap) throws Exception;

	void setProfileViewItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setLeavePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setNoticePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setWriteNoticePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setManageExclntStfPageItem(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setExclntStfListItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setManageNewEmplydPageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setNewEmplydListItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;
}
