package com.metelsos.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		String stringMonth = String.valueOf(month);
		System.out.println(stringMonth);
		System.out.println(year);
		System.out.println(month-5);
	}

}
