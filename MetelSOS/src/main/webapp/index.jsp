<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.io.File"%>
<%@page import="javax.servlet.ServletContext"%>

<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	
	request.getRequestDispatcher("main.jsp").forward(request, response);
%>
<%-- <%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	
	if(session == null || session.getAttribute("SESSION_LOGIN_USER_ID") == null){
		System.out.println(session);
		//세션이 없을 때 로그인 페이지로 이동
		request.getRequestDispatcher("login.jsp").forward(request, response);
	} else {
		//세션이 있을 때 메인 페이지로 이동
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
%> --%>