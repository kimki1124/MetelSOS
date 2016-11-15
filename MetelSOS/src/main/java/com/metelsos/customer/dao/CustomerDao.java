package com.metelsos.customer.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.customer.vo.CustomerVo;

/**
 * 
* <pre>
* com.metelsos.customer.dao
*   |_ CustomerDao.java
* </pre>
* 
* Desc : 고객사 소속 고객 관련 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 4:59:52
* @Version :
 */
@Repository("customerDao")
public class CustomerDao extends AbstractDAO{

	/**
	 * 
	 * Desc : ID와 비밀번호로 SELECT된 CustomerVo 하나 리턴
	 * @Method Name : checkLogin
	 * @param paramMap - 조건절에서 비교할 ID와 비밀번호가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	public CustomerVo checkLogin(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerByIdAndPasswd", paramMap);
	}

	/**
	 * 
	 * Desc : ID로 SELECT된 CustomerVo 하나 리턴
	 * @Method Name : validateCustomerId
	 * @param paramMap - 조건절에서 비교할 ID가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	public CustomerVo validateCustomerId(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerById", paramMap);
	}

	/**
	 * 
	 * Desc : 회원가입 시 고객의 정보를 INSERT
	 * @Method Name : insertUser
	 * @param paramMap - 회원가입 폼에서 고객이 작성한 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	public int insertUser(HashMap<String, Object> paramMap) throws Exception{
		return (int) insert("customer.insertCustomer", paramMap);
	}

	/**
	 * 
	 * Desc : ID와 이메일로 고객 회원의 정보를 찾는 메서드
	 * @Method Name : findCustomerId
	 * @param paramMap - ID와 이메일 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	public List<CustomerVo> findCustomerId(HashMap<String, String> paramMap) throws Exception{
		return (List<CustomerVo>)selectList("customer.getCustomerByNameAndEmail", paramMap);
	}

	/**
	 * 
	 * Desc : ID, 이름, 이메일로 고객회원 정보 찾는 메서드 
	 * @Method Name : findCustomer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public CustomerVo findCustomer(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerByIdAndNameAndEmail", paramMap);
	}

	/**
	 * 
	 * Desc : 고객회원 비밀번호 변경 
	 * @Method Name : updateCustomerPasswd
	 * @param vo
	 * @throws Exception
	 */
	public void updateCustomerPasswd(CustomerVo vo) throws Exception{
		update("customer.updateCustomerPasswd", vo);
	}

	/**
	 * 
	 * Desc : 고객회원정보 업데이트
	 * @Method Name : updateCustomerInfo
	 * @param vo
	 * @throws Exception
	 */
	public void updateCustomerInfo(CustomerVo vo) throws Exception{
		update("customer.updateCustomerInfo", vo);
	}

	/**
	 * 
	 * Desc : 고객회원탈퇴
	 * @Method Name : deleteCustomerAccount
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomerAccount(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("customer.deleteCustomer", paramMap);
	}

	/**
	 * 
	 * Desc : ID로 고객회원 정보 SELECT
	 * @Method Name : getCustomerInfo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCustomerInfo(String userId) throws Exception{
		return (HashMap<String, Object>)selectOne("customer.getCustomerInfo", userId);
	}

	/**
	 * 
	 * Desc : CUSTOMER_NAME하고 CUSTOMER_PHONE으로 고객회원정보 SELECT
	 * @Method Name : selectCustomerPosition
	 * @param supportMap
	 * @return
	 * @throws Exception
	 */
	public String selectCustomerPosition(HashMap<String, String> supportMap) throws Exception{
		return (String)selectOne("customer.selectCustomerPosition", supportMap);
	}

}
