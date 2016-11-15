package com.metelsos.engineer.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.engineer.vo.EngineerVo;
/**
 * 
* <pre>
* com.metelsos.engineer.dao
*   |_ EngineerDao.java
* </pre>
* 
* Desc : 엔지니어 회원관련 DAO클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 6:02:46
* @Version :
 */
@Repository("engineerDao")
public class EngineerDao extends AbstractDAO{

	/**
	 * 
	 * Desc : ID와 비밀번호로 엔지니어회원 SELECT
	 * @Method Name : checkLogin
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public EngineerVo checkLogin(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerByIdAndPasswd", paramMap);
	}

	/**
	 * 
	 * Desc : ID로 엔지니어 SELECT
	 * @Method Name : validateEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public EngineerVo validateEngineerId(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerById", paramMap);
	}

	/**
	 * 
	 * Desc : 엔지니어 회원가입 시 회원가입 폼에 작성한 회원정보 INSERT
	 * @Method Name : insertUser
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int insertUser(HashMap<String, String> paramMap) throws Exception{
		return (int)insert("engineer.insertEngineer", paramMap);
	}

	/**
	 * 
	 * Desc : 이름과 이메일로 엔지니어 회원 정보 SELECT
	 * @Method Name : findEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<EngineerVo> findEngineerId(HashMap<String, String> paramMap) throws Exception{
		return (List<EngineerVo>)selectList("engineer.getEngineerByNameAndEmail", paramMap);
	}

	/**
	 * 
	 * Desc : ID로 엔지니어 회원 정보 SELECT
	 * @Method Name : findEngineer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public EngineerVo findEngineer(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerByUserId", paramMap);
	}

	/**
	 * 
	 * Desc : 엔지니어 회원 비밀번호 UPDATE
	 * @Method Name : updateEngineerPasswd
	 * @param vo
	 */
	public void updateEngineerPasswd(EngineerVo vo) {
		update("engineer.updateEngineerPasswd", vo);
	}

	/**
	 * 
	 * Desc : 엔지니어 마이프로필 수정 시 회원정보 UPDATE
	 * @Method Name : updateEngineerInfo
	 * @param vo
	 * @throws Exception
	 */
	public void updateEngineerInfo(EngineerVo vo) throws Exception{
		update("engineer.updateEngineerInfo", vo);
	}

	/**
	 * 
	 * Desc : 엔지니어 회원 탈퇴 시 해당 엔지니어 정보 DELETE
	 * @Method Name : deleteEngineerAccount
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteEngineerAccount(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("engineer.deleteEngineer", paramMap);
	}

	/**
	 * 
	 * Desc : 엔지니어가 소속된 부서의 리스트 SELECT
	 * @Method Name : getDeptList
	 * @return
	 * @throws Exception
	 */
	public List<String> getDeptList() throws Exception{
		return (List<String>)selectList("engineer.getDeptList");
	}

	/**
	 * 
	 * Desc : 해당 부서에 소속된 엔지니어들의 리스트 SELECT
	 * @Method Name : getEngineerNameListByDept
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<String> getEngineerNameListByDept(HashMap<String, String> paramMap) throws Exception{
		return (List<String>)selectList("engineer.getEngineerNameListByDept", paramMap);
	}

	/**
	 * 
	 * Desc : 해당 엔지니어의 ENGINEER_POSITION SELECT
	 * @Method Name : getEngineerPosition
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public String getEngineerPosition(HashMap<String, Object> paramMap) throws Exception{
		return (String)selectOne("engineer.getEngineerPosition", paramMap);
	}

	/**
	 * 
	 * Desc : 해당 월의 신입 엔지니어들의 ENGINEER_NAME 리스트 SELECT
	 * @Method Name : getNewEmplydNameList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<String> getNewEmplydNameList(HashMap<String, String> paramMap) throws Exception{
		return (List<String>)selectList("engineer.getNewEmplydNameList", paramMap);
	}

	/**
	 * 
	 * Desc : 해당 엔지니어의 ENGINEER_POSITION SELECT
	 * @Method Name : selectEngineerPosition
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public String selectEngineerPosition(HashMap<String, String> paramMap) throws Exception{
		return (String)selectOne("engineer.selectEngineerPosition", paramMap);
	}

}
