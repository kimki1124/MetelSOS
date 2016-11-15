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
/**
 * 
* <pre>
* com.metelsos.notice.controller
*   |_ NoticeController.java
* </pre>
* 
* Desc : 공지사항 관련 컨트롤러 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 10:27:18
* @Version :
 */
@Controller
public class NoticeController {
	private Log log = LogFactory.getLog(NoticeController.class);
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	@Resource(name="menuService")
	private MenuService menuService;
	/**
	 * 
	 * Desc : 공지사항 리스트 가져옴
	 * @Method Name : selectNoticeList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectNoticeList.do")
	public ModelAndView selectNoticeList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => selectNoticeList");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = noticeService.selectNoticeList(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 공지사항 작성 후 DB에 INSERT
	 * @Method Name : insertNoticeBoard
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertNotice.do")
	public ModelAndView insertNoticeBoard(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => insertNoticeBoard");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = noticeService.insertNoticeBoard(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 공지사항 등록시 첨부한 파일에 대한 정보를 DB에 INSERT
	 * @Method Name : uploaNoticeFile
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadNoticeFile.do")
	public ModelAndView uploaNoticeFile(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => uploadNoticeFile");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = noticeService.uploadNoticeFile(paramMap, request);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 공지사항 글 상세보기 페이지로 이동할때 필요한 데이터 SET
	 * @Method Name : moveNoticeDetailPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 글 상세보기에서 첨부파일 다운로드
	 * @Method Name : downloadNoticeFile
	 * @param paramMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadNoticeFile.do")
	public void downloadNoticeFile(@RequestParam HashMap<String, Object> paramMap, HttpServletResponse response) throws Exception{
		log.info("#operation => downloadNoticeFile");
		HashMap<String, Object> returnMap = noticeService.selectNoticeFileInfo(paramMap);
		FileVo vo = (FileVo)returnMap.get("fileVo");
		String storedFileName = vo.getStored_file_name();
		String originalFileName = vo.getOriginal_file_name();
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\file\\notice\\"+storedFileName));	
		
		response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
	/**
	 * 
	 * Desc : 공지 글 삭제
	 * @Method Name : deleteNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteNotice.do")
	public ModelAndView deleteNotice(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => deleteNotice");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = noticeService.deleteNotice(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 공지사항 글 수정 페이지에 뿌릴 데이터 SET
	 * @Method Name : moveNoticeUpdatePage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 공지 글 수정 시 업로드한 첨부파일 삭제
	 * @Method Name : deleteNoticeFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteNoticeFile.do")
	public ModelAndView deleteNoticeFile(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => deleteNoticeFile");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = noticeService.deleteNoticeFile(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 공지사항 글 수정 시 DB에 UPDATE
	 * @Method Name : updateNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateNotice.do")
	public ModelAndView updateNotice(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateNotice");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = noticeService.updateNotice(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
}
