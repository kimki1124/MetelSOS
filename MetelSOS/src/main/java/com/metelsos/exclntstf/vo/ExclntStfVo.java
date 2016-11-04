package com.metelsos.exclntstf.vo;

import org.springframework.web.multipart.MultipartFile;

public class ExclntStfVo {
	private String exclnt_year_month;
	private String engineer_name;
	private String engineer_position;
	private String engineer_dept;
	private MultipartFile engineer_image;
	private String impression_speech;
	
	public String getExclnt_year_month() {
		return exclnt_year_month;
	}
	public void setExclnt_year_month(String exclnt_year_month) {
		this.exclnt_year_month = exclnt_year_month;
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
