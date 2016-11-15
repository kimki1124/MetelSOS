package com.metelsos.company.service;

import java.util.List;

import com.metelsos.company.vo.CompanyVo;
/**
 * 
* <pre>
* com.metelsos.company.service
*   |_ CompanyService.java
* </pre>
* 
* Desc : 고객사 관련 서비스 인터페이스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 4:53:38
* @Version :
 */
public interface CompanyService {

	List<CompanyVo> setItemForRegisterForm() throws Exception;

}
