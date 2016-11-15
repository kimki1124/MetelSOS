package com.metelsos.company.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metelsos.company.dao.CompanyDao;
import com.metelsos.company.vo.CompanyVo;
/**
 * 
* <pre>
* com.metelsos.company.service
*   |_ CompanyServiceImpl.java
* </pre>
* 
* Desc : 고객사 관련 서비스 구현 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 4:53:55
* @Version :
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	@Resource(name="companyDao")
	private CompanyDao companyDao;

	/**
	 * DAO에서 넘겨준 CompanyVo 리스트를 그대로 컨트롤러로 리턴
	 * Desc : 
	 * @Method Name : setItemForRegisterForm
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CompanyVo> setItemForRegisterForm() throws Exception {
		return companyDao.setItemForRegisterForm();
	}
	
	
}
