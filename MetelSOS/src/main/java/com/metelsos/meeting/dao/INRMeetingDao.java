package com.metelsos.meeting.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.meeting.vo.INRMeetingVo;

@Repository("inrmeetingDao")
public class INRMeetingDao extends AbstractDAO{

	public List<INRMeetingVo> getMeetingHistory(HashMap<String, String> paramMap) throws Exception{
		return (List<INRMeetingVo>)selectList("inrmeeting.getMeetingHistory", paramMap);
	}

}
