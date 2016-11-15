package com.metelsos.support.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.support.vo.FileVo;
import com.metelsos.support.vo.SupportVo;
/**
 * 
* <pre>
* com.metelsos.support.dao
*   |_ SupportDao.java
* </pre>
* 
* Desc : 지원 요청 및 지원 관련 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오후 8:42:11
* @Version :
 */
@Repository("supportDao")
public class SupportDao extends AbstractDAO{

	/**
	 * 
	 * Desc : 유저타입에 따라서 엔지니어는 자기가 접수한 지원 요청 리스트, 고객은 본인이 요청한 리스트 가져옴
	 * @Method Name : getSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<SupportVo> getSupportHistory(HashMap<String, String> paramMap) throws Exception{
		if("engineer".equals(paramMap.get("userType"))){
			return (List<SupportVo>)selectList("support.getSupportListByEngineerName", paramMap);
		}else{
			return (List<SupportVo>)selectList("support.getSupportListByCustomerName", paramMap);
		}
	}

	/**
	 * 
	 * Desc : 지원 완료 상태의 지원 요청 갯수 SELECT
	 * @Method Name : selectSupportCompleteCount
	 * @param interval
	 * @return
	 * @throws Exception
	 */
	public int selectSupportCompleteCount(int interval) throws Exception{
		return (int)selectOne("support.getSupportCompleteCount", interval);
	}

	/**
	 * 
	 * Desc : 지원 완료되지 않은 지원 요청 갯수 SELECT
	 * @Method Name : selectSupportNotCompleteCount
	 * @param interval
	 * @return
	 */
	public int selectSupportNotCompleteCount(int interval) {
		return (int)selectOne("support.getSupportNotCompleteCount", interval);
	}

	/**
	 * 
	 * Desc : 고객 회원 아이디를 이용해서 본인이 요청한 지원 요청 리스트 가져옴
	 * @Method Name : selectSupportListByCustomerId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SupportVo> selectSupportListByCustomerId(String userId) throws Exception{
		return (List<SupportVo>)selectList("support.selectSupportListByCustomerId", userId);
	}

	/**
	 * 
	 * Desc : 고객 회원 아이디를 이용해서 본인이 요청한 지원 완료 상태의 지원 요청 리스트 가져옴
	 * @Method Name : selectCompleteSupportListByCustomerId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SupportVo> selectCompleteSupportListByCustomerId(String userId) throws Exception{
		return (List<SupportVo>)selectList("support.selectCompleteSupportListByCustomerId", userId);
	}

	/**
	 * 
	 * Desc : 지원 요청 글 등록했을 때 정보 테이블에 INSERT
	 * @Method Name : insertSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int insertSupportRequest(HashMap<String, String> paramMap) throws Exception{
		return (int)insert("support.insertSupportRequest", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 요청 글에 첨부파일 올릴 때 첨부파일 정보 INSERT
	 * @Method Name : uploadSupportFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int uploadSupportFile(HashMap<String, Object> paramMap) throws Exception{
		return (int)insert("support.uploadSupportFile", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 요청 글 상세보기
	 * @Method Name : selectDetailSupport
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public SupportVo selectDetailSupport(HashMap<String, String> paramMap) throws Exception{
		return (SupportVo)selectOne("support.selectDetailSupport", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 요청 글 상세보기 할때 해당 글의 첨부파일 리스트 SELECT
	 * @Method Name : selectFileList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<FileVo> selectFileList(HashMap<String, String> paramMap) throws Exception{
		return (List<FileVo>)selectList("support.selectFileList", paramMap);
	}

	/**
	 * 
	 * Desc : FILE_NUM으로 첨부파일의 정보 가져옴
	 * @Method Name : selectSupportFileInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public FileVo selectSupportFileInfo(HashMap<String, Object> paramMap) throws Exception{
		return (FileVo)selectOne("support.selectSupportFileInfo", paramMap);
	}

	/**
	 * 
	 * Desc : 첨부파일의 정보 DELETE
	 * @Method Name : deleteSupportFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteSupportFile(HashMap<String, Object> paramMap) throws Exception{
		return (int)delete("support.deleteSupportFile", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 요청 글 내용 수정 시 UPDATE
	 * @Method Name : updateSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportRequest(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportRequest", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 요청 글 삭제 시 테이블에서 DELETE
	 * @Method Name : deleteSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteSupportHistory(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("support.deleteSupportHistory", paramMap);
	}

	/**
	 * 
	 * Desc : 접수 대기 상태인 지원 요청의 리스트 가져옴
	 * @Method Name : getUnSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getUnSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getUnSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : 접수 대기 상태의 지원 요청을 엔지니어가 접수 후 접수 완료로 변경
	 * @Method Name : updateSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportHistory(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportHistory", paramMap);
	}

	/**
	 * 
	 * Desc : 접수 완료된 지원 요청 리스트 SELECT
	 * @Method Name : getAcceptSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getAcceptSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getAcceptSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 중 상태인 지원 요청 리스트 SELECT
	 * @Method Name : getSupportingList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSupportingList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getSupportingList", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 완료 상태의 지원 요청 리스트 SELECT
	 * @Method Name : getCompleteSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCompleteSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getCompleteSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 상태 별 해당 상태의 지원 요청한 고객사의 리스트 가져옴 (ex. 지원 중 상태의 고객사 리스트)
	 * @Method Name : getReqCompanyList
	 * @param support_state
	 * @return
	 */
	public List<String> getReqCompanyList(String support_state) {
		return (List<String>)selectList("support.getReqCompanyList", support_state);
	}

	/**
	 * 
	 * Desc : 접수 대기 상태의 지원 요청 리스트 SELECT
	 * @Method Name : getWaitSupportList
	 * @param paramMap
	 * @return
	 */
	public List<HashMap<String, Object>> getWaitSupportList(HashMap<String, String> paramMap) {
		return (List<HashMap<String, Object>>)selectList("support.getWaitSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : 접수 완료 상태의 지원 요청 리스트 SELECT
	 * @Method Name : selectAcceptSupport
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAcceptSupport(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>)selectOne("support.selectAcceptSupport", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 상태 UPDATE
	 * @Method Name : updateSupportState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportState(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportState", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 중인 지원 요청 글의 데이터, 엔지니어 직급, 담당자 직급, 담당자 ID 가져옴
	 * @Method Name : selectSupportingReq
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSupportingReq(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>)selectOne("support.selectEngAcceptSupport", paramMap);
	}

	/**
	 * 
	 * Desc : 지원 중 상태인 지원 요청의 지원 상태 UPDATE
	 * @Method Name : updateSupportingState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportingState(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportingState", paramMap);
	}

	/**
	 * 
	 * Desc : SUPPORT_NUM으로 해당 지원 요청 정보 SELECT
	 * @Method Name : getCompleteSupDetail
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public SupportVo getCompleteSupDetail(HashMap<String, String> paramMap) throws Exception{
		return (SupportVo)selectOne("support.getCompleteSupDetail", paramMap);
	}

}
