package com.metelsos.menu.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.menu.vo.MenuVo;

@Repository("menuDao")
public class MenuDao extends AbstractDAO{

	public List<MenuVo> getEngineerLeftMenuList() {
		return (List<MenuVo>)selectList("engineerMenu.getEngineerMenuList");
	}

	public List<MenuVo> getCustomerLeftMenuList() throws Exception{
		return (List<MenuVo>)selectList("customerMenu.getCustomerMenuList");
	}
	
}
