package com.metelsos.menu.service;

import java.util.List;

import com.metelsos.menu.vo.MenuVo;

public interface MenuService {

	List<MenuVo> getEngineerLeftMenuList() throws Exception;

	List<MenuVo> getCustomerLeftMenuList() throws Exception;

}
