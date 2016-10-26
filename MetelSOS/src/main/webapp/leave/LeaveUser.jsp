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
			//세션없으면 로그인 페이지로 이동
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId == 'null'){
				document.location.href="/metelSOS/login.jsp";
			}
			
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
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;회원탈퇴 실패</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#empty_dialog').dialog("close");
								$("#inputPasswd").focus();
								
								return false;
							}
						}]
					});
				 
				 $('#passwd_not_equal_dialog').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;회원탈퇴 실패</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#passwd_not_equal_dialog').dialog("close");
								$("#inputPasswd").focus();
								
								return false;
							}
						}]
					});
				 
				 $('#err_500_dialog').dialog({
						autoOpen:false,
						width:500,
						height:700,
						resizable:false,
						modal:true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;회원탈퇴</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#err_500_dialog').dialog("close");
								$("#inputPasswd").focus();
								return false;
							}
						}]
					});
				 
				 $('#success_delete_user_dialog').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-check'></i>&nbsp;회원탈퇴</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#success_delete_user_dialog').dialog("close");
								document.location.href="/metelSOS/login.jsp";
							}
						}]
					});
			});
		</script>
</head>
<body class="">
		<form id="leaveUserParamForm" name="leaveUserParamForm">
			<%-- <input type="hidden" id="userType" name="userType" value="${userType }"/> --%>
			<%-- <input type="hidden" id="userId" name="userId" value="${userId }" /> --%>
			<input type="hidden" id="userPasswd" name="userPasswd" value="${userPasswd }" />
		</form>

		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/engineer/EngineerLeftPanel.jsp" flush="false"/>
		
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

				
				<!-- col -->
			<div id="content">
				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h3 class="page-title txt-color-blueDark">
							
							<!-- PAGE HEADER -->
							<i class="${menuIcon} fa-fw"></i>
								${menuTitle} 
						</h3>
					</div>
				</div>
				<!-- end col -->
				<section id="widget-grid" class="">
					<div class="row">
						<article class="col-sm-12">
							<div class="alert alert-warning fade in">
								<i class="fa-fw fa fa-warning"></i>
								<strong>경고</strong> 회원 탈퇴는 신중하게 생각하세요.
							</div>
						</article>
					</div>
					
					<div class="row">
						<div class="col-sm-12">
							<p>
								<span class="label bg-color-darken txt-color-white">유의사항</span> 회원 탈퇴 시 다음과 같이 처리됩니다.
							</p>
							<p>
								1. 회원님이 담당자로 있었던 지원 사항에 대한 담당자 항목이 익명으로 처리됩니다.
							</p>
							<p>
								2. 회원님이 게시한 기술 자료의 작성자 항목이 익명으로 처리됩니다.
							</p>
							<p>
								3. 회원 탈퇴 시 개인 정보는 모두 삭제되며, 복구할 수 없습니다.
							</p>
							<br /><br />
							<p>
								<fieldset>
									<form action='/metelSOS/deleteUser.do' method='post' id="deleteUserForm">
										<section>
											<label class="label" style="font-size:100%; color:#000000;">비밀번호를 한번 더 입력해주세요.</label>
											<label class="input">
												<input type="hidden" id="userType" name="userType" value="${userType }"/>
												<input type="hidden" id="userId" name="userId" value="${userId }" />
												<input type="password" id="inputPasswd" name="inputPasswd" style="height:32px; padding-bottom:5px;">
												<button type="submit" class="btn btn-primary">
													확인
												</button>
											</label>
										</section>
									</form>
								</fieldset>
							</p>
						</div>
					</div>
				</section>
				
				
			</div>
			
			<!-- END #MAIN CONTENT -->

		</div>
		<!-- END #MAIN PANEL -->

		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		
		<div id="empty_dialog" title="empty dialog title">
			<p>
				<span>빈 칸을 입력해주세요.</span>
			</p>
		</div>
		
		<div id="passwd_not_equal_dialog">
			<p>
				<span>비밀번호가 맞지 않습니다.</span>
			</p>
		</div>
		
		<div id="err_500_dialog" title="err 500 dialog">
			<p>
				<span id="err-dialog-text"></span>
			</p>
		</div>
		
		<div id="success_delete_user_dialog">
			<p>
				<span>회원 탈퇴가 정상적으로 처리되었습니다. <br />
						로그인 화면으로 이동합니다.
				</span>
			</p>
		</div>

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
		  		$("#inputPasswd").focus();
		  		
		  		$(document).keydown(function(e){
	    			if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){
	        			if(e.keyCode === 8){   
	        				return false;
	        			}
	    			}
				});
		  		
		  		$("#deleteUserForm").submit(function(event){
		  			event.preventDefault();
					var postData = $(this).serializeArray();
					if($("#inputPasswd").val() == '' || $("#inputPasswd").val() == null){
			 			$('#empty_dialog').dialog("open");
			 			 return false;
			 		 }
			 		 
			 		 if($("#inputPasswd").val() != $("#userPasswd").val()){
			 			 $("#passwd_not_equal_dialog").dialog("open");
			 			 return false;
			 		 }
			 		 
			 		 var formUrl = $(this).attr('action');
			 		 $.ajax({
			 			 url:formUrl,
			 			 type:'POST',
			 			 dataType:'json',
			 			 data:postData,
			 			 success:function(msg){
			 				$('#success_delete_user_dialog').dialog("open"); 
			 			 },
			 			error:function(request,status,error){
			 				var errMsg = request.status+"\n"+error;
							$('#err-dialog-text').html(errMsg);
							$('#err_500_dialog').dialog("open");
			 			}
			 		 });
		  		});
		  		
		  	});
		</script>

	</body>
</html>