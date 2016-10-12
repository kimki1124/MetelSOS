package com.metelsos.user.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.user.vo.UserVo;

@Repository("userDao")
public class UserDao extends AbstractDAO{
	
	public UserVo checkUser(HashMap<String, String> paramMap){
		return (UserVo)selectOne("user.getUserByIdAndPasswd", paramMap);
	}

	public UserVo checkUserById(HashMap<String, String> paramMap) {
		return (UserVo)selectOne("user.getUserById", paramMap);
	}
}
