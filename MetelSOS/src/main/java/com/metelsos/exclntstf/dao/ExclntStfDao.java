package com.metelsos.exclntstf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.exclntstf.vo.ExclntStfVo;
/**
 * 
* <pre>
* com.metelsos.exclntstf.dao
*   |_ ExclntStfDao.java
* </pre>
* 
* Desc : 우수사원 관련 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 6:27:32
* @Version :
 */
@Repository("exclntstfDao")
public class ExclntStfDao extends AbstractDAO{

	public List<ExclntStfVo> getExclntStfList(HashMap<String, String> paramMap) throws Exception{
		return (List<ExclntStfVo>)selectList("exclntstf.getExclntStfList", paramMap);
	}

	public int deleteExclntStf(HashMap<String, String> paramMap) throws Exception{
		return (int) delete("exclntstf.deleteExclntStf", paramMap);
	}

	public int insertExclntStf(HashMap<String, Object> paramMap) throws Exception{
		return (int) insert("exclntstf.insertExclntStf", paramMap);
	}

	public List<HashMap<String, Object>> selectCurrExclntStf(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectList("exclntstf.selectCurrExclntStf", paramMap);
	}

	public HashMap<String, Object> getExclntStfImage(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>) selectOne("exclntstf.getExclntStfImage", paramMap);
	}

}
