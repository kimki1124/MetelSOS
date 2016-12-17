package com.metelsos.notice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.notice.dao.NoticeDao;
import com.metelsos.notice.vo.FileVo;
import com.metelsos.notice.vo.NoticeVo;
/**
 * 
* <pre>
* com.metelsos.notice.service
*   |_ NoticeServiceImpl.java
* </pre>
* 
* Desc : 공지사항 관련 서비스 구현 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 10:38:21
* @Version :
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	private Log log = LogFactory.getLog(NoticeServiceImpl.class);
	
	@Resource(name="noticeDao")
	private NoticeDao noticeDao;

	/**
	 * 
	 * Desc : 공지 글 게시
	 * @Method Name : insertNoticeBoard
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> insertNoticeBoard(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		MetelSOSUtil util = new MetelSOSUtil();
		paramMap.put("currDate", util.currDatetoString("yyyyMMddHHmmss"));
		
		String content = paramMap.get("noticeContent");
		content = content.replace("\n", "<br />").replace("\r", "<br />");
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

	/**
	 * 
	 * Desc : 공지 글 게시할 때 첨부파일이 있으면 첨부파일 업로드 
	 * @Method Name : uploadNoticeFile
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * Desc : 공지 글 상세보기
	 * @Method Name : selectDetailNotice
	 * @param returnMap
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void selectDetailNotice(HashMap<String, Object> returnMap, HashMap<String, String> paramMap) throws Exception {
		MetelSOSUtil util = new MetelSOSUtil();
		NoticeVo vo = noticeDao.selectDetailNotice(paramMap);
		
		//content = content.replace("\n", "<br />").replace("\r", "<br />");
		vo.setNotice_content(vo.getNotice_content().replace("<br /><br />", "<br />"));
		
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

	/**
	 * 
	 * Desc : 첨부파일 다운로드를 위해 클릭한 파일 정보 가져옴
	 * @Method Name : selectNoticeFileInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> selectNoticeFileInfo(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		FileVo vo = noticeDao.selectNoticeFileInfo(paramMap);
		
		returnMap.put("fileVo", vo);
		return returnMap;
	}

	/**
	 * 
	 * Desc : 공지 글 삭제
	 * @Method Name : deleteNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * Desc : 공지 글 수정 시 첨부파일 삭제
	 * @Method Name : deleteNoticeFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * Desc : 공지 글 수정
	 * @Method Name : updateNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateNotice(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		noticeDao.updateNotice(paramMap);
		returnMap.put("resultMsg", "SUCCESS");
		return returnMap;
	}

	/**
	 * 
	 * Desc : 공지 글 리스트 가져옴
	 * @Method Name : selectNoticeList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
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
