package com.metelsos.support.controller;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.menu.service.MenuService;
import com.metelsos.support.service.SupportService;
import com.metelsos.support.vo.FileVo;

@Controller
public class SupportController {
	private Log log = LogFactory.getLog(SupportController.class);
	
	@Resource(name="supportService")
	private SupportService supportService;
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@RequestMapping(value="/getWaitSupportList.do")
	public ModelAndView getWaitSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getWaitSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "접수대기");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/getCusAcceptSupportList.do")
	public ModelAndView getCusAcceptSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusAcceptSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "접수완료");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/getCusSupportingList.do")
	public ModelAndView getCusSupportingList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusSupportingList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 중");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/getCusCompleteSupportList.do")
	public ModelAndView getCusCompleteSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusCompleteSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 완료");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/insertSupportRequest.do")
	public ModelAndView insertSupportRequest(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => insertSupportRequest");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.insertSupportRequest(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/uploadSupportFile.do")
	public ModelAndView uploadSupportFile(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => uploadNoticeFile");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.uploadSupportFile(paramMap, request);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/moveSupportDetailPage.do")
	public ModelAndView moveSupportDetailPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveSupportDetailPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("userType", "customer");
		paramMap.put("menuTitle", "지원 히스토리");
		paramMap.put("menuIcon", "fa fa-lg fa-fw fa-history");
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setSupportDetailPageItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/customer/support/RequestSupportDetail");
		return model;
	}
	
	@RequestMapping(value="/downloadSupportFile.do")
	public void downloadSupportFile(@RequestParam HashMap<String, Object> paramMap, HttpServletResponse response) throws Exception{
		log.info("#operation => downloadSupportFile");
		HashMap<String, Object> returnMap = supportService.selectSupportFileInfo(paramMap);
		FileVo vo = (FileVo)returnMap.get("fileVo");
		
		String storedFileName = vo.getStored_file_name();
		String originalFileName = vo.getOriginal_file_name();
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\file\\support\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
	
	@RequestMapping(value="/moveSupportUpdatePage.do")
	public ModelAndView moveSupportUpdatePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveSupportUpdatePage ");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setSupportDetailPageItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/customer/support/RequestSupportUpdateForm");
		
		return model;
	}
	
	@RequestMapping(value="/deleteSupportFile.do")
	public ModelAndView deleteSupportFile(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => deleteSupportFile");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.deleteSupportFile(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/updateSupportRequest.do")
	public ModelAndView updateSupportRequest(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportRequest");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.updateSupportRequest(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/deleteSupportHistory.do")
	public ModelAndView deleteSupportHistory(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => deleteSupportHistory");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.deleteSupportHistory(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/getUnSupportList.do")
	public ModelAndView getUnSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getUnSupportList");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.getUnSupportList(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/moveUnsupportDetailPage.do")
	public ModelAndView moveUnsupportDetailPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveUnsupportDetailPage");
		ModelAndView model = new ModelAndView();
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setUnsupportDetailItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/engineer/support/UnsupportDetail");
		return model;
	}
	
	@RequestMapping(value="/acceptSupport.do")
	public ModelAndView acceptSupport(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => acceptSupport");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.acceptSupport(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/getAcceptSupportList.do")
	public ModelAndView getAcceptSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getAcceptSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "접수완료");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/getSupportingList.do")
	public ModelAndView getSupportingList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getSupportingList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 중");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/getCompleteSupportList.do")
	public ModelAndView getCompleteSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCompleteSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 완료");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/mvEngAcptSprtDtlPage.do")
	public ModelAndView mvEngAcptSprtDtlPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => mvEngAcptSprtDtlPage");
		ModelAndView model = new ModelAndView();
		
		paramMap.put("userType", "engineer");
		paramMap.put("menuTitle", "SupportList");
		paramMap.put("menuIcon", "fa fa-lg fa-fw fa-pencil-square-o");
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setUpdateSupportingFormItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/engineer/support/UpdateAcceptSupportForm");
		return model;
	}
	
	@RequestMapping(value="/updateSupportState.do")
	public ModelAndView updateSupportState(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportState");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.updateSupportState(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/mvEngSprtngDtlPage.do")
	public ModelAndView mvEngSprtngDtlPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => mvEngSprtngDtlPage");
		ModelAndView model = new ModelAndView();
		
		paramMap.put("userType", "engineer");
		paramMap.put("menuTitle", "SupportList");
		paramMap.put("menuIcon", "fa fa-lg fa-fw fa-pencil-square-o");
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setUpdateCompleteSupportFormItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/engineer/support/UpdateSupportingForm");
		return model;
	}
	
	@RequestMapping(value="/updateSupportingState.do")
	public ModelAndView updateSupportingState(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportingState");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.updateSupportingState(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	
	@RequestMapping(value="/getCompleteSupDetail.do")
	public ModelAndView getCompleteSupDetail(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCompleteSupDetail");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.getCompleteSupDetail(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
}
