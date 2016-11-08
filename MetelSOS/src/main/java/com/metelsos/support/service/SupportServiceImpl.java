package com.metelsos.support.service;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.support.dao.SupportDao;

@Service("supportService")
public class SupportServiceImpl implements SupportService{

	@Resource(name="supportDao")
	private SupportDao supportDao;
	
	@Override
	public HashMap<String, Object> getAllSupportList(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("undefined".equals(paramMap.get("searchValue")) || "".equals(paramMap.get("searchValue"))){
			paramMap.remove("searchValue");
		}else{
			paramMap.put("searchValue", URLDecoder.decode(paramMap.get("searchValue"), "UTF-8"));
			returnMap.put("searchValue", paramMap.get("searchValue"));
		}
		
		List<HashMap<String, Object>> list = supportDao.selectAllSupportListByCustomerId(paramMap);
		
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> map = list.get(i);
			
			if(!map.containsKey("SUPPORT_ENGINEER")){
				map.put("SUPPORT_ENGINEER", "¹ÌÁ¤");
			}
			
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
			returnMap.put("allSupportList", list);
		}
		
		return returnMap;
	}

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
		content = content.replace(System.getProperty("line.separator"), "<br />");
		paramMap.put("support_request", content);
		
		Date sysdate = new Date();
		String currDate = toSTRINGTransFormat.format(sysdate);
		paramMap.put("support_accept_date", currDate);
		
		int result = supportDao.insertSupportRequest(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("supportNum", paramMap.get("supportNum"));
		}else{
			returnMap.put("resultMsg", "FAILED");
		}

		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> uploadSupportFile(HashMap<String, Object> paramMap, HttpServletRequest request)
			throws Exception {
		int uploadCount = 0;
		MetelSOSUtil util = new MetelSOSUtil();
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
         
        File file = new File("C:\\dev\\file\\support\\");
        if(file.exists() == false){
            file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;
                
                file = new File("C:\\dev\\file\\support\\" + storedFileName);
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

	private String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
