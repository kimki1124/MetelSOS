package com.metelsos.customer.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.customer.vo.CustomerVo;

@Repository("customerDao")
public class CustomerDao extends AbstractDAO{

	public CustomerVo checkLogin(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerByIdAndPasswd", paramMap);
	}

	public CustomerVo validateCustomerId(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerById", paramMap);
	}

	public int insertUser(HashMap<String, Object> paramMap) throws Exception{
		return (int) insert("customer.insertCustomer", paramMap);
	}

}
