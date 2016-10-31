package com.metelsos.menu.service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metelsos.common.aes.AesUtil;
import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.company.dao.CompanyDao;
import com.metelsos.company.vo.CompanyVo;
import com.metelsos.customer.dao.CustomerDao;
import com.metelsos.customer.vo.CustomerVo;
import com.metelsos.engineer.dao.EngineerDao;
import com.metelsos.engineer.vo.EngineerVo;
import com.metelsos.meeting.dao.CSTMRMeetingDao;
import com.metelsos.meeting.dao.INRMeetingDao;
import com.metelsos.meeting.vo.CSTMRMeetingVo;
import com.metelsos.meeting.vo.INRMeetingVo;
import com.metelsos.menu.dao.MenuDao;
import com.metelsos.menu.vo.MenuVo;
import com.metelsos.notice.dao.NoticeDao;
import com.metelsos.notice.vo.FileVo;
import com.metelsos.notice.vo.NoticeVo;
import com.metelsos.support.dao.SupportDao;
import com.metelsos.support.vo.SupportVo;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Resource(name="menuDao")
	private MenuDao menuDao;
	
	@Resource(name="engineerDao")
	private EngineerDao engineerDao;
	
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	
	@Resource(name="companyDao")
	private CompanyDao companyDao;
	
	@Resource(name="supportDao")
	private SupportDao supportDao;
	
	@Resource(name="cstmrmeetingDao")
	private CSTMRMeetingDao cstmrmeetingDao;
	
	@Resource(name="inrmeetingDao")
	private INRMeetingDao inrmeetingDao;
	
	@Resource(name="noticeDao")
	private NoticeDao noticeDao;

	@Override
	public List<MenuVo> getEngineerLeftMenuList() throws Exception {
		List<MenuVo> list = new ArrayList<MenuVo>();
		List<MenuVo> filterList = new ArrayList<MenuVo>();
		List<MenuVo> childList = null;
		list = menuDao.getEngineerLeftMenuList();
		MenuVo vo = new MenuVo();
		MenuVo tempVo = new MenuVo();
		
		// 메뉴리스트 가공
		for(int i=0;i<list.size();i++){
			childList = new ArrayList<MenuVo>();
			vo = list.get(i);
			for(int j=0;j<list.size();j++){
				tempVo = list.get(j);
				if(tempVo.getMenu_parent_code() == vo.getMenu_code()){
					childList.add(tempVo);
				}
			}
			vo.setChildren(childList);
			
			if(vo.getMenu_parent_code() == 0){
				filterList.add(vo);
			}
		}
		
		return filterList;
	}

	@Override
	public List<MenuVo> getCustomerLeftMenuList() throws Exception {
		List<MenuVo> list = new ArrayList<MenuVo>();
		List<MenuVo> filterList = new ArrayList<MenuVo>();
		List<MenuVo> childList = null;
		list = menuDao.getCustomerLeftMenuList();
		MenuVo vo = new MenuVo();
		MenuVo tempVo = new MenuVo();
		
		// 메뉴리스트 가공
		for(int i=0;i<list.size();i++){
			childList = new ArrayList<MenuVo>();
			vo = list.get(i);
			
			for(int j=0;j<list.size();j++){
				tempVo = list.get(j);
				if(tempVo.getMenu_parent_code() == vo.getMenu_code()){
					childList.add(tempVo);
				}
			}
			vo.setChildren(childList);
			
			if(vo.getMenu_parent_code() == 0){
				filterList.add(vo);
			}
		}
		
		return filterList;
	}

	@Override
	public HashMap<String, Object> getMainPanelItems(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		List<MenuVo> filterList = new ArrayList<MenuVo>();
		List<MenuVo> childList = null;
		MenuVo vo = new MenuVo();
		MenuVo tempVo = new MenuVo();
		List<String> breadcrumbList = new ArrayList<String>();
		List<String> menuEngTitleList = new ArrayList<String>();
		
		if("engineer".equals(paramMap.get("userType"))){
			//엔지니어일 때
			menuList = menuDao.getEngineerLeftMenuList();
		}else if("customer".equals(String.valueOf(paramMap.get("userType")))){
			//고객일 때
			menuList = menuDao.getCustomerLeftMenuList();
		}
		
		//메뉴리스트 가공
		for(int i=0;i<menuList.size();i++){
			childList = new ArrayList<MenuVo>();
			vo = menuList.get(i);
			for(int j=0;j<menuList.size();j++){
				tempVo = menuList.get(j);
				if(tempVo.getMenu_parent_code() == vo.getMenu_code()){
					childList.add(tempVo);
				}
			}
			vo.setChildren(childList);
			
			if(vo.getMenu_parent_code() == 0){
				filterList.add(vo);
			}
		}
		
		setMainBreadcrumb(menuList, breadcrumbList, menuEngTitleList, paramMap.get("menuTitle"));
		
		//arraylist 순서 뒤집기
		Collections.reverse(breadcrumbList);
		Collections.reverse(menuEngTitleList);
		
		String menuPath = "";
		
		for(int i=0;i<menuEngTitleList.size();i++){
			menuPath += "/"+menuEngTitleList.get(i);
		}
		
		//set breadcrumb value
		returnMap.put("menuList", filterList);
		returnMap.put("breadcrumbList", breadcrumbList);
		returnMap.put("menuPath", menuPath);
		if(breadcrumbList.size() > 0){
			returnMap.put("menuTitle", breadcrumbList.get(breadcrumbList.size()-1));
		}
		
		returnMap.put("menuIcon", paramMap.get("menuIcon"));
		
		return returnMap;
	}

	private void setMainBreadcrumb(List<MenuVo> menuList, List<String> breadcrumbList, List<String> menuEngTitleList, String menu_title) throws Exception{
		MenuVo vo = new MenuVo();
		MenuVo tempVo = new MenuVo();
		
		for(int i=0;i<menuList.size();i++){
			vo = menuList.get(i);
			if(menu_title.equals(vo.getMenu_eng_title())){
				breadcrumbList.add(vo.getMenu_title());
				menuEngTitleList.add(vo.getMenu_eng_title());
				if(vo.getMenu_parent_code() != 0){
					for(int j=0;j<menuList.size();j++){
						tempVo = menuList.get(j);
						if(vo.getMenu_parent_code() == tempVo.getMenu_code()){
							setMainBreadcrumb(menuList, breadcrumbList, menuEngTitleList, tempVo.getMenu_title());
						}
					}
				}
			}
		}
	}

	@Override
	public void setProfileViewItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		
		if("engineer".equals(paramMap.get("userType"))){
			EngineerVo vo = engineerDao.findEngineer(paramMap);
			List<CSTMRMeetingVo> cstmrMeetingList = cstmrmeetingDao.getMeetingHistory(paramMap);
			List<INRMeetingVo> inrMeetingList = inrmeetingDao.getMeetingHistory(paramMap);
			
			for(int i=0;i<cstmrMeetingList.size();i++){
				CSTMRMeetingVo cstmrMeetingVo = cstmrMeetingList.get(i);
				cstmrMeetingVo.setMeeting_accept_date(util.changeDatePattern(cstmrMeetingVo.getMeeting_accept_date(), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			}
			
			for(int i=0;i<inrMeetingList.size();i++){
				INRMeetingVo inrMeetingVo = inrMeetingList.get(i);
				inrMeetingVo.setMeeting_date(util.changeDatePattern(inrMeetingVo.getMeeting_date(), "yyyyMMddHHmmss", "yyyy-MM-dd"));
			}
			
			returnMap.put("INRMeetingList", inrMeetingList);
			returnMap.put("CSTMRMeetingList", cstmrMeetingList);
			returnMap.put("engineerVo", vo);
			returnMap.put("menuPath", "/myprofile/EngineerViewMyProfile");
		}else{
			paramMap.put("customerId", paramMap.get("userId"));
			CustomerVo vo = customerDao.validateCustomerId(paramMap);
			List<CompanyVo> companyList = companyDao.setItemForRegisterForm();
			
			returnMap.put("customerCompanyList", companyList);
			returnMap.put("customerVo", vo);
			returnMap.put("menuPath", "/myprofile/CustomerViewMyProfile");
		}
		
		List<SupportVo> supportList = supportDao.getSupportHistory(paramMap);
		
		for(int i=0;i<supportList.size();i++){
			SupportVo supportVo = supportList.get(i);
			supportVo.setSupport_accept_date(util.changeDatePattern(supportVo.getSupport_accept_date(), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
		}
		
		returnMap.put("supportHistoryList", supportList);
		
		returnMap.put("currDate", util.currDatetoString("yyyy-MM-dd HH:mm:ss"));
		List<String> list = new ArrayList<String>();
		list.add("마이 프로필");
		
		returnMap.put("breadcrumbList", list);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}

	@Override
	public void setLeavePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		AesUtil aesUtil = new AesUtil();
		if("engineer".equals(paramMap.get("userType"))){
			EngineerVo vo = engineerDao.findEngineer(paramMap);
			String userPasswd = aesUtil.decrypt(vo.getEngineer_passwd());
			returnMap.put("userId", paramMap.get("userId"));
			returnMap.put("userPasswd", userPasswd);
		}else{
			paramMap.put("customerId", paramMap.get("userId"));
			CustomerVo vo = customerDao.validateCustomerId(paramMap);
			String userPasswd = aesUtil.decrypt(vo.getCustomer_passwd());
			returnMap.put("userId", paramMap.get("userId"));
			returnMap.put("userPasswd", userPasswd);
		}
		
		List<String> list = new ArrayList<String>();
		list.add("마이 프로필");
		returnMap.put("userType", paramMap.get("userType"));
		returnMap.put("menuPath", "/leave/LeaveUser");
		returnMap.put("breadcrumbList", list);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}

	@Override
	public void setNoticePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		List<NoticeVo> noticeList = noticeDao.getNoticeList();
		for(int i=0;i<noticeList.size();i++){
			NoticeVo vo = noticeList.get(i);
			vo.setNotice_date(util.changeDatePattern(vo.getNotice_date(), "yyyyMMddHHmmss", "yyyy-MM-dd"));
			
			int boardNum = vo.getNotice_num();
			List<FileVo> fileList = noticeDao.getNoticeFileList(boardNum);
			
			if(fileList.size() > 0){
				vo.setHas_file("Y");
			}else{
				vo.setHas_file("N");
			}
		}
		
		List<String> list = new ArrayList<String>();
		list.add("공지사항 관리");
		
		returnMap.put("noticeList", noticeList);
		returnMap.put("breadcrumbList", list);
		returnMap.put("currDate", util.currDatetoString("yyyy-MM-dd HH:mm:ss"));
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}

	@Override
	public void setWriteNoticePageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		returnMap.put("userName", URLDecoder.decode(paramMap.get("userName"), "UTF-8"));
		List<String> list = new ArrayList<String>();
		list.add("공지사항 관리");
		
		returnMap.put("breadcrumbList", list);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}
}
