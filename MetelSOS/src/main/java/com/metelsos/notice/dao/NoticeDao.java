package com.metelsos.notice.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.notice.vo.FileVo;
import com.metelsos.notice.vo.NoticeVo;

@Repository("noticeDao")
public class NoticeDao extends AbstractDAO{

	public List<NoticeVo> getNoticeList() throws Exception{
		return (List<NoticeVo>) selectList("notice.getNoticeList");
	}

	public int insertNoticeBoard(HashMap<String, String> paramMap) throws Exception{
		return (int) insert("notice.insertNoticeBoard", paramMap);
	}

	public void uploadNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		insert("notice.uploadNoticeFile", paramMap);
	}

	public List<FileVo> getNoticeFileList(int boardNum) throws Exception{
		return (List<FileVo>)selectList("notice.getNoticeFileList", boardNum);
	}

	public NoticeVo selectDetailNotice(HashMap<String, String> paramMap) throws Exception{
		return (NoticeVo)selectOne("notice.getDetailNotice", paramMap);
	}

	public List<FileVo> selectFileList(HashMap<String, String> paramMap) throws Exception{
		paramMap.put("boardNum", paramMap.get("notice_num"));
		return (List<FileVo>)selectList("notice.getNoticeFileList", paramMap);
	}

	public FileVo selectNoticeFileInfo(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("file_num")));
		return (FileVo)selectOne("notice.getNoticeFileInfo", file_num);
	}

	public void deleteNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		delete("notice.deleteNoticeFile", file_num);
	}

	public int deleteNotice(HashMap<String, Object> paramMap) throws Exception{
		int boardNum = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		return (int)delete("notice.deleteNotice", boardNum);
	}

	public FileVo selectHasNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		int boardNum = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		return (FileVo)selectOne("notice.selectHasNoticeFile", boardNum);
	}

}
