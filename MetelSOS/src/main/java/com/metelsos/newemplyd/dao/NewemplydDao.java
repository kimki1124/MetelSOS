package com.metelsos.newemplyd.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.newemplyd.vo.NewemplydVo;
/**
 * 
* <pre>
* com.metelsos.newemplyd.dao
*   |_ NewemplydDao.java
* </pre>
* 
* Desc : 신입사원 관련 
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 9:47:14
* @Version :
 */
@Repository("newemplydDao")
public class NewemplydDao extends AbstractDAO{

	/**
	 * 
	 * Desc : CREATE_DATE가 현재 년과 월인 엔지니어의 리스트를 가져옴
	 * @Method Name : getCurrNewEmplydList
	 * @param year_month
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCurrNewEmplydList(String year_month) throws Exception{
		return (List<HashMap<String, Object>>)selectList("newemplyd.getCurrNewEmplydList", year_month);
	}

	/**
	 * 
	 * Desc : 위 메서드와 기능 같음(둘 중 하나 삭제 예정)
	 * @Method Name : getNewEmplydList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getNewEmplydList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectList("newemplyd.getNewEmplydList", paramMap);
	}

	/**
	 * 
	 * Desc : 신입사원 목록에서 사원 정보 DELETE
	 * @Method Name : deleteNewEmplyd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteNewEmplyd(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("newemplyd.deleteNewEmplyd", paramMap);
	}

	/**
	 * 
	 * Desc : 새로운 신입사원의 정보를 신입사원 테이블에 INSERT
	 * @Method Name : enrollNewEmplyd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int enrollNewEmplyd(HashMap<String, Object> paramMap) throws Exception{
		if(paramMap.get("engineer_image") != null){
			return (int)insert("newemplyd.enrollNewEmplyd", paramMap);
		}else{
			return (int)insert("newemplyd.enrollNewEmplydDefaultImg", paramMap);
		}
		
	}

	/**
	 * 
	 * Desc : 신입사원의 이미지파일이 있으면 테이블에서 SELECT
	 * @Method Name : getNewEmplydImage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getNewEmplydImage(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>) selectOne("newemplyd.getNewEmplydImage", paramMap);
	}

}
