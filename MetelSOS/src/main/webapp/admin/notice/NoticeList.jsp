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
									<h2>공지사항 관리</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:25%;">제목</th>
														<th style="width:5%; text-align:center;">파일</th>
														<th style="width:25%;">작성자</th>
														<th style="width:25%;">작성일</th>
														<th style="width:10%;">조회수</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${noticeList}" varStatus="status">
														<tr>
															<td>${status.count }</td>
															<td><a href="javascript:moveNoticeDetailPage('${item.notice_num }');">${item.notice_title }</a></td>
															<td style="text-align:center;"><c:if test="${item.has_file == 'Y' }">
																	<i class="fa fa-file fa-lg" aria-hidden="true"></i>
																</c:if></td>
															<td>${item.notice_author }</td>
															<td>${item.notice_date }</td>
															<td>${item.notice_hit }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="widget-body" style="min-height:40px;">
										<button id="moveWriteNoticePage" class="btn btn-success">새 공지 작성</button>
										<button id="moveMain" class="btn btn-success" style="float:right;">메인으로</button>
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
		  		
		  		$("#moveMain").click(function(e){
		  			e.preventDefault();
		  			document.location.href="/metelSOS/leftMenuPageMove.do?userType=engineer&menuTitle=EngineerMain&menuIcon=fa fa-lg fa-fw fa-info";
		  		});
		  		
		  		$("#moveWriteNoticePage").click(function(e){
		  			e.preventDefault();
		  			var comSubmit = new ComSubmit();
		  			comSubmit.setUrl("/metelSOS/writeNoticePageMove.do");
		  			comSubmit.addParam('userName', encodeURI('<%=session.getAttribute("SESSION_LOGIN_USER_NAME") %>'));
		  			comSubmit.addParam('userType', 'engineer');
		  			comSubmit.addParam('menuTitle', encodeURI('공지사항 작성'));
		  			comSubmit.addParam('menuIcon', 'fa fa-lg fa-fw fa-pencil-square-o');
		  			comSubmit.getSubmit();
		  		});
		 	 });
		  	
		  	function moveNoticeDetailPage(notice_num){
		  		document.location.href="/metelSOS/moveNoticeDetailPage.do?notice_num="+notice_num;
		  	}
		
		</script>

	</body>
</html>