package com.metelsos.newemplyd.service;

import java.util.HashMap;
import java.util.List;

import com.metelsos.newemplyd.vo.NewemplydVo;

public interface NewemplydService {

	HashMap<String, Object> deleteNewEmplyd(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> setNewEmplydNameList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> enrollNewEmplyd(NewemplydVo vo) throws Exception;

	HashMap<String, Object> getNewEmplydImage(HashMap<String, String> paramMap) throws Exception;

}
