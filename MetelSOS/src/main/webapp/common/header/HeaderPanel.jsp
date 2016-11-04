<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
		<form name="myProfileParamForm">
			<input type="hidden" name="userType" />
			<input type="hidden" name="userId" />
			<input type="hidden" name="menuTitle" />
			<input type="hidden" name="menuIcon" />
		</form>
		<header id="header">
			<div id="logo-group">

				<!-- PLACE YOUR LOGO HERE -->
				<span id="logo"> <img src="/metelSOS/resources/img/logo.png" alt="MetelSOS"> </span>
				<!-- END LOGO PLACEHOLDER -->
			</div>

			<div class="pull-right">

				<!-- logout button -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="/metelSOS/logout.do" title="로그 아웃" data-action="userLogout" data-logout-msg="로그아웃 하시겠습니까?"><i class="fa fa-sign-out"></i></a> </span><!-- /metelSOS/logout.do -->
				</div>
				<!-- end logout button -->
				
				<!-- mypage button -->
				<div id="myProfile" class="btn-header pull-right">
					<span><a href="javascript:viewMyProfile('<%=session.getAttribute("SESSION_LOGIN_USER_TYPE") %>', '<%=session.getAttribute("SESSION_LOGIN_USER_ID") %>', '<%=session.getAttribute("SESSION_LOGIN_USER_NAME") %>');" data-action="viewMyProfile" title="내 정보 보기"><i class="fa fa-user"></i></a></span>
				</div>				
				<div id="welcomeMsg" class="pull-right" style="padding-top:5px;">
					<h6><span><%=session.getAttribute("SESSION_LOGIN_USER_NAME") %>님 환영합니다.</span></h6>
				</div>
				<!-- end fullscreen button -->

			</div>
			<!-- end pulled right: nav area -->

		</header>
		
		<script>
			function viewMyProfile(userType, userId, userName){
				var form = document.myProfileParamForm;
				form.userType.value = userType;
				form.userId.value = userId;
				form.menuTitle.value = encodeURI("마이 프로필");
				form.menuIcon.value = "fa fa-user";
				form.action = "/metelSOS/profilePageMove.do";
				form.method = "get";
				form.submit();
			}
		
			$(document).ready(function(){
				
			});
		</script>
</body>
</html>