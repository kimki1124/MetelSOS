<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>+++  MetelSOS  +++</title>
		<meta name="description" content="">
		<meta name="author" content="">
			
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-production-plugins.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-skins.min.css">

		<!-- SmartAdmin RTL Support  -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-rtl.min.css">

		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/demo.min.css">
		
		<!-- FAVICONS -->
		<link rel="shortcut icon" href="/metelSOS/resources/img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="/metelSOS/resources/img/favicon/favicon.ico" type="image/x-icon">

		<!-- #GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script>
			if (!window.jQuery) {
				document.write('<script src="/metelSOS/resources/js/libs/jquery-2.1.1.min.js"><\/script>');
			}
		</script>

		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			if (!window.jQuery.ui) {
				document.write('<script src="/metelSOS/resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
			}
		</script>
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
									<h2>공지사항</h2>
									&nbsp;&nbsp;<a class="btn btn-success btn-xs" href="javascript:moveNoticePage();" style="margin-top:5px;"><i class="fa fa-gear"></i> &nbsp;&nbsp;관리</a>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
										<div class="table-responsive">
											<table class="table table-bordered table-striped">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:40%;">제목</th>
														<th style="width:25%;">작성자</th>
														<th style="width:25%;">작성일</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Row 1</td>
														<td>Row 2</td>
														<td>Row 3</td>
														<td>Row 4</td>
													</tr>
													<tr>
														<td>Row 1</td>
														<td>Row 2</td>
														<td>Row 3</td>
														<td>Row 4</td>
													</tr>
													<tr>
														<td>Row 1</td>
														<td>Row 2</td>
														<td>Row 3</td>
														<td>Row 4</td>
													</tr>
													<tr>
														<td>Row 1</td>
														<td>Row 2</td>
														<td>Row 3</td>
														<td>Row 4</td>
													</tr>
													<tr>
														<td>Row 1</td>
														<td>Row 2</td>
														<td>Row 3</td>
														<td>Row 4</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="jarviswidget jarviswidget-color-darken" id="month_support" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
									<h2>월별 유지보수 지원사항 통계</h2>
								</header>
								<div class="no-padding">
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
											<div id="normal-bar-graph" class="chart no-padding"></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 show-stats">
											<div class="row">
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12" style="margin-top:25px; margin-bottom:35px;">
													<strong>10월 유지보수 지원 현황</strong>
												</div>
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12"> <span class="text"> <strong>접수량</strong> <span class="pull-right"><strong>78/100</strong></span> </span>
													<div class="progress">
														<div class="progress-bar bg-color-blueDark" style="width: 65%;"></div>
													</div> 
												</div>
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12"> <span class="text"> <strong>지원량</strong> <span class="pull-right"><strong>50/100</strong></span> </span>
													<div class="progress">
														<div class="progress-bar bg-color-blue" style="width: 34%;"></div>
													</div> 
												</div>
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12"> <span class="text"> <strong>미지원량</strong> <span class="pull-right"><strong>28/100</strong></span> </span>
													<div class="progress">
														<div class="progress-bar bg-color-greenLight" style="width: 84%;"></div>
													</div> 
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</article>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="well well-light">
								<h1>10월 우수사원, <small>4명
									<a class="btn btn-success" href="#"><i class="fa fa-gear"></i> &nbsp;&nbsp;관리</a>
								</h1>
								<div class="row">
									<div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-success pricing-big">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	사업수행부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>김기범 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                         감사합니다
						                </div>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-teal pricing-big">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	사업수행부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>한진웅 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                        감사합니다
						                </div>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary pricing-big">
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	사업수행부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>김준호 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                         감사합니다
						                </div>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-darken pricing-big">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	플랫폼연구부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>장준환 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                         감사합니다
						                </div>
						            </div>
						        </div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="well well-light">
								<h1>10월 신입사원, <small>4명</small>
									<a class="btn btn-success" href="javascript:void(0);"><i class="fa fa-gear"></i> &nbsp;&nbsp;관리</a>
								</h1>
								<div class="row">
									<div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-success pricing-big">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	사업수행부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>김기범 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                   	 열심히 하겠습니다
						                </div>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-teal pricing-big">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	사업수행부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>한진웅 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                        열심히 하겠습니다
						                </div>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-primary pricing-big">
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	사업수행부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>김준호 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                         열심히 하겠습니다
						                </div>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-darken pricing-big">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	플랫폼연구부
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>장준환 연구원</strong></h1>
						                    </div>
											<div class="price-features">
												<i class="fa fa-user" style="font-size:190px;"></i>
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                         열심히 하겠습니다
						                </div>
						            </div>
						        </div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->

		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>

		<!--================================================== -->

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->
		

		<!-- IMPORTANT: APP CONFIG -->
		<script src="/metelSOS/resources/js/app.config.js"></script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
		<script src="/metelSOS/resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> 

		<!-- BOOTSTRAP JS -->
		<script src="/metelSOS/resources/js/bootstrap/bootstrap.min.js"></script>

		<!-- CUSTOM NOTIFICATION -->
		<script src="/metelSOS/resources/js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="/metelSOS/resources/js/smartwidgets/jarvis.widget.min.js"></script>

		<!-- EASY PIE CHARTS -->
		<script src="/metelSOS/resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

		<!-- SPARKLINES -->
		<script src="/metelSOS/resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="/metelSOS/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<!-- JQUERY MASKED INPUT -->
		<script src="/metelSOS/resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

		<!-- JQUERY SELECT2 INPUT -->
		<script src="/metelSOS/resources/js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="/metelSOS/resources/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

		<!-- browser msie issue fix -->
		<script src="/metelSOS/resources/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

		<!-- FastClick: For mobile devices -->
		<script src="/metelSOS/resources/js/plugin/fastclick/fastclick.min.js"></script>

		<!-- MAIN APP JS FILE -->
		<script src="/metelSOS/resources/js/app.min.js"></script>

		<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
		<!-- Voice command : plugin -->
		<script src="/metelSOS/resources/js/speech/voicecommand.min.js"></script>

		<!-- SmartChat UI : plugin -->
		<script src="/metelSOS/resources/js/smart-chat-ui/smart.chat.ui.min.js"></script>
		<script src="/metelSOS/resources/js/smart-chat-ui/smart.chat.manager.min.js"></script>
		
		<!-- PAGE RELATED PLUGIN(S) -->
		
		<!-- Flot Chart Plugin: Flot Engine, Flot Resizer, Flot Tooltip -->
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.time.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>
		
		<!-- Vector Maps Plugin: Vectormap engine, Vectormap language -->
		<script src="/metelSOS/resources/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>
		
		<!-- Full Calendar -->
		<script src="/metelSOS/resources/js/plugin/moment/moment.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/fullcalendar/jquery.fullcalendar.min.js"></script>
		
		<!-- Morris Chart Dependencies -->
		<script src="/metelSOS/resources/js/plugin/morris/raphael.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/morris/morris.min.js"></script>
		
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
		  
		  	function moveNoticePage(){
		  		var form = document.noticePageParamForm;
		  		form.userType.value = "engineer";
		  		form.menuTitle.value = encodeURI("공지사항 관리");
		  		form.menuIcon.value = "fa fa-lg fa-fw fa-bell";
		  		form.action = "/metelSOS/noticePageMove.do";
		  		form.method = "get";
		  		form.submit();
		  	}
		  	
		  	$(document).ready(function(){
		  		$(document).keydown(function(e){   
	    			if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
	        			if(e.keyCode === 8){   
	        				return false;
	        			}
	    			}
				});
		  		
		  		$("#left-panel nav ul li").first().addClass("active");
		  		
		  		pageSetUp();
		  		
		  		if ($('#normal-bar-graph').length) {

					Morris.Bar({
						element : 'normal-bar-graph',
						data : [{
							period : '2016-05',
							접수량 : 78,
							지원량 : 40,
							미지원량 : 38
						}, {
							period : '2016-06',
							접수량 : 35,
							지원량 : 24,
							미지원량 : 11
						}, {
							period : '2016-07',
							접수량 : 62,
							지원량 : 36,
							미지원량 : 26
						}, {
							period : '2016-08',
							접수량 : 48,
							지원량 : 48,
							미지원량 : 0
						}, {
							period : '2016-09',
							접수량 : 37,
							지원량 : 13,
							미지원량 : 24
						}],
						xkey : 'period',
						ykeys : ['접수량', '지원량', '미지원량'],
						labels : ['접수량', '지원량', '미지원량'],
						hideHover : 'auto'
					});

				}
		 	 });
		
		</script>

	</body>
</html>