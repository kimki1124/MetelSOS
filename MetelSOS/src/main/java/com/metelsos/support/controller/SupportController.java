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
/**
 * 
* <pre>
* com.metelsos.support.controller
*   |_ SupportController.java
* </pre>
* 
* Desc : 고객 지원 요청 및 지원 관련 컨트롤러 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 11:21:19
* @Version :
 */
@Controller
public class SupportController {
	private Log log = LogFactory.getLog(SupportController.class);
	
	@Resource(name="supportService")
	private SupportService supportService;
	
	@Resource(name="menuService")
	private MenuService menuService;
	/**
	 * 
	 * Desc : 접수대기 상태 지원요청 리스트 가져옴
	 * @Method Name : getWaitSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWaitSupportList.do")
	public ModelAndView getWaitSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getWaitSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "접수대기");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 접수완료 상태의 지원요청 리스트 가져옴
	 * @Method Name : getCusAcceptSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCusAcceptSupportList.do")
	public ModelAndView getCusAcceptSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusAcceptSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "접수완료");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원 중 상태의 지원요청 리스트 가져오기
	 * @Method Name : getCusSupportingList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCusSupportingList.do")
	public ModelAndView getCusSupportingList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusSupportingList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 중");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원 완료 상태의 지원요청 리스트 가져오기
	 * @Method Name : getCusCompleteSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCusCompleteSupportList.do")
	public ModelAndView getCusCompleteSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusCompleteSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 완료");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 고객으로부터 들어온 지원 요청 정보 INSERT
	 * @Method Name : insertSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertSupportRequest.do")
	public ModelAndView insertSupportRequest(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => insertSupportRequest");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.insertSupportRequest(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원요청 시 첨부파일 업로드
	 * @Method Name : uploadSupportFile
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadSupportFile.do")
	public ModelAndView uploadSupportFile(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => uploadNoticeFile");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.uploadSupportFile(paramMap, request);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : (고객)지원 요청 상세페이지로 이동
	 * @Method Name : moveSupportDetailPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 지원요청 글에 첨부된 파일 다운로드
	 * @Method Name : downloadSupportFile
	 * @param paramMap
	 * @param response
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 지원 요청 수정 페이지에 필요한 데이터 SET해서 뷰로 넘김
	 * @Method Name : moveSupportUpdatePage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 지원요청 글에 첨부한 파일 삭제
	 * @Method Name : deleteSupportFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSupportFile.do")
	public ModelAndView deleteSupportFile(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => deleteSupportFile");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.deleteSupportFile(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원요청 글 수정
	 * @Method Name : updateSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateSupportRequest.do")
	public ModelAndView updateSupportRequest(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportRequest");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.updateSupportRequest(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원 요청 글 삭제
	 * @Method Name : deleteSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSupportHistory.do")
	public ModelAndView deleteSupportHistory(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => deleteSupportHistory");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.deleteSupportHistory(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 접수 대기 상태의 지원 요청 글 리스트 가져오기
	 * @Method Name : getUnSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUnSupportList.do")
	public ModelAndView getUnSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getUnSupportList");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.getUnSupportList(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 접수 대기 상태의 글 상세보기 페이지에 필요한 아이템 SET해서 뷰로 넘김 
	 * @Method Name : moveUnsupportDetailPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 접수 대기 상태의 지원 요청을 접수
	 * @Method Name : acceptSupport
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/acceptSupport.do")
	public ModelAndView acceptSupport(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => acceptSupport");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.acceptSupport(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 접수완료 상태의 지원 요청 리스트 가져옴 
	 * @Method Name : getAcceptSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAcceptSupportList.do")
	public ModelAndView getAcceptSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getAcceptSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "접수완료");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원 중 상태의 지원 요청 리스트 가져옴
	 * @Method Name : getSupportingList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSupportingList.do")
	public ModelAndView getSupportingList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getSupportingList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 중");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원 완료 상태의 지원 요청 리스트 가져옴
	 * @Method Name : getCompleteSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompleteSupportList.do")
	public ModelAndView getCompleteSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCompleteSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "지원 완료");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 엔지니어 회원이 접수완료된 지원 요청 글 상세보기 할 때 데이터 SET
	 * @Method Name : mvEngAcptSprtDtlPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 지원 상태 업데이트
	 * @Method Name : updateSupportState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateSupportState.do")
	public ModelAndView updateSupportState(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportState");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.updateSupportState(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원 중인 지원 요청 글 상세보기
	 * @Method Name : mvEngSprtngDtlPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * 
	 * Desc : 지원 중 상태의 지원 요청 글 업데이트
	 * @Method Name : updateSupportingState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateSupportingState.do")
	public ModelAndView updateSupportingState(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportingState");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.updateSupportingState(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : 지원 완료 상태의 지원 요청 글 상세보기
	 * @Method Name : getCompleteSupDetail
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompleteSupDetail.do")
	public ModelAndView getCompleteSupDetail(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCompleteSupDetail");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.getCompleteSupDetail(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
}
