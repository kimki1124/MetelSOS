<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title> 비밀번호 찾기</title>
		<meta name="description" content="">
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<!-- #CSS Links -->
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-production-plugins.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-skins.min.css">

		<!-- SmartAdmin RTL Support -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-rtl.min.css"> 
		
		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/demo.min.css">

		<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->
		<!-- #GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
		
		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script> if (!window.jQuery) { document.write('<script src="/metelSOS/resources/js/libs/jquery-2.1.1.min.js"><\/script>');} </script>
		
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			if (!window.jQuery.ui) {
				document.write('<script src="/metelSOS/resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
			}
		</script>
		
		<!-- IMPORTANT: APP CONFIG -->
		<script src="/metelSOS/resources/js/app.config.js"></script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->		
		<script src="/metelSOS/resources/js/bootstrap/bootstrap.min.js"></script>
		
		<!--[if IE 8]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->

		<!-- Demo purpose only -->
		<script src="/metelSOS/resources/js/demo.min.js"></script>

		<!-- MAIN APP JS FILE -->
		<script src="/metelSOS/resources/js/app.min.js"></script>
		
		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
		<script src="/metelSOS/resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> 
		
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
	
		<!-- Voice command : plugin -->
		<script src="/metelSOS/resources/js/speech/voicecommand.min.js"></script>

		<!-- SmartChat UI : plugin -->
		<script src="/metelSOS/resources/js/smart-chat-ui/smart.chat.ui.min.js"></script>
		<script src="/metelSOS/resources/js/smart-chat-ui/smart.chat.manager.min.js"></script>
		
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="/metelSOS/resources/js/plugin/pace/pace.min.js"></script>
		<script>
			$(document).ready(function(){
				//다이얼로그 title에 html을 적용하기 위한 코드
				 $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
						_title : function(title) {
							if (!this.options.title) {
								title.html("&#160;");
							} else {
								title.html(this.options.title);
							}
						}
					}));  
				
				 $('#empty_dialog').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;비밀번호 찾기 실패</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#empty_dialog').dialog("close");
								if($("#userId").val() == ''){
									$("#userId").focus();
								}else if($("#userName").val() == ''){
									$("#userName").focus();
								}else{
									$("#userEmail").focus();
								}
								
								return false;
							}
						}]
					});
			});
		</script>
</head>
<body>
		<header id="header">

			<div id="logo-group">
				<span id="logo"> <img src="/metelSOS/resources/img/logo.png" alt="MetelSOS"> </span>
			</div>
		</header>

		<div id="main" role="main" style="margin-left:0px;">

			<!-- MAIN CONTENT -->
			<div id="content" class="container">

				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4" style="margin-left:390px;">
						<div class="well no-padding">
							<form id="user-type-form" class="smart-form client-form">
								<header>
									비밀번호 찾기
								</header>
								<fieldset style="padding:10px 14px 0px;">
									<section>
											<div class="inline-group">
											<c:choose>
												<c:when test="${userType  == 'Customer'}">
													<label class="radio">
													<input type="radio" name="userCode" value="Engineer">
													<i></i>본사</label>
													<label class="radio">
													<input type="radio" name="userCode" checked="checked" value="Customer">
													<i></i>고객사</label>
												</c:when>
												<c:otherwise>
													<label class="radio">
													<input type="radio" name="userCode" checked="checked" value="Engineer">
													<i></i>본사</label>
													<label class="radio">
													<input type="radio" name="userCode" value="Customer">
													<i></i>고객사</label>
												</c:otherwise>
											</c:choose>
											</div>
									</section>
								</fieldset>
							</form>
							<form action="/metelSOS/sendTempPasswd.do" onsubmit="return emptyCheck();" id="find-id-form" class="smart-form client-form" method="post">
								<fieldset style="padding:0px 14px 5px;">
									<section>
										<input type="hidden" name="userType" value="Engineer" />
									</section>
									<section>
										<label class="label">아이디</label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<c:choose>
												<c:when test="${userId != '' || userId ne null }">
													<input type="text" name="userId" id="userId" value="${userId }">
												</c:when>
												<c:otherwise>
													<input type="text" name="userId" id="userId">
												</c:otherwise>
											</c:choose>
											<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> 아이디를 입력해주세요.</b></label>
									</section>
									
									<section>
										<label class="label">이름</label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<c:choose>
												<c:when test="${userName != '' || userName ne null }">
													<input type="text" name="userName" id="userName" value="${userName }">
												</c:when>
												<c:otherwise>
													<input type="text" name="userName" id="userName">
												</c:otherwise>
											</c:choose>
											<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> 이름을 입력해주세요.</b></label>
									</section>

									<section>
										<label class="label">Email</label>
										<label class="input"> <i class="icon-append fa fa-envelope"></i>
											<c:choose>
												<c:when test="${userEmail != '' || userEmail ne null }">
													<input type="text" name="userEmail" id="userEmail" value="${userEmail }">
												</c:when>
												<c:otherwise>
													<input type="text" name="userEmail" id="userEmail">
												</c:otherwise>
											</c:choose>
											<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 이메일을 입력해주세요</b> </label>
									</section>
								</fieldset>
								<footer>
									<span style="float:right;">
										<button type="submit" class="btn btn-primary">
											비밀번호 찾기
										</button>
										<button type="reset" id="cancel" class="btn btn-default">
											취소
										</button>
									</span>
								</footer>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
		
		<div id="empty_dialog" title="empty dialog title">
			<p>
				<span>빈 칸을 입력해주세요.</span>
			</p>
		</div>

		<script>
		function emptyCheck(){
			if($("#userId").val() == '' || $("#userName").val() == '' || $("#userEmail").val() == ''){
				$('#empty_dialog').dialog("open");
				return false;
			}
			
			return true;
		};
		
		$(document).ready(function(){
			$("#userId").focus();
			
			//취소 버튼 누르면 로그인 화면으로 이동
			$("#cancel").click(function(){
				document.location.href="/metelSOS/login.jsp";
			});
		});
		</script>
</body>
</html>