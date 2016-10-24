<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
		<title> 아이디 찾기 결과</title>
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
					<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4" style="margin-left:390px; width:421px;">
						<div class="well no-padding">
							<form action="/metelSOS/findPasswdPageMove.do" id="findId-result-form" class="smart-form client-form" method="post">
								<header>
									아이디 찾기 결과
								</header>
								<fieldset style="padding:10px 14px 0px;">
									<section>
										<input type="hidden" name="userType" value="${userType }">
									</section>
									<section>
										<label class="label">다음 정보로 가입된 아이디가 총 ${userCount}개 있습니다.</label>
										<br /><br />
											<table class="table table-bordered" style="border:0; margin-left:-14px;">
												<c:choose>
													<c:when test="${userType == 'Engineer' }">
														<c:forEach var="item" items="${findUserList }" varStatus="status">
															<tbody style="border-top:0;">
																<tr>
																	<td style="padding-top:33px; border:0;">
																		<!-- form input value set -->
																		<input type="radio" name="userId" value="${item.engineer_id }">
																		<input type="hidden" name="userName" value="${item.engineer_name }">
																		<input type="hidden" name="userEmail" value="${item.engineer_email }">
																	</td>
																	<td style="padding-left:50px; border:0;">
																		<!-- 아이디, 이름, 이메일 정보 -->
																		<ul class="error-search text-left font-md" style="color:#000000;">
							            									<li style="width:300px;"><small>아이디 : ${item.engineer_id }</small></li>
							           										<li style="width:300px;"><small>이름 : ${item.engineer_name }</small></li>
							           										<li style="width:300px;"><small>이메일 : ${item.engineer_email }</small></li>
							        									</ul>
																	</td>
																</tr>
															</tbody>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:forEach var="item" items="${findUserList }" varStatus="status">
															<tbody style="border-top:0;">
																<tr>
																	<td style="padding-top:33px; border:0;">
																		<!-- form input value set -->
																		<input type="radio" name="userId" value="${item.customer_id }">
																		<input type="hidden" name="userName" value="${item.customer_name }">
																		<input type="hidden" name="userEmail" value="${item.customer_email }">
																	</td>
																	<td style="padding-left:50px; border:0;">
																		<!-- 아이디, 이름, 이메일 정보 -->
																		<ul class="error-search text-left font-md" style="color:#000000;">
							            									<li style="width:300px;"><small>아이디 : ${item.customer_id }</small></li>
							           										<li style="width:300px;"><small>이름 : ${item.customer_name }</small></li>
							           										<li style="width:300px;"><small>이메일 : ${item.customer_email }</small></li>
							        									</ul>
																	</td>
																</tr>
															</tbody>
														</c:forEach>
													</c:otherwise>
												</c:choose>
												
											</table>
									</section>
								</fieldset>
								<footer>
									<span style="float:right;">
										<button type="reset" class="btn btn-primary" value="confirm">
											로그인
										</button>
										<button type="submit" id="cancel" class="btn btn-danger" disabled="true"  value="cancel">
											비밀번호 찾기
										</button>
									</span>
								</footer>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
		
		<script>
			$(document).ready(function(){
				//라디오버튼 check 시 tr에 success 클래스 부여
				$('input[type=radio][name=userId]').change(function() {
					$('tr').removeClass('success');
					$(this).parents('tr').addClass('success');
					$('#cancel').removeAttr('disabled');
			    });
				
				//비밀번호 찾기 버튼 클릭 이벤트
				/* $('#findId-result-form').submit(function(event){
					event.preventDefault();
					var postData = $(this).serializeArray();
					console.log(postData);
				}); */
				
				//로그인 버튼 클릭 이벤트
				$('button[type=reset]').click(function(){
					document.location.href="/metelSOS/login.jsp";
				});
				
			});
		</script>
</body>
</html>