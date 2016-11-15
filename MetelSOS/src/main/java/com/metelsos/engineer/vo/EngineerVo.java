package com.metelsos.engineer.vo;
/**
 * 
* <pre>
* com.metelsos.engineer.vo
*   |_ EngineerVo.java
* </pre>
* 
* Desc : 엔지니어 회원 관련 VO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 6:21:58
* @Version :
 */
public class EngineerVo {
	private int engineer_num;
	private String engineer_id;
	private String engineer_passwd;
	private String engineer_name;
	private String engineer_dept;
	private String engineer_email;
	private String engineer_phone;
	private String engineer_create_date;
	private String last_connect_date;
	private String engineer_position;
	
	public int getEngineer_num() {
		return engineer_num;
	}
	public void setEngineer_num(int engineer_num) {
		this.engineer_num = engineer_num;
	}
	public String getEngineer_id() {
		return engineer_id;
	}
	public void setEngineer_id(String engineer_id) {
		this.engineer_id = engineer_id;
	}
	public String getEngineer_passwd() {
		return engineer_passwd;
	}
	public void setEngineer_passwd(String engineer_passwd) {
		this.engineer_passwd = engineer_passwd;
	}
	public String getEngineer_name() {
		return engineer_name;
	}
	public void setEngineer_name(String engineer_name) {
		this.engineer_name = engineer_name;
	}
	public String getEngineer_dept() {
		return engineer_dept;
	}
	public void setEngineer_dept(String engineer_dept) {
		this.engineer_dept = engineer_dept;
	}
	public String getEngineer_email() {
		return engineer_email;
	}
	public void setEngineer_email(String engineer_email) {
		this.engineer_email = engineer_email;
	}
	public String getEngineer_phone() {
		return engineer_phone;
	}
	public void setEngineer_phone(String engineer_phone) {
		this.engineer_phone = engineer_phone;
	}
	public String getEngineer_create_date() {
		return engineer_create_date;
	}
	public void setEngineer_create_date(String engineer_create_date) {
		this.engineer_create_date = engineer_create_date;
	}
	public String getLast_connect_date() {
		return last_connect_date;
	}
	public void setLast_connect_date(String last_connect_date) {
		this.last_connect_date = last_connect_date;
	}
	public String getEngineer_position() {
		return engineer_position;
	}
	public void setEngineer_position(String engineer_position) {
		this.engineer_position = engineer_position;
	}
	
	
}
