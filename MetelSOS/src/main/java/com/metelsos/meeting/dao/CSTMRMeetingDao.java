package com.metelsos.meeting.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.meeting.vo.CSTMRMeetingVo;

@Repository("cstmrmeetingDao")
public class CSTMRMeetingDao extends AbstractDAO{

	public List<CSTMRMeetingVo> getMeetingHistory(HashMap<String, String> paramMap) throws Exception{
		return (List<CSTMRMeetingVo>)selectList("cstmrmeeting.getMeetingHistory", paramMap);
	}

}
