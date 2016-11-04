package com.metelsos.menu.service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
import com.metelsos.exclntstf.dao.ExclntStfDao;
import com.metelsos.exclntstf.vo.ExclntStfVo;
import com.metelsos.meeting.dao.CSTMRMeetingDao;
import com.metelsos.meeting.dao.INRMeetingDao;
import com.metelsos.meeting.vo.CSTMRMeetingVo;
import com.metelsos.meeting.vo.INRMeetingVo;
import com.metelsos.menu.dao.MenuDao;
import com.metelsos.menu.vo.MenuVo;
import com.metelsos.newemplyd.dao.NewemplydDao;
import com.metelsos.newemplyd.vo.NewemplydVo;
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
	
	@Resource(name="exclntstfDao")
	private ExclntStfDao exclntstfDao;
	
	@Resource(name="newemplydDao")
	private NewemplydDao newemplydDao;

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
		
		//menuTitle이 engineerMain이면 공지사항, 월별 유지보수 지원사항, 우수사원, 신입사원 item set
		if("EngineerMain".equals(paramMap.get("menuTitle"))){
			setNoticeBoard(returnMap);
			setMonthSupportStats(returnMap);
			setExclntStfItems(returnMap);
			setNewEmplydItems(returnMap);
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

	private void setNewEmplydItems(HashMap<String, Object> returnMap) throws Exception{
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		String year_month = String.valueOf(year)+String.valueOf(month+1);
		List<HashMap<String, Object>> newEmplydList = newemplydDao.getCurrNewEmplydList(year_month);
		
		if(newEmplydList.size() > 0){
			returnMap.put("newEmplydListCount", newEmplydList.size());
		}else{
			returnMap.put("newEmplydListCount", 0);
		}
		
		returnMap.put("newEmplydList", newEmplydList);
	}

	private void setExclntStfItems(HashMap<String, Object> returnMap) throws Exception{
		HashMap<String, String> paramMap = new HashMap<String, String>();
		int exclntStfCount = 0;
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		String stringMonth = String.valueOf(month+1);
		if(stringMonth.length() < 2){
			stringMonth = "0"+stringMonth;
		}
		String stringYear = String.valueOf(year);
		paramMap.put("year_month", stringMonth+"-"+stringYear);
		
		List<HashMap<String, Object>> list = exclntstfDao.selectCurrExclntStf(paramMap);
		if(list.size() > 0){
			exclntStfCount = list.size();
		}
		returnMap.put("exclntStfCount", exclntStfCount);
		returnMap.put("currExclntStfList", list);
	}

	private void setMonthSupportStats(HashMap<String, Object> returnMap) throws Exception{
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		int supportCompleteCount;
		int supportNotCompleteCount;
		//Calender의 MONTH는 0부터 시작 ex) 10월 - MONTH + 1
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		month = month + 1;
		
		supportCompleteCount = getSupportCompleteCount(0);
		supportNotCompleteCount = getSupportNotCompleteCount(0);
		returnMap.put("curr_support_complete_count", supportCompleteCount);
		returnMap.put("percent_curr_support_complete_count", ((double)supportCompleteCount/100) * 100);
		returnMap.put("curr_support_not_complete_count", supportNotCompleteCount);
		returnMap.put("percent_curr_support_not_complete_count", ((double)supportNotCompleteCount/100) * 100);
		returnMap.put("curr_support_total_count", supportCompleteCount + supportNotCompleteCount);
		returnMap.put("percent_curr_support_total_count", ((double)(supportCompleteCount + supportNotCompleteCount)/100) * 100);

			//현재 6월 이후일때
		for(int i=-5;i<0;i++){
			HashMap<String, Object> statsMap = new HashMap<String, Object>();
			int filterMonth = month + i;
			
			supportCompleteCount = getSupportCompleteCount(i);
			supportNotCompleteCount = getSupportNotCompleteCount(i);
			
			if(filterMonth == 0){
				year = year - 1;
				filterMonth = 12;
			}else if(filterMonth == -1){
				year = year - 1;
				filterMonth = 11;
			}else if(filterMonth == -2){
				year = year - 1;
				filterMonth = 10;
			}else if(filterMonth == -3){
				year = year - 1;
				filterMonth = 9;
			}else if(filterMonth == -4){
				year = year - 1;
				filterMonth = 8;
			}
			
			if(String.valueOf(filterMonth).length() == 1){
				statsMap.put("yearMonth", year+"-0"+filterMonth);
			}else{
				statsMap.put("yearMonth", year+"-"+filterMonth);
			}
			statsMap.put("support_complete_count", supportCompleteCount);
			statsMap.put("support_not_complete_count", supportNotCompleteCount);
			statsMap.put("support_total_count", supportCompleteCount + supportNotCompleteCount);
			list.add(statsMap);
		}
			
		returnMap.put("prevSupportCountList", list);

		
		returnMap.put("currMonth", month);
	}

	private int getSupportNotCompleteCount(int interval) throws Exception{
		return supportDao.selectSupportNotCompleteCount(interval);
	}

	private int getSupportCompleteCount(int interval) throws Exception{
		return supportDao.selectSupportCompleteCount(interval);
	}

	private void setNoticeBoard(HashMap<String, Object> returnMap) throws Exception{
		MetelSOSUtil util = new MetelSOSUtil();
		List<NoticeVo> noticeList = noticeDao.selectNotice5Rows();
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
		
		returnMap.put("noticeList", noticeList);
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

	@Override
	public void setManageExclntStfPageItem(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		
		List<String> list = new ArrayList<String>();
		list.add(paramMap.get("menuTitle"));
		
		returnMap.put("currYear", year);
		returnMap.put("breadcrumbList", list);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}

	@Override
	public void setExclntStfListItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception {
		String selectMonth = null;
		returnMap.put("selectMonth", paramMap.get("selectMonth"));
		returnMap.put("currYear", paramMap.get("currYear"));
		
		if(paramMap.get("selectMonth").length() < 2){
			selectMonth = "0"+paramMap.get("selectMonth");
			paramMap.put("selectMonth", selectMonth);
		}
		
		//우수사원 리스트
		List<ExclntStfVo> exclntstfList = exclntstfDao.getExclntStfList(paramMap);
		
		//부서 셀렉트박스에 들어갈 부서 리스트
		List<String> deptList = engineerDao.getDeptList();
		
		List<String> list = new ArrayList<String>();
		list.add(paramMap.get("menuTitle"));
		
		returnMap.put("deptList", deptList);
		returnMap.put("exclntstfList", exclntstfList);
		returnMap.put("breadcrumbList", list);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}

	@Override
	public void setManageNewEmplydPageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		
		List<String> list = new ArrayList<String>();
		list.add(paramMap.get("menuTitle"));
		
		returnMap.put("currYear", year);
		returnMap.put("breadcrumbList", list);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}

	@Override
	public void setNewEmplydListItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		String year_month = null;
		returnMap.put("selectMonth", paramMap.get("selectMonth"));
		returnMap.put("currYear", paramMap.get("currYear"));
		
		if(paramMap.get("selectMonth").length() < 2){
			paramMap.put("selectMonth", "0"+paramMap.get("selectMonth"));
		}
		
		year_month = paramMap.get("currYear")+paramMap.get("selectMonth");
		paramMap.put("year_month", year_month);
		
		List<HashMap<String, Object>> newEmplydList = newemplydDao.getNewEmplydList(paramMap);
		List<String> deptList = engineerDao.getDeptList();
		
		List<String> list = new ArrayList<String>();
		list.add(paramMap.get("menuTitle"));
		returnMap.put("deptList", deptList);
		returnMap.put("newEmplydList", newEmplydList);
		returnMap.put("breadcrumbList", list);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}
}
