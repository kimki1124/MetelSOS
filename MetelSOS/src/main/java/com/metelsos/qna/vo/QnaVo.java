package com.metelsos.qna.vo;
/**
 * 
* <pre>
* com.metelsos.qna.vo
*   |_ QnaVo.java
* </pre>
* 
* Desc : 고객회원 QnA 관련 VO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. 오전 11:13:52
* @Version :
 */
public class QnaVo {
	private int qna_num;
	private String qna_question;
	private String qna_answer;
	private String qna_hit;
	
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getQna_question() {
		return qna_question;
	}
	public void setQna_question(String qna_question) {
		this.qna_question = qna_question;
	}
	public String getQna_answer() {
		return qna_answer;
	}
	public void setQna_answer(String qna_answer) {
		this.qna_answer = qna_answer;
	}
	public String getQna_hit() {
		return qna_hit;
	}
	public void setQna_hit(String qna_hit) {
		this.qna_hit = qna_hit;
	}
}
