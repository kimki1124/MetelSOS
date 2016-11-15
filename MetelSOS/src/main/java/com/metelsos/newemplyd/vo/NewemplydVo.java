package com.metelsos.newemplyd.vo;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
* <pre>
* com.metelsos.newemplyd.vo
*   |_ NewemplydVo.java
* </pre>
* 
* Desc : 신입사원 관련 VO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 10:04:00
* @Version :
 */
public class NewemplydVo {
	private String new_emply_year_month;
	private String engineer_name;
	private String engineer_position;
	private String engineer_dept;
	private MultipartFile engineer_image;
	private String impression_speech;
	
	public String getNew_emply_year_month() {
		return new_emply_year_month;
	}
	public void setNew_emply_year_month(String new_emply_year_month) {
		this.new_emply_year_month = new_emply_year_month;
	}
	public String getEngineer_name() {
		return engineer_name;
	}
	public void setEngineer_name(String engineer_name) {
		this.engineer_name = engineer_name;
	}
	public String getEngineer_position() {
		return engineer_position;
	}
	public void setEngineer_position(String engineer_position) {
		this.engineer_position = engineer_position;
	}
	public String getEngineer_dept() {
		return engineer_dept;
	}
	public void setEngineer_dept(String engineer_dept) {
		this.engineer_dept = engineer_dept;
	}
	public MultipartFile getEngineer_image() {
		return engineer_image;
	}
	public void setEngineer_image(MultipartFile engineer_image) {
		this.engineer_image = engineer_image;
	}
	public String getImpression_speech() {
		return impression_speech;
	}
	public void setImpression_speech(String impression_speech) {
		this.impression_speech = impression_speech;
	}
}
