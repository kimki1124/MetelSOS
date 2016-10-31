package com.metelsos.menu.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.metelsos.menu.vo.MenuVo;

public interface MenuService {

	List<MenuVo> getEngineerLeftMenuList() throws Exception;

	List<MenuVo> getCustomerLeftMenuList() throws Exception;

	HashMap<String, Object> getMainPanelItems(HashMap<String, String> paramMap) throws Exception;

	void setProfileViewItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setLeavePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setNoticePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

	void setWriteNoticePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception;

}
