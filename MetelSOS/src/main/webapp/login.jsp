<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title> 로그인</title>
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
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId != 'null'){
				var sessionType = '<%=session.getAttribute("SESSION_LOGIN_USER_TYPE")%>';
				if(sessionType == 'ENGINEER'){
					document.location.href="/metelSOS/EngineerMain.jsp";
				}else{
					document.location.href="/metelSOS/CustomerMain.jsp";
				}
			}
		</script>
</head>
<body>
		<header id="header">

			<div id="logo-group">
				<span id="logo"> <img src="/metelSOS/resources/img/logo.png" alt="MetelSOS"> </span>
			</div>

			<span id="extr-page-header-space" style="line-height:40px;"> <span class="hidden-mobile hidden-xs">계정이 없으신가요?</span> <button id="register" class="btn btn-danger">회원가입</button></span>

		</header>

		<div id="main" role="main">

			<!-- MAIN CONTENT -->
			<div id="content" class="container">

				<div class="row" style="margin-left:200px;">
					<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
						<div class="well no-padding">
							<!-- ENGINEER login form START -->
							<form id="user-type-form" class="smart-form client-form">
								<header>
									로그인
								</header>
								<fieldset style="padding:10px 14px 0px;">
									<section>
											<div class="inline-group">
												<label class="radio">
													<input type="radio" name="userCode" checked="checked" value="Engineer">
													<i></i>본사</label>
												<label class="radio">
													<input type="radio" name="userCode" value="Customer">
													<i></i>고객사</label>
											</div>
									</section>
								</fieldset>
							</form>
							<form action="/metelSOS/login.do" id="engineer-login-form" class="smart-form client-form" method="post">
								<fieldset style="padding:0px 14px 5px;">
									<section>
										<input type="hidden" name="userType" value="Engineer" />
									</section>
									<section>
										<label class="label">ID</label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="engineerId" id="engineerId">
											<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> 아이디를 입력해주세요.</b></label>
									</section>

									<section>
										<label class="label">Password</label>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="engineerPasswd" id="engineerPasswd">
											<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 비밀번호를 입력해주세요</b> </label>
										<div class="note">
											<a href="forgotpassword.html">비밀번호를 잊어버렸습니까?</a>
										</div>
									</section>

									<section>
										<label class="checkbox">
											<input type="checkbox" name="remember" checked="true">
											<i></i>로그인 상태 유지하기</label>
									</section>
								</fieldset>
								<footer>
									<button type="submit" class="btn btn-primary">
										로그인
									</button>
									<!-- <input type="button" class="btn btn-primary" id="loginButton" value="로그인" /> -->
								</footer>
							</form>
							<!-- ENGINEER login form END -->
							
							<!-- CUSTOMER login form START -->
							<form action="/metelSOS/login.do" id="customer-login-form" class="smart-form client-form" method="post" hidden>
								<fieldset style="padding:0px 14px 5px;">
									<section>
										<input type="hidden" name="userType" value="Customer" />
									</section>
									
									<section>
										<label class="label">ID</label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="customerId" id="customerId">
											<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> 아이디를 입력해주세요.</b></label>
									</section>

									<section>
										<label class="label">Password</label>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="customerPasswd" id="customerPasswd">
											<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 비밀번호를 입력해주세요</b> </label>
										<div class="note">
											<a href="forgotpassword.html">비밀번호를 잊어버렸습니까?</a>
										</div>
									</section>

									<section>
										<label class="checkbox">
											<input type="checkbox" name="remember" checked="true">
											<i></i>로그인 상태 유지하기</label>
									</section>
								</fieldset>
								<footer>
									<button type="submit" class="btn btn-primary">
										로그인
									</button>
									<!-- <input type="button" class="btn btn-primary" id="loginButton" value="로그인" /> -->
								</footer>
							</form>
							<!-- CUSTOMER login form END -->

						</div>
					</div>
				</div>
			</div>

		</div>
		
		<div id="empty_dialog" title="empty dialog title">
			<p>
				빈 칸을 입력해주세요.
			</p>
		</div>
		
		<script>
		$(document).ready(function(){
			$("#engineerId").focus();
			
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
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;로그인 실패</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#empty_dialog').dialog("close");
							var radioValue = $("input[name=userCode]").val();
							if(radioValue == 'Engineer'){
								if($("#engineerId").val() == ''){
									$("#engineerId").focus();
								}else{
									$("#engineerPasswd").focus();
								}
							}else{
								if($("#customerId").val() == ''){
									$("#customerId").focus();
								}else{
									$("#customerPasswd").focus();
								}
							}
							
							return false;
						}
					}]
				});
				
				//회원가입 버튼 클릭 이벤트 처리
				$("#register").click(function(){
					document.location.href="/metelSOS/SelectRegisterType.jsp";
				});
				
				//본사, 고객사 라디오버튼 이벤트
				$("input[name=userCode]").change(function(){
					var radioValue = $(this).val();
					if(radioValue == 'Engineer'){
						$("#customer-login-form").hide();
						$("#engineer-login-form").show();
						$("#engineerId").focus();
					}else{
						$("#engineer-login-form").hide();
						$("#customer-login-form").show();
						$("#customerId").focus();
					}
				});
				
				//엔지니어 로그인 버튼 ajax 이벤트 처리
				$("#engineer-login-form").submit(function(event){
					event.preventDefault();
					var postData = $(this).serializeArray();
					var formUrl = $(this).attr('action');
					$.ajax({
						url:formUrl,
						type:'POST',
						dataType:'json',
						data:postData,
						success:function(msg){
							if(msg.resultMsg == 'SUCCESS'){
								//로그인 성공
								document.location.href="/metelSOS/pageMove.do?userType=engineer&menuTitle=EngineerMain&menuIcon=fa fa-lg fa-fw fa-home";
							}else{
								//로그인 실패
							}
						}
					});
				});
				
				//고객 로그인 버튼 ajax 이벤트 처리
				$("#customer-login-form").submit(function(event){
					event.preventDefault();
					var postData = $(this).serializeArray();
					var formUrl = $(this).attr('action');
					$.ajax({
						url:formUrl,
						type:'POST',
						dataType:'json',
						data:postData,
						success:function(msg){
							if(msg.resultMsg == 'SUCCESS'){
								//로그인 성공
								document.location.href="/metelSOS/pageMove.do?userType=customer&menuTitle=CustomerMain&menuIcon=fa fa-lg fa-fw fa-home";
							}else{
								//로그인 실패
							}
						}
					});
				});
				
		});
		</script>
</body>
</html>