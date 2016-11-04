package com.metelsos.support.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
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

}
