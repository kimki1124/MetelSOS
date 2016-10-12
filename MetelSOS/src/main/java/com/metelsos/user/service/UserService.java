package com.metelsos.user.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {

	HashMap<String, Object> loginUser(HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception;

	HashMap<String, Object> validateId(HashMap<String, String> paramMap) throws Exception;

}
