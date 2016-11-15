package com.metelsos.common.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 
* <pre>
* com.metelsos.common.util
*   |_ MyAuthentication.java
* </pre>
* 
* Desc : 메일 전송 SMTP 사용하기 위한 권한 획득에 사용하는 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 3:09:19
* @Version :
 */
public class MyAuthentication extends Authenticator{
	private PasswordAuthentication pa;
	
	/**
	 * Desc : Constructor of MyAuthentication.java class
	 */
	public MyAuthentication(){
		String id="mymetelsos@gmail.com";
		String pw = "vnstnsu1";
		
		pa = new PasswordAuthentication(id, pw);
	}
	
	public PasswordAuthentication getPasswordAuthentication(){
		return pa;
	}
}
