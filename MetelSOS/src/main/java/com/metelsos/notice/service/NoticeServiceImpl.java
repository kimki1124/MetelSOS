package com.metelsos.notice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.notice.dao.NoticeDao;
import com.metelsos.notice.vo.FileVo;
import com.metelsos.notice.vo.NoticeVo;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Resource(name="noticeDao")
	private NoticeDao noticeDao;

	@Override
	public HashMap<String, Object> insertNoticeBoard(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		MetelSOSUtil util = new MetelSOSUtil();
		paramMap.put("currDate", util.currDatetoString("yyyyMMddHHmmss"));
		
		String content = paramMap.get("noticeContent");
		content = content.replace(System.getProperty("line.separator"), "<br />");
		paramMap.put("noticeContent", content);
		
		int result = noticeDao.insertNoticeBoard(paramMap);
		
		if(result > 0){
			returnMap.put("boardNum", paramMap.get("noticeNum"));
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> uploadNoticeFile(HashMap<String, Object> paramMap, HttpServletRequest request)
			throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = util.parseInsertFileInfo(paramMap, request);
		for(int i=0;i<list.size();i++){
			noticeDao.uploadNoticeFile(list.get(i));
			returnMap.put("file_num", list.get(i).get("noticeFileNum"));
		}
		
		return returnMap;
	}

	@Override
	public void selectDetailNotice(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		NoticeVo vo = noticeDao.selectDetailNotice(paramMap);
		
		if(vo != null){
			if(paramMap.containsKey("userId")){
				returnMap.put("userId", paramMap.get("userId"));
				noticeDao.updateNoticeHit(paramMap);
			}
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		List<FileVo> fileList = noticeDao.selectFileList(paramMap);
		
		for(int i=0;i<fileList.size();i++){
			FileVo fileVo = fileList.get(i);
			fileVo.setCrea_dtm(util.changeDatePattern(fileVo.getCrea_dtm(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
		}
		
		
		if(fileList.size() > 0){
			returnMap.put("fileList", fileList);
		}
		
		List<String> list = new ArrayList<String>();
		list.add("공지사항 관리");
		
		returnMap.put("breadcrumbList", list);
		returnMap.put("noticeVo", vo);
		returnMap.put("menuTitle", paramMap.get("menuTitle"));
	}

	@Override
	public HashMap<String, Object> selectNoticeFileInfo(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		FileVo vo = noticeDao.selectNoticeFileInfo(paramMap);
		
		returnMap.put("fileVo", vo);
		return returnMap;
	}

	@Override
	public HashMap<String, Object> deleteNotice(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		FileVo vo = noticeDao.selectHasNoticeFile(paramMap);
		if(vo != null){
			noticeDao.deleteNoticeFile(paramMap);
		}
		
		int result = noticeDao.deleteNotice(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> deleteNoticeFile(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		int result = noticeDao.deleteNoticeFileByFileNum(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> updateNotice(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		noticeDao.updateNotice(paramMap);
		returnMap.put("resultMsg", "SUCCESS");
		return returnMap;
	}

	@Override
	public HashMap<String, Object> selectNoticeList(HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> noticeList = noticeDao.selectNoticeList(paramMap);
		
		for(int i=0;i<noticeList.size();i++){
			HashMap<String, Object> noticeMap = noticeList.get(i);
			noticeMap.put("NOTICE_DATE", util.changeDatePattern(String.valueOf(noticeMap.get("NOTICE_DATE")), "yyyyMMddHHmmss", "yyyy-MM-dd"));
			
			int boardNum = Integer.parseInt(String.valueOf(noticeMap.get("NOTICE_NUM")));
			List<FileVo> fileList = noticeDao.getNoticeFileList(boardNum);
			
			if(fileList.size() > 0){
				noticeMap.put("HAS_FILE", "Y");
			}else{
				noticeMap.put("HAS_FILE", "N");
			}
		}
		
		if(noticeList.size() > 0){
			returnMap.put("TOTAL", noticeList.get(0).get("TOTAL_COUNT"));
		}else{
			returnMap.put("TOTAL", 0);
		}
		
		returnMap.put("noticeList", noticeList);
		return returnMap;
	}

}
