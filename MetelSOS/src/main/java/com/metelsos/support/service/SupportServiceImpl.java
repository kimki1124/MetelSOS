package com.metelsos.support.service;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.metelsos.common.sms.Coolsms;
import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.customer.dao.CustomerDao;
import com.metelsos.engineer.dao.EngineerDao;
import com.metelsos.support.dao.SupportDao;
import com.metelsos.support.vo.FileVo;
import com.metelsos.support.vo.SupportVo;

import net.sf.json.JSONException;
/**
 * 
* <pre>
* com.metelsos.support.service
*   |_ SupportServiceImpl.java
* </pre>
* 
* Desc : 지원 요청 및 지원 관련 서비스 구현 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오후 9:02:00
* @Version :
 */
@Service("supportService")
public class SupportServiceImpl implements SupportService{
	private Log log = LogFactory.getLog(SupportServiceImpl.class);

	@Resource(name="supportDao")
	private SupportDao supportDao;
	
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	
	@Resource(name="engineerDao")
	private EngineerDao engineerDao;

	/**
	 * 
	 * Desc : 지원 요청 글 등록 시 INSERT 서비스
	 * @Method Name : insertSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> insertSupportRequest(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String cusName = paramMap.get("customer_name");
		cusName = cusName.split(" ")[0];
		paramMap.put("customer_name", cusName);
		
		String cusPhone = paramMap.get("customer_phone");
		cusPhone = cusPhone.replace("-", "");
		paramMap.put("customer_phone", cusPhone);
		
		String hopeDate = paramMap.get("hope_support_date");
		SimpleDateFormat toDATETransFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date to = toDATETransFormat.parse(hopeDate);
		SimpleDateFormat toSTRINGTransFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String from = toSTRINGTransFormat.format(to);
		
		paramMap.put("hope_support_date", from);
		
		String content = paramMap.get("support_request");
		//content = content.replace(System.getProperty("line.separator"), "<br />");
		content = content.replace("\n", "<br />").replace("\r", "<br />");
		paramMap.put("support_request", content);
		
		Date sysdate = new Date();
		String currDate = toSTRINGTransFormat.format(sysdate);
		paramMap.put("support_accept_date", currDate);
		
		int result = supportDao.insertSupportRequest(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("supportNum", paramMap.get("supportNum"));
			//sendSMSAllEngineer();
		}else{
			returnMap.put("resultMsg", "FAILED");
		}

		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 요청 글 등록 시 엔지니어회원들에게 문자 전송 (문자 전송 기능만 구현, 구체적인 구현은 아직 안함)
	 * @Method Name : sendSMSAllEngineer
	 */
	private void sendSMSAllEngineer() {
		String api_key = "";
		String api_secret = "";
		Coolsms coolsms = new Coolsms(api_key, api_secret);
		
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", ""); // 수신번호

		// 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다.
		set.put("from", ""); // 발신번호
		set.put("text", "새로운 지원 요청이 들어왔습니다. 자세한 내용은 홈페이지에서 확인해주세요."); // 문자내용
		set.put("type", "sms"); // 문자 타입
		
		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		if ((Boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			log.info("성공");			
			log.info(result.get("group_id")); // 그룹아이디
			log.info(result.get("result_code")); // 결과코드
			log.info(result.get("result_message"));  // 결과메시지
			log.info(result.get("success_count")); // 성공갯수
			log.info(result.get("error_count"));  // 발송실패 메시지 수
		} else {
			// 메시지 보내기 실패
			log.info("실패");
			log.info(result.get("code")); // REST API 에러코드
			log.info(result.get("message")); // 에러메시지
		}
	}

	/**
	 * 
	 * Desc : 지원 요청 글 등록 시 첨부파일 업로드
	 * @Method Name : uploadSupportFile
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> uploadSupportFile(HashMap<String, Object> paramMap, HttpServletRequest request)
			throws Exception {
		int uploadCount = 0;
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = parseInsertFileInfo(paramMap, request);
		
		for(int i=0;i<list.size();i++){
			int result = supportDao.uploadSupportFile(list.get(i));
			uploadCount = uploadCount + result;
		}
		
		if(uploadCount == list.size()){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 요청 글의 첨부파일 로컬에 생성하고 파일 테이블에 넣을 데이터 포맷을 맞춤
	 * @Method Name : parseInsertFileInfo
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private List<HashMap<String, Object>> parseInsertFileInfo(HashMap<String, Object> paramMap,
			HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> listMap = null; 
         
        String supportNum = (String)paramMap.get("supportNum");
         
        File file = new File("/home/indigo/MYMETELSOS/file/support/");
        if(file.exists() == false){
            file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;
                
                file = new File("/home/indigo/MYMETELSOS/file/support/" + storedFileName);
                multipartFile.transferTo(file);
                
                listMap = new HashMap<String,Object>();
                listMap.put("BOARD_IDX", supportNum);
                listMap.put("ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                listMap.put("CREA_ID", paramMap.get("userId"));
                list.add(listMap);
        	}
        }
        
		return list;
	}

	/**
	 * 
	 * Desc : 32글자의 랜덤한 문자열(숫자포함)을 만들어서 반환
	 * @Method Name : getRandomString
	 * @return
	 */
	private String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 
	 * Desc : 지원 요청 글 상세보기 페이지에 필요한 데이터 SET
	 * @Method Name : setSupportDetailPageItems
	 * @param returnMap
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void setSupportDetailPageItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		SupportVo vo = supportDao.selectDetailSupport(paramMap);
		
		if(vo.getPurpose_of_visit() != null){
			vo.setPurpose_of_visit(vo.getPurpose_of_visit().replace("<br /><br />", "<br />"));
		}
		
		if(vo.getSupport_request() != null){
			vo.setSupport_request(vo.getSupport_request().replace("<br /><br />", "<br />"));
		}
		
		if(vo.getSupport_response() != null){
			vo.setSupport_response(vo.getSupport_response().replace("<br /><br />", "<br />"));
		}
		
		if(vo.getSupport_engineer_comment() != null){
			vo.setSupport_engineer_comment(vo.getSupport_engineer_comment().replace("<br /><br />", "<br />"));
		}
		
		
		if(vo != null){
			String userId = paramMap.get("userId");
			HashMap<String, Object> customerInfoMap = customerDao.getCustomerInfo(paramMap.get("userId"));
			
			String cusPhone = vo.getCustomer_phone();
			StringBuffer sb = new StringBuffer(cusPhone);
			if(cusPhone.length() == 11){
				sb.insert(3, "-");
				sb.insert(8, "-");
			}else{
				sb.insert(3, "-");
				sb.insert(7, "-");
			}
			vo.setCustomer_phone(sb.toString());
			
			vo.setHope_support_date(util.changeDatePattern(vo.getHope_support_date(), "yyyyMMddHHmmss", "yyyy/MM/dd"));
			
			List<FileVo> fileList = supportDao.selectFileList(paramMap);
			for(int i=0;i<fileList.size();i++){
				FileVo fileVo = fileList.get(i);
				fileVo.setCrea_dtm(util.changeDatePattern(fileVo.getCrea_dtm(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
			}
			
			if(fileList.size() > 0){
				returnMap.put("fileList", fileList);
			}
			
			List<String> list = new ArrayList<String>();
			list.add(paramMap.get("menuTitle"));
			
			returnMap.put("supportNum", vo.getSupport_num());
			returnMap.put("userId", userId);
			returnMap.put("breadcrumbList", list);
			returnMap.put("supportVo", vo);
			returnMap.put("menuTitle", paramMap.get("menuTitle"));
			returnMap.put("customerInfo", customerInfoMap);
		}
	}

	/**
	 * 
	 * Desc : 첨부파일 다운로드 시 해당 파일의 정보 가져옴
	 * @Method Name : selectSupportFileInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> selectSupportFileInfo(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		FileVo vo = supportDao.selectSupportFileInfo(paramMap);
		
		returnMap.put("fileVo", vo);
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 첨부파일 삭제
	 * @Method Name : deleteSupportFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> deleteSupportFile(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		int result = supportDao.deleteSupportFile(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 요청 글 수정 시 해당 글 정보 UPDATE
	 * @Method Name : updateSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateSupportRequest(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String cusName = paramMap.get("customer_name");
		cusName = cusName.split(" ")[0];
		paramMap.put("customer_name", cusName);
		
		String cusPhone = paramMap.get("customer_phone");
		cusPhone = cusPhone.replace("-", "");
		paramMap.put("customer_phone", cusPhone);
		
		String hopeDate = paramMap.get("hope_support_date");
		SimpleDateFormat toDATETransFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date to = toDATETransFormat.parse(hopeDate);
		SimpleDateFormat toSTRINGTransFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String from = toSTRINGTransFormat.format(to);
		
		paramMap.put("hope_support_date", from);
		
		String content = paramMap.get("support_request");
		//content = content.replace(System.getProperty("line.separator"), "<br />");
		content = content.replace("\n", "<br />").replace("\r", "<br />");
		paramMap.put("support_request", content);
		
		int result = supportDao.updateSupportRequest(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 요청 글 삭제 시 해당 글 정보 DELETE
	 * @Method Name : deleteSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> deleteSupportHistory(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		int result = supportDao.deleteSupportHistory(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 미지원 상태의 지원 요청 리스트 가져오기
	 * @Method Name : getUnSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getUnSupportList(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("undefined".equals(paramMap.get("searchValue")) || "".equals(paramMap.get("searchValue"))){
			paramMap.remove("searchValue");
		}else{
			paramMap.put("searchValue", URLDecoder.decode(paramMap.get("searchValue"), "UTF-8"));
			returnMap.put("searchValue", paramMap.get("searchValue"));
		}
		
		List<HashMap<String, Object>> list = supportDao.getUnSupportList(paramMap);
		
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> map = list.get(i);
			
			map.put("SUPPORT_ACCEPT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_ACCEPT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			if(map.get("SUPPORT_DATE") != null){
				map.put("SUPPORT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			}
		}
		
		if(list.size() > 0){
			returnMap.put("TOTAL", list.get(0).get("TOTAL_COUNT"));
		}else{
			returnMap.put("TOTAL", 0);
		}
		
		if(list.size() > 0){
			returnMap.put("unSupportList", list);
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 접수 대기 상태의 지원 요청 글 상세보기 페이지에 필요한 데이터 SET
	 * @Method Name : setUnsupportDetailItems
	 * @param returnMap
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void setUnsupportDetailItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		//support_history vo 가져오기
		//해당 지원요청의 첨부파일 리스트 가져오기
		SupportVo vo = supportDao.selectDetailSupport(paramMap);
		//vo.setNotice_content(vo.getNotice_content().replace("<br /><br />", "<br />"));
		vo.setSupport_request(vo.getSupport_request().replace("<br /><br />", "<br />"));
		
		if(vo != null){
			HashMap<String, String> supportMap = new HashMap<String, String>();
			supportMap.put("customer_name", vo.getCustomer_name());
			supportMap.put("customer_phone", vo.getCustomer_phone());
			
			vo.setCustomer_position(customerDao.selectCustomerPosition(supportMap));
			
			String cusPhone = vo.getCustomer_phone();
			StringBuffer sb = new StringBuffer(cusPhone);
			if(cusPhone.length() == 11){
				sb.insert(3, "-");
				sb.insert(8, "-");
			}else{
				sb.insert(3, "-");
				sb.insert(7, "-");
			}
			vo.setCustomer_phone(sb.toString());
			
			vo.setHope_support_date(util.changeDatePattern(vo.getHope_support_date(), "yyyyMMddHHmmss", "yyyy/MM/dd"));
			
			List<FileVo> fileList = supportDao.selectFileList(paramMap);
			
			for(int i=0;i<fileList.size();i++){
				FileVo fileVo = fileList.get(i);
				fileVo.setCrea_dtm(util.changeDatePattern(fileVo.getCrea_dtm(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
			}
			
			if(fileList.size() > 0){
				returnMap.put("fileList", fileList);
			}
			returnMap.put("supportVo", vo);
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
	}

	/**
	 * 
	 * Desc : 접수 대기 상태의 지원 요청을 접수 완료로 업데이트
	 * @Method Name : acceptSupport
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> acceptSupport(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		int result = supportDao.updateSupportHistory(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 접수 완료 상태의 지원 요청 리스트 가져옴
	 * @Method Name : getAcceptSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getAcceptSupportList(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> list = supportDao.getAcceptSupportList(paramMap);
		
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> map = list.get(i);
			
			map.put("SUPPORT_ACCEPT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_ACCEPT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			if(map.get("SUPPORT_DATE") != null){
				map.put("SUPPORT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			}
		}
		
		if(list.size() > 0){
			returnMap.put("TOTAL", list.get(0).get("TOTAL_COUNT"));
		}else{
			returnMap.put("TOTAL", 0);
		}
		
		if(list.size() > 0){
			returnMap.put("jsonList", listmap_to_json_string(list));
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 중 상태의 지원 요청 리스트 가져옴
	 * @Method Name : getSupportingList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getSupportingList(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("undefined".equals(paramMap.get("searchValue")) || "".equals(paramMap.get("searchValue"))){
			paramMap.remove("searchValue");
		}else{
			paramMap.put("searchValue", URLDecoder.decode(paramMap.get("searchValue"), "UTF-8"));
			returnMap.put("searchValue", paramMap.get("searchValue"));
		}
		
		List<HashMap<String, Object>> list = supportDao.getSupportingList(paramMap);
		
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> map = list.get(i);
			
			map.put("SUPPORT_ACCEPT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_ACCEPT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			if(map.get("SUPPORT_DATE") != null){
				map.put("SUPPORT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			}
		}
		
		if(list.size() > 0){
			returnMap.put("TOTAL", list.get(0).get("TOTAL_COUNT"));
		}else{
			returnMap.put("TOTAL", 0);
		}
		
		if(list.size() > 0){
			returnMap.put("supportingList", list);
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 완료 상태의 지원 요청 리스트 가져옴
	 * @Method Name : getCompleteSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getCompleteSupportList(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("undefined".equals(paramMap.get("searchValue")) || "".equals(paramMap.get("searchValue"))){
			paramMap.remove("searchValue");
		}else{
			paramMap.put("searchValue", URLDecoder.decode(paramMap.get("searchValue"), "UTF-8"));
			returnMap.put("searchValue", paramMap.get("searchValue"));
		}
		
		List<HashMap<String, Object>> list = supportDao.getCompleteSupportList(paramMap);
		
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> map = list.get(i);
			
			map.put("SUPPORT_ACCEPT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_ACCEPT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			if(map.get("SUPPORT_DATE") != null){
				map.put("SUPPORT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			}
		}
		
		if(list.size() > 0){
			returnMap.put("TOTAL", list.get(0).get("TOTAL_COUNT"));
		}else{
			returnMap.put("TOTAL", 0);
		}
		
		if(list.size() > 0){
			returnMap.put("completeSupportList", list);
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 고객 회원 접속 시 본인이 요청한 지원 요청 리스트 가져옴 
	 * @Method Name : getCusSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getCusSupportList(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = supportDao.getWaitSupportList(paramMap);
		
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> map = list.get(i);
			
			map.put("SUPPORT_ACCEPT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_ACCEPT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			if(map.get("SUPPORT_DATE") != null){
				map.put("SUPPORT_DATE", util.changeDatePattern(String.valueOf(map.get("SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			}
		}
		
		if(list.size() > 0){
			returnMap.put("TOTAL", list.get(0).get("TOTAL_COUNT"));
		}else{
			returnMap.put("TOTAL", 0);
		}
		
		if(list.size() > 0){
			returnMap.put("jsonList", listmap_to_json_string(list));
			
		}
		
		return returnMap;
	}
	/**
	 * 
	 * Desc : Map List를 JSONArray로 변경 후 toString해서 리턴 (jqgrid 데이터 포맷)
	 * @Method Name : listmap_to_json_string
	 * @param list
	 * @return
	 */
	private String listmap_to_json_string(List<HashMap<String, Object>> list)
	{       
	    JSONArray json_arr=new JSONArray();
	    for (HashMap<String, Object> map : list) {
	        JSONObject json_obj=new JSONObject();
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            try {
	                json_obj.put(key,value);
	            } catch (JSONException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }                           
	        }
	        json_arr.add(json_obj);
	    }
	    return json_arr.toString();
	}

	/**
	 * 
	 * Desc : 엔지니어가 접수 완료 된 지원 요청 상세보기 페이지로 넘어갈 때 필요한 데이터 SET 
	 * @Method Name : setUpdateSupportingFormItems
	 * @param returnMap
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void setUpdateSupportingFormItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		returnMap.put("menuTitle", "지원상태 업데이트");
		HashMap<String, Object> supportMap = supportDao.selectAcceptSupport(paramMap);
		
		if(supportMap.get("PURPOSE_OF_VISIT") != null){
			supportMap.put("PURPOSE_OF_VISIT", String.valueOf(supportMap.get("PURPOSE_OF_VISIT")).replace("<br /><br />", "<br />"));
		}
		
		if(supportMap.get("SUPPORT_REQUEST") != null){
			supportMap.put("SUPPORT_REQUEST", String.valueOf(supportMap.get("SUPPORT_REQUEST")).replace("<br /><br />", "<br />"));
		}
		
		if(supportMap.get("SUPPORT_RESPONSE") != null){
			supportMap.put("SUPPORT_RESPONSE", String.valueOf(supportMap.get("SUPPORT_RESPONSE")).replace("<br /><br />", "<br />"));
		}
		
		if(supportMap.get("SUPPORT_ENGINEER_COMMENT") != null){
			supportMap.put("SUPPORT_ENGINEER_COMMENT", String.valueOf(supportMap.get("SUPPORT_ENGINEER_COMMENT")).replace("<br /><br />", "<br />"));
		}
		
		String cusPhone = String.valueOf(supportMap.get("CUSTOMER_PHONE"));
		StringBuffer sb = new StringBuffer(cusPhone);
		if(cusPhone.length() == 11){
			sb.insert(3, "-");
			sb.insert(8, "-");
		}else{
			sb.insert(3, "-");
			sb.insert(7, "-");
		}
		supportMap.put("CUSTOMER_PHONE", sb.toString());
		
		supportMap.put("HOPE_SUPPORT_DATE", util.changeDatePattern(String.valueOf(supportMap.get("HOPE_SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy/MM/dd"));
		
		paramMap.put("supportNum", String.valueOf(supportMap.get("SUPPORT_NUM")));
		List<FileVo> fileList = supportDao.selectFileList(paramMap);
		
		for(int i=0;i<fileList.size();i++){
			FileVo fileVo = fileList.get(i);
			fileVo.setCrea_dtm(util.changeDatePattern(fileVo.getCrea_dtm(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
		}
		
		if(fileList.size() > 0){
			returnMap.put("fileList", fileList);
		}
		
		returnMap.put("supportInfo", supportMap);
	}

	/**
	 * 
	 * Desc : 지원 요청의 지원 상태 업데이트
	 * @Method Name : updateSupportState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateSupportState(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("support_date", URLDecoder.decode(paramMap.get("support_date"), "UTF-8"));
		paramMap.put("support_way", URLDecoder.decode(paramMap.get("support_way"), "UTF-8"));
		
		if("방문".equals(paramMap.get("support_way"))){
			paramMap.put("purpose_of_visit", URLDecoder.decode(paramMap.get("purpose_of_visit"), "UTF-8"));
			String content = paramMap.get("purpose_of_visit");
			//content = content.replace(System.getProperty("line.separator"), "<br />");
			content = content.replace("\n", "<br />").replace("\r", "<br />");
			paramMap.put("purpose_of_visit", content);
		}else{
			paramMap.remove("purpose_of_visit");
		}
		
		String supportDate = paramMap.get("support_date");
		SimpleDateFormat toDATETransFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date to = toDATETransFormat.parse(supportDate);
		SimpleDateFormat toSTRINGTransFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String from = toSTRINGTransFormat.format(to);
		
		paramMap.put("support_date", from);
		
		int result = supportDao.updateSupportState(paramMap);
		
		if(result > 0){
			
			returnMap.put("userId", paramMap.get("userId"));
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 중 상태의 지원 요청 글 상세보기 페이지로 넘어갈 때 필요한 데이터 SET
	 * @Method Name : setUpdateCompleteSupportFormItems
	 * @param returnMap
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void setUpdateCompleteSupportFormItems(HashMap<String, Object> returnMap, HashMap<String, String> paramMap)
			throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		returnMap.put("menuTitle", "지원상태 업데이트");
		
		HashMap<String, Object> supportMap = supportDao.selectSupportingReq(paramMap);
		
		if(supportMap.get("PURPOSE_OF_VISIT") != null){
			supportMap.put("PURPOSE_OF_VISIT", String.valueOf(supportMap.get("PURPOSE_OF_VISIT")).replace("<br /><br />", "<br />"));
		}
		
		if(supportMap.get("SUPPORT_REQUEST") != null){
			supportMap.put("SUPPORT_REQUEST", String.valueOf(supportMap.get("SUPPORT_REQUEST")).replace("<br /><br />", "<br />"));
		}
		
		if(supportMap.get("SUPPORT_RESPONSE") != null){
			supportMap.put("SUPPORT_RESPONSE", String.valueOf(supportMap.get("SUPPORT_RESPONSE")).replace("<br /><br />", "<br />"));
		}
		
		if(supportMap.get("SUPPORT_ENGINEER_COMMENT") != null){
			supportMap.put("SUPPORT_ENGINEER_COMMENT", String.valueOf(supportMap.get("SUPPORT_ENGINEER_COMMENT")).replace("<br /><br />", "<br />"));
		}
		
		String cusPhone = String.valueOf(supportMap.get("CUSTOMER_PHONE"));
		StringBuffer sb = new StringBuffer(cusPhone);
		if(cusPhone.length() == 11){
			sb.insert(3, "-");
			sb.insert(8, "-");
		}else{
			sb.insert(3, "-");
			sb.insert(7, "-");
		}
		supportMap.put("CUSTOMER_PHONE", sb.toString());
		
		supportMap.put("HOPE_SUPPORT_DATE", util.changeDatePattern(String.valueOf(supportMap.get("HOPE_SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy/MM/dd"));
		supportMap.put("SUPPORT_DATE", util.changeDatePattern(String.valueOf(supportMap.get("SUPPORT_DATE")), "yyyyMMddHHmmss", "yyyy/MM/dd"));
		
		paramMap.put("supportNum", String.valueOf(supportMap.get("SUPPORT_NUM")));
		List<FileVo> fileList = supportDao.selectFileList(paramMap);
		
		for(int i=0;i<fileList.size();i++){
			FileVo fileVo = fileList.get(i);
			fileVo.setCrea_dtm(util.changeDatePattern(fileVo.getCrea_dtm(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
		}
		
		if(fileList.size() > 0){
			returnMap.put("fileList", fileList);
		}
		
		returnMap.put("supportInfo", supportMap);
	}

	/**
	 * 
	 * Desc : 지원 중 상태의 지원 요청을 지원 완료로 상태 업데이트
	 * @Method Name : updateSupportingState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateSupportingState(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("support_response", URLDecoder.decode(paramMap.get("support_response"), "UTF-8"));
		paramMap.put("support_engineer_comment", URLDecoder.decode(paramMap.get("support_engineer_comment"), "UTF-8"));
		
		String support_response = paramMap.get("support_response");
		//support_response = support_response.replace(System.getProperty("line.separator"), "<br />");
		support_response = support_response.replace("\n", "<br />").replace("\r", "<br />");
		paramMap.put("support_response", support_response);
		
		String support_engineer_comment = paramMap.get("support_engineer_comment");
		//support_engineer_comment = support_engineer_comment.replace(System.getProperty("line.separator"), "<br />");
		support_engineer_comment = support_engineer_comment.replace("\n", "<br />").replace("\r", "<br />");
		paramMap.put("support_engineer_comment", support_engineer_comment);
		
		int result = supportDao.updateSupportingState(paramMap);
		
		if(result > 0){
			returnMap.put("userId", paramMap.get("userId"));
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 지원 완료 상태의 지원 요청 글 상세보기 페이지로 넘어갈 때 필요한 데이터 SET
	 * @Method Name : getCompleteSupDetail
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getCompleteSupDetail(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		SupportVo vo = supportDao.getCompleteSupDetail(paramMap);
		
		if(vo.getPurpose_of_visit() != null){
			vo.setPurpose_of_visit(vo.getPurpose_of_visit().replace("<br /><br />", "<br />"));
		}
		
		if(vo.getSupport_request() != null){
			vo.setSupport_request(vo.getSupport_request().replace("<br /><br />", "<br />"));
		}
		
		if(vo.getSupport_response() != null){
			vo.setSupport_response(vo.getSupport_response().replace("<br /><br />", "<br />"));
		}
		
		if(vo.getSupport_engineer_comment() != null){
			vo.setSupport_engineer_comment(vo.getSupport_engineer_comment().replace("<br /><br />", "<br />"));
		}
		
		if(vo != null){
			HashMap<String, String> cusMap = new HashMap<String, String>();
			cusMap.put("customer_name", vo.getCustomer_name());
			cusMap.put("customer_phone", vo.getCustomer_phone());
			vo.setCustomer_position(customerDao.selectCustomerPosition(cusMap));
			
			HashMap<String, String> engMap = new HashMap<String, String>();
			engMap.put("engineer_name", vo.getSupport_engineer());
			engMap.put("engineer_phone", vo.getSupport_engineer_phone());
			vo.setEngineer_position(engineerDao.selectEngineerPosition(engMap));
			
			StringBuffer sb = new StringBuffer(vo.getCustomer_phone());
			if(vo.getCustomer_phone().length() == 11){
				sb.insert(3, "-");
				sb.insert(8, "-");
			}else{
				sb.insert(3, "-");
				sb.insert(7, "-");
			}
			vo.setCustomer_phone(sb.toString());
			
			vo.setSupport_accept_date(util.changeDatePattern(vo.getSupport_accept_date(), "yyyyMMddHHmmss", "yyyy/MM/dd"));
			vo.setSupport_date(util.changeDatePattern(vo.getSupport_date(), "yyyyMMddHHmmss", "yyyy/MM/dd"));
			
			returnMap.put("supportInfo", vo);
		}
		
		return returnMap;
	}
	
}
