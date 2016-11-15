package com.metelsos.notice.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.notice.vo.FileVo;
import com.metelsos.notice.vo.NoticeVo;
/**
 * 
* <pre>
* com.metelsos.notice.dao
*   |_ NoticeDao.java
* </pre>
* 
* Desc : 공지사항 관련 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 10:30:52
* @Version :
 */
@Repository("noticeDao")
public class NoticeDao extends AbstractDAO{

	/**
	 * 
	 * Desc : 공지사항 리스트 가져옴
	 * @Method Name : getNoticeList
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVo> getNoticeList() throws Exception{
		return (List<NoticeVo>) selectList("notice.getNoticeList");
	}

	/**
	 * 
	 * Desc : 새로운 공지 글 올렸을 때 DB에 INSERT
	 * @Method Name : insertNoticeBoard
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int insertNoticeBoard(HashMap<String, String> paramMap) throws Exception{
		return (int) insert("notice.insertNoticeBoard", paramMap);
	}

	/**
	 * 
	 * Desc : 공지 글 게시할 때 첨부파일 관련 정보 INSERT
	 * @Method Name : uploadNoticeFile
	 * @param paramMap
	 * @throws Exception
	 */
	public void uploadNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		insert("notice.uploadNoticeFile", paramMap);
	}

	/**
	 * 
	 * Desc : 해당 공지 글에 첨부된 파일리스트 SELECT
	 * @Method Name : getNoticeFileList
	 * @param boardNum
	 * @return
	 * @throws Exception
	 */
	public List<FileVo> getNoticeFileList(int boardNum) throws Exception{
		return (List<FileVo>)selectList("notice.getNoticeFileList", boardNum);
	}

	/**
	 * 
	 * Desc : 공지 글 하나의 상세정보 SELECT
	 * @Method Name : selectDetailNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public NoticeVo selectDetailNotice(HashMap<String, String> paramMap) throws Exception{
		return (NoticeVo)selectOne("notice.getDetailNotice", paramMap);
	}

	/**
	 * 
	 * Desc : getNoticeFileList 함수와 같은 기능(둘 중 하나 제거 예정) 
	 * @Method Name : selectFileList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<FileVo> selectFileList(HashMap<String, String> paramMap) throws Exception{
		paramMap.put("boardNum", paramMap.get("notice_num"));
		return (List<FileVo>)selectList("notice.getNoticeFileList", paramMap);
	}

	/**
	 * 
	 * Desc : FILE_NUM으로 파일 하나의 정보를 SELECT
	 * @Method Name : selectNoticeFileInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public FileVo selectNoticeFileInfo(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("file_num")));
		return (FileVo)selectOne("notice.getNoticeFileInfo", file_num);
	}

	/**
	 * 
	 * Desc : 첨부파일 삭제 시 DB에 있는 해당 파일의 정보 DELETE
	 * @Method Name : deleteNoticeFile
	 * @param paramMap
	 * @throws Exception
	 */
	public void deleteNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		delete("notice.deleteNoticeFile", file_num);
	}

	/**
	 * 
	 * Desc : 공지 글 삭제 시 해당 글 관련 정보 DELETE
	 * @Method Name : deleteNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteNotice(HashMap<String, Object> paramMap) throws Exception{
		int boardNum = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		return (int)delete("notice.deleteNotice", boardNum);
	}

	/**
	 * 
	 * Desc : 해당 공지 글에 첨부 파일이 있는 지 확인
	 * @Method Name : selectHasNoticeFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public FileVo selectHasNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		int boardNum = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		return (FileVo)selectOne("notice.selectHasNoticeFile", boardNum);
	}

	/**
	 * 
	 * Desc : FILE_NUM으로 첨부 파일 하나에 관련된 정보 DELETE
	 * @Method Name : deleteNoticeFileByFileNum
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteNoticeFileByFileNum(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("file_num")));
		return (int)delete("notice.deleteNoticeFileByFileNum", file_num);
	}

	/**
	 * 
	 * Desc : 공지 글 수정 시 해당 글에 관련된 정보 UPDATE
	 * @Method Name : updateNotice
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateNotice(HashMap<String, String> paramMap) throws Exception{
		update("notice.updateNotice", paramMap);
	}

	/**
	 * 
	 * Desc : 공지 글 조회 시 해당 글 조회 수 증가
	 * @Method Name : updateNoticeHit
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateNoticeHit(HashMap<String, String> paramMap) throws Exception{
		update("notice.updateNoticeHit", paramMap);
	}

	/**
	 * 
	 * Desc : 페이징 처리를 위해서 공지사항을 START부터 END까지의 ROWNUM의 데이터만 가져옴
	 * @Method Name : selectNoticeList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> selectNoticeList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("notice.selectNoticeList", paramMap);
	}

}
