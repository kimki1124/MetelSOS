<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<aside id="left-panel">

			<!-- NAVIGATION : This navigation is also responsive

			To make this navigation dynamic please make sure to link the node
			(the reference to the nav > ul) after page load. Or the navigation
			will not initialize.
			-->
			<!-- 네비게이션 메뉴 -->
			<nav>
				<!-- 
				NOTE: Notice the gaps after each icon usage <i></i>..
				Please note that these links work a bit different than
				traditional href="" links. See documentation for details.
				-->
				<ul>
					<c:forEach var="item" items="${menuList }" varStatus="status">
						<c:choose>
							<c:when test="${status.count == 1}">
								<li class="active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${fn:length(item.children) > 0}">
								<a href="#"><i class="${item.menu_icon}"></i><span class="menu-item-parent">${item.menu_title}</span></a>
								<c:if test="${fn:length(item.children) > 0}">
									<ul>
										<c:forEach var="childItem" items="${item.children}" varStatus="childStatus">
											<li>
												<a href="#">${childItem.menu_title}</a>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</c:when>
							<c:otherwise>
								<a href="#"><i class="${item.menu_icon}"></i><span class="menu-item-parent">${item.menu_title}</span></a>
							</c:otherwise>
						</c:choose>
								</li>
					</c:forEach>
				</ul>
				<!-- <ul>
					<li class="">
						<a href="#" title="blank_"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">메인화면</span></a>
					</li>
					<li class="">
						<a href="#" title="blank_"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">지원 요청</span></a>
					</li>
					<li class="">
						<a href="#" title="blank_"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">지원 히스토리</span></a>
					</li>
					<li class="">
						<a href="#" title="blank_"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">엔지니어 검색</span></a>
					</li>
					<li class="">
						<a href="#" title="blank_"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">제품 설치 메뉴얼</span></a>
					</li>
					<li class="">
						<a href="#" title="blank_"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">자주 묻는 질문</span></a>
					</li>
				</ul> -->
			</nav>

			<span class="minifyme" data-action="minifyMenu"> <i class="fa fa-arrow-circle-left hit"></i> </span>

		</aside>
</body>
</html>