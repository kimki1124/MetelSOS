package com.metelsos.company.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metelsos.company.dao.CompanyDao;
import com.metelsos.company.vo.CompanyVo;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	@Resource(name="companyDao")
	private CompanyDao companyDao;

	@Override
	public List<CompanyVo> setItemForRegisterForm() throws Exception {
		return companyDao.setItemForRegisterForm();
	}
	
	
}
