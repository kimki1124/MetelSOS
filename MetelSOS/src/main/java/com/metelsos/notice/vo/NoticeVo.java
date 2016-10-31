package com.metelsos.notice.vo;

public class NoticeVo {
	private int notice_num;
	private int parent_notice_num;
	private String notice_title;
	private String notice_author;
	private String notice_date;
	private String notice_content;
	private int notice_hit;
	private String del_gb;
	private String has_file;
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public int getParent_notice_num() {
		return parent_notice_num;
	}
	public void setParent_notice_num(int parent_notice_num) {
		this.parent_notice_num = parent_notice_num;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_author() {
		return notice_author;
	}
	public void setNotice_author(String notice_author) {
		this.notice_author = notice_author;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_hit() {
		return notice_hit;
	}
	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}
	public String getDel_gb() {
		return del_gb;
	}
	public void setDel_gb(String del_gb) {
		this.del_gb = del_gb;
	}
	public String getHas_file() {
		return has_file;
	}
	public void setHas_file(String has_file) {
		this.has_file = has_file;
	}
}
