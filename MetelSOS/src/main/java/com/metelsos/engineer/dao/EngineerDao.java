package com.metelsos.engineer.dao;

import java.util.HashMap;
import java.util.List;

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

	public List<EngineerVo> findEngineerId(HashMap<String, String> paramMap) throws Exception{
		return (List<EngineerVo>)selectList("engineer.getEngineerByNameAndEmail", paramMap);
	}

	public EngineerVo findEngineer(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerByUserId", paramMap);
	}

	public void updateEngineerPasswd(EngineerVo vo) {
		update("engineer.updateEngineerPasswd", vo);
	}

	public void updateEngineerInfo(EngineerVo vo) throws Exception{
		update("engineer.updateEngineerInfo", vo);
	}

	public int deleteEngineerAccount(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("engineer.deleteEngineer", paramMap);
	}

}
