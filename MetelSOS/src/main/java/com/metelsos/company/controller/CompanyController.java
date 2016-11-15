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

/**
 * 
* <pre>
* com.metelsos.company.controller
*   |_ CompanyController.java
* </pre>
* 
* Desc : 고객사 관련 컨트롤러 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 3:49:38
* @Version :
 */
@Controller
public class CompanyController {
	private Log log = LogFactory.getLog(CompanyController.class);
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	/**
	 * 
	 * Desc : 고객사 회원가입 페이지로 넘어갈 때 셀렉트박스에 set할 아이템을 가져와서 뷰로 넘어감
	 * @Method Name : setItemForRegisterForm
	 * @return
	 * @throws Exception
	 */
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
