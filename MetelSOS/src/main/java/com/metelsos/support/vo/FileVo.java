package com.metelsos.support.vo;
/**
 * 
* <pre>
* com.metelsos.support.vo
*   |_ FileVo.java
* </pre>
* 
* Desc : 지원 요청 글 첨부파일 관련 VO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오후 9:02:14
* @Version :
 */
public class FileVo {
	private int file_num;
	private int board_num;
	private String original_file_name;
	private String stored_file_name;
	private int file_size;
	private String crea_dtm;
	private String crea_id;
	private String del_gb;
	
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getOriginal_file_name() {
		return original_file_name;
	}
	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}
	public String getStored_file_name() {
		return stored_file_name;
	}
	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public String getCrea_dtm() {
		return crea_dtm;
	}
	public void setCrea_dtm(String crea_dtm) {
		this.crea_dtm = crea_dtm;
	}
	public String getCrea_id() {
		return crea_id;
	}
	public void setCrea_id(String crea_id) {
		this.crea_id = crea_id;
	}
	public String getDel_gb() {
		return del_gb;
	}
	public void setDel_gb(String del_gb) {
		this.del_gb = del_gb;
	}
}
