package com.metelsos.common;

import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.menu.service.MenuService;
/**
 * 
* <pre>
* com.metelsos.common
*   |_ PageController.java
* </pre>
* 
* Desc : LeftMenu의 메뉴를 클릭으로 페이지를 이동 할 때 거쳐가는 Page Controller
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 3:22:14
* @Version :
 */
@Controller
public class PageController {
	private Log log = LogFactory.getLog(PageController.class);
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	/**
	 * 
	 * Desc : leftmenu의 메뉴를 클릭했을 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : movePage
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @param request - 세션 사용을 위한 HttpServletRequest 객체
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/leftMenuPageMove.do")
	public ModelAndView movePage(@RequestParam HashMap<String, String> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => movePage");
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("userId", (String) request.getSession().getAttribute("SESSION_LOGIN_USER_ID"));
		returnMap = menuService.getMainPanelItems(paramMap);
		modelAndView.addAllObjects(returnMap);
		String menuPath = String.valueOf(returnMap.get("menuPath"));
		String path = "/"+paramMap.get("userType")+menuPath;
		modelAndView.setViewName(path);
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 마이 프로필 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : moveProfilePage
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/profilePageMove.do")
	public ModelAndView moveProfilePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveProfilePage");
		ModelAndView modelAndView = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setProfileViewItems(returnMap, paramMap);
		
		modelAndView.addAllObjects(returnMap);
		String menuPath = String.valueOf(returnMap.get("menuPath"));
		String path = "/"+paramMap.get("userType")+menuPath;
		modelAndView.setViewName(path);
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 회원 탈퇴 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : moveLeavePage
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/leavePageMove.do")
	public ModelAndView moveLeavePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveLeavePage");
		ModelAndView modelAndView = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setLeavePageItems(returnMap, paramMap);
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName(String.valueOf(returnMap.get("menuPath")));
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 공지사항 관리 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : noticePageMove
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/noticePageMove.do")
	public ModelAndView noticePageMove(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => noticePageMove ");
		ModelAndView modelAndView = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setNoticePageItems(returnMap, paramMap);
		
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/admin/notice/NoticeList");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 공지사항 관리 페이지에서 등록 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : writeNoticePageMove
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/writeNoticePageMove.do")
	public ModelAndView writeNoticePageMove(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => writeNoticePageMove");
		ModelAndView modelAndView= new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setWriteNoticePageItems(returnMap, paramMap);	
		
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/admin/notice/NoticeForm");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * Desc : 우수사원 관리 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : moveManageExclntStfPage
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveManageExclntStfPage.do")
	public ModelAndView moveManageExclntStfPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveManageExclntStfPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setManageExclntStfPageItem(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/exclntstf/YearMonthList");
		return model;
	}
	
	/**
	 * 
	 * Desc : 우수사원 리스트 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : moveExclntStfListPage
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveExclntStfListPage.do")
	public ModelAndView moveExclntStfListPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveExclntStfListPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setExclntStfListItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/exclntstf/ExclntStfList");
		return model;
	}
	
	/**
	 * 신입사원 관리 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * Desc : 
	 * @Method Name : moveManageNewEmplydPage
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveManageNewEmplydPage.do")
	public ModelAndView moveManageNewEmplydPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveManageNewEmplydPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setManageNewEmplydPageItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/newemplyd/YearMonthList");
		return model;
	}
	
	/**
	 * 
	 * Desc : 신입사원 리스트 페이지로 넘어갈 때 leftmenu item, menuicon, menutitle 등의 값을 set해서 뷰로 리턴
	 * @Method Name : moveNewEmplydListPage
	 * @param paramMap - 뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveNewEmplydListPage.do")
	public ModelAndView moveNewEmplydListPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveNewEmplydListPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		menuService.setNewEmplydListItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/newemplyd/NewEmplydList");
		return model;
	}
}
