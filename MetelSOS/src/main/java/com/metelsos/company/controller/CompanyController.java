package com.metelsos.company.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.company.service.CompanyService;
import com.metelsos.company.vo.CompanyVo;

@Controller
public class CompanyController {
	private Log log = LogFactory.getLog(CompanyController.class);
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	@RequestMapping(value="/setItemForRegisterForm.do")
	public ModelAndView setItemForRegisterForm() throws Exception{
		log.info("#operation => setItemForRegisterForm");
		ModelAndView modelAndView = new ModelAndView();
		List<CompanyVo> returnList= companyService.setItemForRegisterForm();
		modelAndView.addObject("customerCompanyList", returnList);
		modelAndView.setViewName("/customer/CustomerRegister");
		
		return modelAndView;
	}
}
