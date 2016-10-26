package com.metelsos.support.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.support.vo.SupportVo;

@Repository("supportDao")
public class SupportDao extends AbstractDAO{

	public List<SupportVo> getSupportHistory(HashMap<String, String> paramMap) throws Exception{
		return (List<SupportVo>)selectList("support.getSupportListByEngineerName", paramMap);
	}

}
