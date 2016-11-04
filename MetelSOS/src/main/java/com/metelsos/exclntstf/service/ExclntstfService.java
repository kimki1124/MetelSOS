package com.metelsos.exclntstf.service;

import java.util.HashMap;

import com.metelsos.exclntstf.vo.ExclntStfVo;

public interface ExclntstfService {

	HashMap<String, Object> deleteExclntStf(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> setEngineerNameList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> enrollExclntStf(ExclntStfVo vo) throws Exception;

	HashMap<String, Object> getExclntStfImage(HashMap<String, String> paramMap) throws Exception;

}
