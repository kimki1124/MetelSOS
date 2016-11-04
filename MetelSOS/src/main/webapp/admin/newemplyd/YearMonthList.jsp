<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<title>+++  MetelSOS  +++</title>
		<%@ include file="/common/include/include-header.jsp" %>
		<script src="/metelSOS/resources/js/common.js" charset="utf-8"></script>
		<script>
			//세션없으면 로그인 페이지로 이동
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId == 'null'){
				document.location.href="/metelSOS/login.jsp";
			}
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/engineer/EngineerLeftPanel.jsp" flush="false"/>
		<form name="noticePageParamForm">
			<input type="hidden" name="userType">
			<input type="hidden" name="menuTitle">
			<input type="hidden" name="menuIcon">
		</form>
		<!-- #MAIN PANEL -->
		<div id="main" role="main">

			<!-- RIBBON -->
			<div id="ribbon">

				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>Home</li>
					<c:forEach var="item" items="${breadcrumbList}" varStatus="status">
						<li>${item}</li>
					</c:forEach>
					<!-- <li>메인화면</li> -->
				</ol>
				<!-- end breadcrumb -->
			</div>
			<!-- END RIBBON -->

			<!-- #MAIN CONTENT -->
			<div id="content">
				
				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h3 class="page-title txt-color-blueDark">
							<i class="${menuIcon}"></i> 
								${menuTitle} 
						</h3>
					</div>
				</div>
				
				<section id="widget-grid" class="">
					<div class="row">
						<article class="col-sm-12">
							<div class="jarviswidget jarviswidget-color-darken" id="notice" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>신입사원 관리</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="text-align:center;">항목</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="i" begin="1" end="12" step="1">
														<tr>
															<td style="text-align:center;"><a href="javascript:moveNewEmplydListPage('${currYear }', '${i }');" >${currYear}년 ${i }월 신입사원</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->

		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />

		<%@ include file="/common/include/include-body.jsp" %>
		
		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
		 	 var _gaq = _gaq || [];
		 	 _gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
		 	 _gaq.push(['_trackPageview']);
		
		  	(function() {
		   		 var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		   		 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		   	 	var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  	})();
		  	
		  	$(document).ready(function(){
		  		pageSetUp();
		 	 });
		  	
		  	function moveNewEmplydListPage(year, month){
		  		var comSubmit = new ComSubmit();
	  			comSubmit.setUrl("/metelSOS/moveNewEmplydListPage.do");
	  			comSubmit.addParam('currYear', year);
	  			comSubmit.addParam('selectMonth', month);
	  			comSubmit.addParam('userType', 'engineer');
	  			comSubmit.addParam('menuTitle', encodeURI(year+"년 "+month+"월 신입사원 "));
	  			comSubmit.addParam('menuIcon', 'fa fa-lg fa-fw fa-male');
	  			comSubmit.getSubmit();
		  	}
		
		</script>

	</body>
</html>