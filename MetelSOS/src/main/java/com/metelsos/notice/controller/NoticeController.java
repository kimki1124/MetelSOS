package com.metelsos.notice.controller;

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
import com.metelsos.notice.service.NoticeService;
import com.metelsos.notice.vo.FileVo;

@Controller
public class NoticeController {
	private Log log = LogFactory.getLog(NoticeController.class);
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@RequestMapping(value="/insertNotice.do")
	public ModelAndView insertNoticeBoard(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => insertNoticeBoard");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = noticeService.insertNoticeBoard(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/uploadNoticeFile.do")
	public ModelAndView uploaNoticeFile(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => uploadNoticeFile");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = noticeService.uploadNoticeFile(paramMap, request);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/moveNoticeDetailPage.do")
	public ModelAndView moveNoticeDetailPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveNoticeDetailPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("userType", "engineer");
		paramMap.put("menuTitle", "공지사항");
		paramMap.put("menuIcon", "fa fa-lg fa-fw fa-bell");
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		noticeService.selectDetailNotice(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/notice/NoticeDetail");
		return model;
	}
	
	@RequestMapping(value="/downloadNoticeFile.do")
	public void downloadNoticeFile(@RequestParam HashMap<String, Object> paramMap, HttpServletResponse response) throws Exception{
		log.info("#operation => downloadNoticeFile");
		HashMap<String, Object> returnMap = noticeService.selectNoticeFileInfo(paramMap);
		FileVo vo = (FileVo)returnMap.get("fileVo");
		String storedFileName = vo.getStored_file_name();
		String originalFileName = vo.getOriginal_file_name();
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\file\\"+storedFileName));	
		
		response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
	
	@RequestMapping(value="/deleteNotice.do")
	public ModelAndView deleteNotice(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => deleteNotice");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = noticeService.deleteNotice(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/moveNoticeUpdatePage.do")
	public ModelAndView moveNoticeUpdatePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveNoticeUpdatePage");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		noticeService.selectDetailNotice(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/admin/notice/NoticeUpdateForm");
		return model;
	}
	
}
