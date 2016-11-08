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
			//뒤로가기 막기
			history.pushState(null, null, location.href); 
			window.onpopstate = function(event) { 
				history.go(1); 
			}
			
			//세션없으면 로그인 페이지로 이동
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId == 'null'){
				document.location.href="/metelSOS/login.jsp";
			}
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/customer/CustomerLeftPanel.jsp" flush="false"/>
		
		<div id="main" role="main">
			<div id="ribbon">
				<ol class="breadcrumb">
					<li>Home</li>
					<c:forEach var="item" items="${breadcrumbList}" varStatus="status">
						<li>${item}</li>
					</c:forEach>
				</ol>
			</div>
			
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
						<div class="col-sm-6 col-md-6 col-lg-12">
							<div class="well well-sm well-light">
								<h3>METELSOS 사용 가이드
								<br>
								<small>처음 이용하시는 분은 이용하기 전에 한번씩 읽어보세요.</small></h3>
								<div id="guide-accordion">
									<div>
										<h4>지원 요청</h4>
										<div class="padding-10">
											<ul>
												<li>솔루션에 문제가 생기는 경우, 회의가 필요한 경우, 교육이 필요한 경우 이곳에서 온라인으로 지원을 요청할 수 있습니다.</li><br />
												<li>신청을 하게 되는 경우, 엔지니어에게 지원 요청이 왔다는 문자가 전송됩니다. 따로 엔지니어에게 연락을 하지 않아도 됩니다.</li><br />
												<li>엔지니어가 지원 요청을 받으면 사이트 담당자에게 연락을 합니다. 그 때 구체적인 지원 일정을 잡으시면 됩니다.</li><br />
												<li>지원 일정을 맞추면 담당 엔지니어가 일정에 맞춰서 사이트를 방문할 것입니다.</li>
											</ul>
										</div>
									</div>
				
									<div>
										<h4>지원 히스토리</h4>
										<div class="padding-10">
											<ul>
												<li>지원 히스토리 메뉴에서 그동안 지원받았던 히스토리를 볼 수 있습니다.</li><br />
												<li>이 메뉴를 통해 지원받았던 담당 엔지니어, 엔지니어가 남긴 코멘트 등을 확인할 수 있습니다.</li>
											</ul>
										</div>
									</div>
									<div>
										<h4>엔지니어 검색</h4>
										<div class="padding-10">
											<ul>
												<li>엔지니어 검색 메뉴로 지원이 가능한 엔지니어를 검색할 수 있습니다.</li><br />
												<li>각 엔지니어들의 일정을 확인할 수 있으며, 대략적인 지원 요청 날짜를 정하실 수 있습니다.</li><br />
												<li>물론 구체적인 지원 날짜는 엔지니어와의 연락을 통해 정하셔야 확실한 날짜에 지원받으실 수 있습니다.</li>
											</ul>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="row">
						<article class="col-sm-12">
							<div class="jarviswidget jarviswidget-color-darken" id="currNonCompleteSupport" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>최근 지원 요청사항</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<p style="color:#999;">최대 5개까지 표시됩니다.</p>
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:30%;">기술지원 명</th>
														<th style="width:20%;">고객사명</th>
														<th style="width:10%;">담당자</th>
														<th style="width:10%;">담당 엔지니어</th>
														<th style="width:20%;">접수일</th>
														<th style="width:10%;">상태</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${supportList}" varStatus="status">
														<tr>
															<td>${item.support_title }</td>
															<td>${item.company_name }</td>
															<td>${item.customer_name }</td>
															<td>${item.support_engineer }</td>
															<td>${item.support_accept_date }</td>
															<td>${item.support_state }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="jarviswidget jarviswidget-color-darken" id="currCompleteSupport" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>최근 지원 히스토리</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<p style="color:#999;">최대 5개까지 표시됩니다.</p>
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:30%;">기술지원 명</th>
														<th style="width:20%;">고객사명</th>
														<th style="width:10%;">담당자</th>
														<th style="width:10%;">담당 엔지니어</th>
														<th style="width:20%;">접수일</th>
														<th style="width:10%;">상태</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${supportCompleteList}" varStatus="status">
														<tr>
															<td>${item.support_title }</td>
															<td>${item.company_name }</td>
															<td>${item.customer_name }</td>
															<td>${item.support_engineer }</td>
															<td>${item.support_accept_date }</td>
															<td>${item.support_state }</td>
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
					<div class="row">
						<div class="col-sm-6 col-md-6 col-lg-12">
							<div class="well well-sm well-light">
								<h3>자주묻는 질문 Best 5
								<br>
								<small>고객님들께서 자주 묻는 질문들 중 가장 빈도가 높은 질문 5개입니다.</small></h3>
								<div id="qnabest-accordion">
									<c:forEach var="item" items="${qnaBest5List}" varStatus="status">
										<div>
											<h4 style="background-color:#d6dde7;">${item.qna_question }</h4>
											<div class="padding-10" style="background-color:#efe1b3;">
												${item.qna_answer }
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		<%@ include file="/common/include/include-body.jsp" %>

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
			  $("#left-panel nav ul li").first().addClass("active");
			  
			  $(document).keydown(function(e){   
	    			if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
	        			if(e.keyCode === 8){   
	        				return false;
	        			}
	    			}
				});
			  
			  var accordionIcons = {
			  		header: "fa fa-plus",    // custom icon class
				    activeHeader: "fa fa-minus" // custom icon class
			   };

			  $("#guide-accordion").accordion({
				autoHeight : false,
				heightStyle : "content",
				collapsible : true,
				animate : 300,
				icons: accordionIcons,
				header : "h4",
			  });
			  
			  $("#qnabest-accordion").accordion({
					autoHeight : false,
					heightStyle : "content",
					collapsible : true,
					animate : 300,
					icons: accordionIcons,
					header : "h4",
				  });
		  });
		
		</script>

	</body>
</html>