<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
		<aside id="left-panel">
			<nav>
				<ul>
					<c:forEach var="item" items="${menuList }" varStatus="status">
						<li>
						<c:choose>
							<c:when test="${fn:length(item.children) > 0}">
								<a href="#"><i class="${item.menu_icon}"></i><span class="menu-item-parent">${item.menu_title}</span></a>
								<c:if test="${fn:length(item.children) > 0}">
									<ul>
										<c:forEach var="childItem" items="${item.children}" varStatus="childStatus">
											<li>
												<a href="javascript:pageMove('${childItem.menu_eng_title}', '${childItem.menu_icon}')">${childItem.menu_title}</a>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</c:when>
							<c:otherwise>
								<a href="javascript:pageMove('${item.menu_eng_title}', '${item.menu_icon}')"><i class="${item.menu_icon}"></i><span class="menu-item-parent">${item.menu_title}</span></a>
							</c:otherwise>
						</c:choose>
								</li>
					</c:forEach>
				</ul>
			</nav>

			<span class="minifyme" data-action="minifyMenu"> <i class="fa fa-arrow-circle-left hit"></i> </span>

		</aside>
		
		<script>
			function pageMove(title, icon){
				document.location.href="/metelSOS/leftMenuPageMove.do?userType=customer&menuTitle="+title+"&menuIcon="+icon;
			}
		
			$(document).ready(function(){
				
			});
		</script>
</body>
</html>