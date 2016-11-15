package com.metelsos.company.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.company.vo.CompanyVo;

/**
 * 
* <pre>
* com.metelsos.company.dao
*   |_ CompanyDao.java
* </pre>
* 
* Desc : 고객사 관련 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 4:52:40
* @Version :
 */
@Repository("companyDao")
public class CompanyDao extends AbstractDAO{
	/**
	 * 
	 * Desc : 고객사의 목록을 가져와서 CompanyVo 리스트 형태로 리턴
	 * @Method Name : setItemForRegisterForm
	 * @return
	 * @throws Exception
	 */
	public List<CompanyVo> setItemForRegisterForm() throws Exception{
		return (List<CompanyVo>)selectList("company.getCustomerCompanyName");
	}

}
