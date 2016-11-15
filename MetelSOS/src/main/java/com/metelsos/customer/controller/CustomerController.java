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
/**
 * 
* <pre>
* com.metelsos.customer.controller
*   |_ CustomerController.java
* </pre>
* 
* Desc : 고객사 소속 고객에 관련된 컨트롤러 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 4:57:17
* @Version :
 */
@Controller
public class CustomerController {
	private Log log = LogFactory.getLog(CustomerController.class);
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	/**
	 * 
	 * Desc : 회원가입 시 고객의 ID validation 체크
	 * @Method Name : validateCustomerId
	 * @param paramMap -뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customerValidateId.do")
	public ModelAndView validateCustomerId(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => validateCustomerId");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = customerService.validateCustomerId(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	/**
	 * 
	 * Desc : 고객 회원 가입 버튼 눌렀을 때 정보를 DB에 insert
	 * @Method Name : registerCustomer
	 * @param paramMap -뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customerRegister.do")
	public ModelAndView registerCustomer(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => registerCustomer");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = customerService.insertCustomer(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	/**
	 * 
	 * Desc : 고객의 개인정보 업데이트 
	 * @Method Name : updateCustomerInfo
	 * @param paramMap -뷰에서 넘어올 때 파라미터에 담은 데이터가 들어있는 Map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateCustomerInfo.do")
	public ModelAndView updateCustomerInfo(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateCustomerInfo");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = customerService.updateCustomerInfo(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
}
