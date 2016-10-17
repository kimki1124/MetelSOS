package com.metelsos.company.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.company.vo.CompanyVo;

@Repository("companyDao")
public class CompanyDao extends AbstractDAO{

	public List<CompanyVo> setItemForRegisterForm() throws Exception{
		return (List<CompanyVo>)selectList("company.getCustomerCompanyName");
	}

}
