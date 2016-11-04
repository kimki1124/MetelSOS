package com.metelsos.newemplyd.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.newemplyd.vo.NewemplydVo;

@Repository("newemplydDao")
public class NewemplydDao extends AbstractDAO{

	public List<HashMap<String, Object>> getCurrNewEmplydList(String year_month) throws Exception{
		return (List<HashMap<String, Object>>)selectList("newemplyd.getCurrNewEmplydList", year_month);
	}

	public List<HashMap<String, Object>> getNewEmplydList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectList("newemplyd.getNewEmplydList", paramMap);
	}

	public int deleteNewEmplyd(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("newemplyd.deleteNewEmplyd", paramMap);
	}

	public int enrollNewEmplyd(HashMap<String, Object> paramMap) throws Exception{
		return (int)insert("newemplyd.enrollNewEmplyd", paramMap);
	}

	public HashMap<String, Object> getNewEmplydImage(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>) selectOne("newemplyd.getNewEmplydImage", paramMap);
	}

}
