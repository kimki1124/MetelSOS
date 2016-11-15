package com.metelsos.customer.vo;
/**
 * 
* <pre>
* com.metelsos.customer.vo
*   |_ CustomerVo.java
* </pre>
* 
* Desc : 고객회원정보 관련 VO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 6:00:31
* @Version :
 */
public class CustomerVo {
	private int customer_num;
	private String customer_id;
	private String customer_passwd;
	private String customer_name;
	private String company_name;
	private String customer_position;
	private String customer_email;
	private String customer_phone;
	private String customer_create_date;
	private String last_connect_date;
	
	public int getCustomer_num() {
		return customer_num;
	}
	public void setCustomer_num(int customer_num) {
		this.customer_num = customer_num;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_passwd() {
		return customer_passwd;
	}
	public void setCustomer_passwd(String customer_passwd) {
		this.customer_passwd = customer_passwd;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCustomer_position() {
		return customer_position;
	}
	public void setCustomer_position(String customer_position) {
		this.customer_position = customer_position;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_create_date() {
		return customer_create_date;
	}
	public void setCustomer_create_date(String customer_create_date) {
		this.customer_create_date = customer_create_date;
	}
	public String getLast_connect_date() {
		return last_connect_date;
	}
	public void setLast_connect_date(String last_connect_date) {
		this.last_connect_date = last_connect_date;
	}
}
