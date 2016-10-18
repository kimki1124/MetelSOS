<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<!--

	TABLE OF CONTENTS.
	
	Use search to find needed section.
	
	===================================================================
	
	|  01. #CSS Links                |  all CSS links and file paths  |
	|  02. #FAVICONS                 |  Favicon links and file paths  |
	|  03. #GOOGLE FONT              |  Google font link              |
	|  04. #APP SCREEN / ICONS       |  app icons, screen backdrops   |
	|  05. #BODY                     |  body tag                      |
	|  06. #HEADER                   |  header tag                    |
	|  07. #PROJECTS                 |  project lists                 |
	|  08. #TOGGLE LAYOUT BUTTONS    |  layout buttons and actions    |
	|  09. #MOBILE                   |  mobile view dropdown          |
	|  10. #SEARCH                   |  search field                  |
	|  11. #NAVIGATION               |  left panel & navigation       |
	|  12. #MAIN PANEL               |  main panel                    |
	|  13. #MAIN CONTENT             |  content holder                |
	|  14. #PAGE FOOTER              |  page footer                   |
	|  15. #SHORTCUT AREA            |  dropdown shortcuts area       |
	|  16. #PLUGINS                  |  all scripts and plugins       |
	
	===================================================================
	
	-->
	
	<!-- #BODY -->
	<!-- Possible Classes

		* 'smart-style-{SKIN#}'
		* 'smart-rtl'         - Switch theme mode to RTL
		* 'menu-on-top'       - Switch to top navigation (no DOM change required)
		* 'no-menu'			  - Hides the menu completely
		* 'hidden-menu'       - Hides the main menu but still accessable by hovering over left edge
		* 'fixed-header'      - Fixes the header
		* 'fixed-navigation'  - Fixes the main menu
		* 'fixed-ribbon'      - Fixes breadcrumb
		* 'fixed-page-footer' - Fixes footer
		* 'container'         - boxed layout mode (non-responsive: will not work with fixed-navigation & fixed-ribbon)
	-->
</head>
<body>
		<header id="header">
			<div id="logo-group">

				<!-- PLACE YOUR LOGO HERE -->
				<span id="logo"> <img src="/metelSOS/resources/img/logo.png" alt="MetelSOS"> </span>
				<!-- END LOGO PLACEHOLDER -->
			</div>

			<div class="pull-right">

				<!-- logout button -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="/metelSOS/logout.do" title="로그 아웃" data-action="userLogout" data-logout-msg="로그아웃 하시겠습니까?"><i class="fa fa-sign-out"></i></a> </span>
				</div>
				<!-- end logout button -->
				
				<!-- mypage button -->
				<div id="myProfile" class="btn-header pull-right">
					<span><a href="" data-action="viewMyProfile" title="내 정보 보기"><i class="fa fa-user"></i></a></span>
				</div>				
				<div id="welcomeMsg" class="pull-right" style="padding-top:5px;">
					<h6><span><%=session.getAttribute("SESSION_LOGIN_USER_NAME") %>님 환영합니다.</span></h6>
					<%-- <c:if test="${empty engineerName}">
						<h6><span>${customerName}님 환영합니다.</span></h6>
					</c:if>
					<c:if test="${empty customerName}">
						<h6><span>${engineerName}님 환영합니다.</span></h6>
					</c:if> --%>
				</div>
				<!-- end fullscreen button -->

			</div>
			<!-- end pulled right: nav area -->

		</header>
</body>
</html>