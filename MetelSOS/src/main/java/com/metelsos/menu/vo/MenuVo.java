package com.metelsos.menu.vo;

import java.util.List;

public class MenuVo {
	private int menu_code;
	private String menu_title;
	private int menu_parent_code;
	private int menu_seq;
	private int menu_level;
	private String menu_visible;
	private String menu_icon;
	private String menu_eng_title;
	private List<MenuVo> children;
	
	
	public int getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(int menu_code) {
		this.menu_code = menu_code;
	}
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public int getMenu_parent_code() {
		return menu_parent_code;
	}
	public void setMenu_parent_code(int menu_parent_code) {
		this.menu_parent_code = menu_parent_code;
	}
	public int getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(int menu_seq) {
		this.menu_seq = menu_seq;
	}
	public int getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(int menu_level) {
		this.menu_level = menu_level;
	}
	public String getMenu_visible() {
		return menu_visible;
	}
	public void setMenu_visible(String menu_visible) {
		this.menu_visible = menu_visible;
	}
	public List<MenuVo> getChildren() {
		return children;
	}
	public void setChildren(List<MenuVo> children) {
		this.children = children;
	}
	public String getMenu_icon() {
		return menu_icon;
	}
	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}
	public String getMenu_eng_title() {
		return menu_eng_title;
	}
	public void setMenu_eng_title(String menu_eng_title) {
		this.menu_eng_title = menu_eng_title;
	}
	
	
}
