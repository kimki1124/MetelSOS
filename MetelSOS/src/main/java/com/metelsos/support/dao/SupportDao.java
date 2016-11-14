package com.metelsos.support.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.support.vo.FileVo;
import com.metelsos.support.vo.SupportVo;

@Repository("supportDao")
public class SupportDao extends AbstractDAO{

	public List<SupportVo> getSupportHistory(HashMap<String, String> paramMap) throws Exception{
		if("engineer".equals(paramMap.get("userType"))){
			return (List<SupportVo>)selectList("support.getSupportListByEngineerName", paramMap);
		}else{
			return (List<SupportVo>)selectList("support.getSupportListByCustomerName", paramMap);
		}
	}

	public int selectSupportCompleteCount(int interval) throws Exception{
		return (int)selectOne("support.getSupportCompleteCount", interval);
	}

	public int selectSupportNotCompleteCount(int interval) {
		return (int)selectOne("support.getSupportNotCompleteCount", interval);
	}

	public List<SupportVo> selectSupportListByCustomerId(String userId) throws Exception{
		return (List<SupportVo>)selectList("support.selectSupportListByCustomerId", userId);
	}

	public List<SupportVo> selectCompleteSupportListByCustomerId(String userId) throws Exception{
		return (List<SupportVo>)selectList("support.selectCompleteSupportListByCustomerId", userId);
	}

	public int insertSupportRequest(HashMap<String, String> paramMap) throws Exception{
		return (int)insert("support.insertSupportRequest", paramMap);
	}

	public int uploadSupportFile(HashMap<String, Object> paramMap) throws Exception{
		return (int)insert("support.uploadSupportFile", paramMap);
	}

	public SupportVo selectDetailSupport(HashMap<String, String> paramMap) throws Exception{
		return (SupportVo)selectOne("support.selectDetailSupport", paramMap);
	}

	public List<FileVo> selectFileList(HashMap<String, String> paramMap) throws Exception{
		return (List<FileVo>)selectList("support.selectFileList", paramMap);
	}

	public FileVo selectSupportFileInfo(HashMap<String, Object> paramMap) throws Exception{
		return (FileVo)selectOne("support.selectSupportFileInfo", paramMap);
	}

	public int deleteSupportFile(HashMap<String, Object> paramMap) throws Exception{
		return (int)delete("support.deleteSupportFile", paramMap);
	}

	public int updateSupportRequest(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportRequest", paramMap);
	}

	public int deleteSupportHistory(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("support.deleteSupportHistory", paramMap);
	}

	public List<HashMap<String, Object>> getUnSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getUnSupportList", paramMap);
	}

	public int updateSupportHistory(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportHistory", paramMap);
	}

	public List<HashMap<String, Object>> getAcceptSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getAcceptSupportList", paramMap);
	}

	public List<HashMap<String, Object>> getSupportingList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getSupportingList", paramMap);
	}

	public List<HashMap<String, Object>> getCompleteSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getCompleteSupportList", paramMap);
	}

	public List<String> getReqCompanyList(String support_state) {
		return (List<String>)selectList("support.getReqCompanyList", support_state);
	}

	public List<HashMap<String, Object>> getWaitSupportList(HashMap<String, String> paramMap) {
		return (List<HashMap<String, Object>>)selectList("support.getWaitSupportList", paramMap);
	}

	public HashMap<String, Object> selectAcceptSupport(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>)selectOne("support.selectAcceptSupport", paramMap);
	}

	public int updateSupportState(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportState", paramMap);
	}

	public HashMap<String, Object> selectSupportingReq(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>)selectOne("support.selectEngAcceptSupport", paramMap);
	}

	public int updateSupportingState(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportingState", paramMap);
	}

	public SupportVo getCompleteSupDetail(HashMap<String, String> paramMap) throws Exception{
		return (SupportVo)selectOne("support.getCompleteSupDetail", paramMap);
	}

}
