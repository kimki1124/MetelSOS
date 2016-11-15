package com.metelsos.meeting.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.meeting.vo.CSTMRMeetingVo;
/**
 * 
* <pre>
* com.metelsos.meeting.dao
*   |_ CSTMRMeetingDao.java
* </pre>
* 
* Desc : 고객사 회의록 관련 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 9:16:25
* @Version :
 */
@Repository("cstmrmeetingDao")
public class CSTMRMeetingDao extends AbstractDAO{

	public List<CSTMRMeetingVo> getMeetingHistory(HashMap<String, String> paramMap) throws Exception{
		return (List<CSTMRMeetingVo>)selectList("cstmrmeeting.getMeetingHistory", paramMap);
	}

}
