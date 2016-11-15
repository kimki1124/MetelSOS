package com.metelsos.newemplyd.service;

import java.util.HashMap;
import java.util.List;

import com.metelsos.newemplyd.vo.NewemplydVo;
/**
 * 
* <pre>
* com.metelsos.newemplyd.service
*   |_ NewemplydService.java
* </pre>
* 
* Desc : 신입사원 관련 서비스 인터페이스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 9:50:43
* @Version :
 */
public interface NewemplydService {

	HashMap<String, Object> deleteNewEmplyd(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> setNewEmplydNameList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> enrollNewEmplyd(NewemplydVo vo) throws Exception;

	HashMap<String, Object> getNewEmplydImage(HashMap<String, String> paramMap) throws Exception;

}
