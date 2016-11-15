package com.metelsos.exclntstf.service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metelsos.engineer.dao.EngineerDao;
import com.metelsos.engineer.vo.EngineerVo;
import com.metelsos.exclntstf.dao.ExclntStfDao;
import com.metelsos.exclntstf.vo.ExclntStfVo;
/**
 * 
* <pre>
* com.metelsos.exclntstf.service
*   |_ ExclntstfServiceImpl.java
* </pre>
* 
* Desc : 우수사원 관련 서비스 구현 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 6:27:55
* @Version :
 */
@Service("exclntstfService")
public class ExclntstfServiceImpl implements ExclntstfService{

	@Resource(name="exclntstfDao")
	private ExclntStfDao exclntstfDao;
	
	@Resource(name="engineerDao")
	private EngineerDao engineerDao;
	
	/**
	 * 
	 * Desc : 우수사원에 등록했던 사원의 정보 삭제
	 * @Method Name : deleteExclntStf
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> deleteExclntStf(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("engineer_dept", URLDecoder.decode(paramMap.get("engineer_dept"), "UTF-8"));
		paramMap.put("engineer_name", URLDecoder.decode(paramMap.get("engineer_name"), "UTF-8"));
		
		int result = exclntstfDao.deleteExclntStf(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("engineer_dept", paramMap.get("engineer_dept"));
			returnMap.put("engineer_name", paramMap.get("engineer_name"));
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 우수사원을 등록할 때에 어떤 사원을 등록할지 사원의 이름 리스트 가져옴
	 * @Method Name : setEngineerNameList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> setEngineerNameList(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("engineer_dept", URLDecoder.decode(paramMap.get("engineer_dept"), "UTF-8"));
		
		List<String> nameList = engineerDao.getEngineerNameListByDept(paramMap);
		
		if(nameList.size() > 0){
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("engineerNameList", nameList);
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 우수사원 테이블에 사원의 정보를 등록
	 * @Method Name : enrollExclntStf
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> enrollExclntStf(ExclntStfVo vo) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		paramMap.put("exclnt_year_month", vo.getExclnt_year_month());
		paramMap.put("engineer_dept", vo.getEngineer_dept());
		paramMap.put("engineer_name", vo.getEngineer_name());
		paramMap.put("impression_speech", vo.getImpression_speech());
		paramMap.put("engineer_image", vo.getEngineer_image().getBytes());
		
		String engineerPosition = engineerDao.getEngineerPosition(paramMap);
		paramMap.put("engineer_position", engineerPosition);
		
		int result = exclntstfDao.insertExclntStf(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : 우수사원의 리스트를 뷰에 뿌릴 때 우수사원의 이미지를 가져옴
	 * @Method Name : getExclntStfImage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getExclntStfImage(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("engineer_dept", new String(paramMap.get("engineer_dept").getBytes("iso-8859-1"), "utf-8"));
		paramMap.put("engineer_name", new String(paramMap.get("engineer_name").getBytes("iso-8859-1"), "utf-8"));
		returnMap = exclntstfDao.getExclntStfImage(paramMap);
		
		return returnMap;
	}

}
