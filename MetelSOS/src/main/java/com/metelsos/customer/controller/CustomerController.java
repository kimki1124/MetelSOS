package com.metelsos.customer.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.customer.service.CustomerService;

@Controller
public class CustomerController {
	private Log log = LogFactory.getLog(CustomerController.class);
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@RequestMapping(value="/customerValidateId.do")
	public ModelAndView validateCustomerId(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => validateCustomerId");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = customerService.validateCustomerId(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/customerRegister.do")
	public ModelAndView registerCustomer(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => registerCustomer");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = customerService.insertCustomer(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
}
