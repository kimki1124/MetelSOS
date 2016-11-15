package com.metelsos.menu.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.menu.vo.MenuVo;
/**
 * 
* <pre>
* com.metelsos.menu.dao
*   |_ MenuDao.java
* </pre>
* 
* Desc : LEFTMENU 관련 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 9:18:33
* @Version :
 */
@Repository("menuDao")
public class MenuDao extends AbstractDAO{

	/**
	 * 
	 * Desc : 엔지니어회원 LEFTMENU 리스트 가져옴
	 * @Method Name : getEngineerLeftMenuList
	 * @return
	 */
	public List<MenuVo> getEngineerLeftMenuList() {
		return (List<MenuVo>)selectList("engineerMenu.getEngineerMenuList");
	}

	/**
	 * 
	 * Desc : 고객회원 LEFTMENU 리스트 가져옴
	 * @Method Name : getCustomerLeftMenuList
	 * @return
	 * @throws Exception
	 */
	public List<MenuVo> getCustomerLeftMenuList() throws Exception{
		return (List<MenuVo>)selectList("customerMenu.getCustomerMenuList");
	}
	
}
