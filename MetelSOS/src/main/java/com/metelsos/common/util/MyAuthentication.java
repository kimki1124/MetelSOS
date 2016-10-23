package com.metelsos.common.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthentication extends Authenticator{
	private PasswordAuthentication pa;
	
	public MyAuthentication(){
		String id="mymetelsos@gmail.com";
		String pw = "vnstnsu1";
		
		pa = new PasswordAuthentication(id, pw);
	}
	
	public PasswordAuthentication getPasswordAuthentication(){
		return pa;
	}
}
