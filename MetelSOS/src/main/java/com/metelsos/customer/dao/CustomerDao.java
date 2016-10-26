package com.metelsos.customer.dao;

import java.util.HashMap;
import java.util.List;

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

	public List<CustomerVo> findCustomerId(HashMap<String, String> paramMap) throws Exception{
		return (List<CustomerVo>)selectList("customer.getCustomerByNameAndEmail", paramMap);
	}

	public CustomerVo findCustomer(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerByIdAndNameAndEmail", paramMap);
	}

	public void updateCustomerPasswd(CustomerVo vo) throws Exception{
		update("customer.updateCustomerPasswd", vo);
	}

	public void updateCustomerInfo(CustomerVo vo) throws Exception{
		update("customer.updateCustomerInfo", vo);
	}

	public int deleteCustomerAccount(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("customer.deleteCustomer", paramMap);
	}

}
