package com.metelsos.engineer.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.engineer.vo.EngineerVo;

@Repository("engineerDao")
public class EngineerDao extends AbstractDAO{

	public EngineerVo checkLogin(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerByIdAndPasswd", paramMap);
	}

	public EngineerVo validateEngineerId(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerById", paramMap);
	}

	public int insertUser(HashMap<String, String> paramMap) throws Exception{
		return (int)insert("engineer.insertEngineer", paramMap);
	}

}
