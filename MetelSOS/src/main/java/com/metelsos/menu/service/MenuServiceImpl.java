package com.metelsos.menu.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metelsos.menu.dao.MenuDao;
import com.metelsos.menu.vo.MenuVo;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Resource(name="menuDao")
	private MenuDao menuDao;

	@Override
	public List<MenuVo> getEngineerLeftMenuList() throws Exception {
		List<MenuVo> list = new ArrayList<MenuVo>();
		List<MenuVo> filterList = new ArrayList<MenuVo>();
		List<MenuVo> childList = null;
		list = menuDao.getEngineerLeftMenuList();
		MenuVo vo = new MenuVo();
		MenuVo tempVo = new MenuVo();
		
		// 메뉴리스트 가공
		for(int i=0;i<list.size();i++){
			childList = new ArrayList<MenuVo>();
			vo = list.get(i);
			for(int j=0;j<list.size();j++){
				tempVo = list.get(j);
				if(tempVo.getMenu_parent_code() == vo.getMenu_code()){
					childList.add(tempVo);
				}
			}
			vo.setChildren(childList);
			
			if(vo.getMenu_parent_code() == 0){
				filterList.add(vo);
			}
		}
		
		return filterList;
	}

	@Override
	public List<MenuVo> getCustomerLeftMenuList() throws Exception {
		List<MenuVo> list = new ArrayList<MenuVo>();
		List<MenuVo> filterList = new ArrayList<MenuVo>();
		List<MenuVo> childList = null;
		list = menuDao.getCustomerLeftMenuList();
		MenuVo vo = new MenuVo();
		MenuVo tempVo = new MenuVo();
		
		// 메뉴리스트 가공
		for(int i=0;i<list.size();i++){
			childList = new ArrayList<MenuVo>();
			vo = list.get(i);
			for(int j=0;j<list.size();j++){
				tempVo = list.get(j);
				if(tempVo.getMenu_parent_code() == vo.getMenu_code()){
					childList.add(tempVo);
				}
			}
			vo.setChildren(childList);
			
			if(vo.getMenu_parent_code() == 0){
				filterList.add(vo);
			}
		}
		
		return filterList;
	}
}
