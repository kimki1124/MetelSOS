<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
		<title>+++ 엔지니어 회원가입 +++</title>
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
</head>
<body id="login">
	
		<header id="header">
			<!--<span id="logo"></span>-->

			<div id="logo-group">
				<span id="logo"> <img src="/metelSOS/resources/img/logo.png"> </span>

				<!-- END AJAX-DROPDOWN -->
			</div>

			<!-- <span id="extr-page-header-space"> <span class="hidden-mobile hiddex-xs">Already registered?</span> <a href="login.html" class="btn btn-danger">Sign In</a> </span> -->

		</header>

		<div id="main" role="main">

			<!-- MAIN CONTENT -->
			<div id="content" class="container">

				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2"></div>
					<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
						<div class="well no-padding">

							<form action="/metelSOS/engineerRegister.do" id="smart-form-register" class="smart-form client-form"><!-- action="/metelSOS/register.do" -->
								<header>
									본사 엔지니어 회원가입
								</header>
									<input type="hidden" id="isValidateCheck" />
								<fieldset>
									<div class="row">
										<section class="col col-9">
											<label class="input"> <i class="icon-append fa fa-user"></i>
												<input type="text" name="engineerId" id="engineerId" placeholder="아이디">
												<b class="tooltip tooltip-bottom-right">아이디는 최대 20자까지 가능합니다.</b> </label>
										</section>
										<section class="col col-3">
											<button id="idValidate" class="btn btn-primary" style="padding:6px 22px 6px 22px; margin-left:-14px;">
												중복확인
											</button>
										</section>
									</div>
									
									<section>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="engineerPasswd" placeholder="비밀번호" id="engineerPasswd">
											<b class="tooltip tooltip-bottom-right">비밀번호는 영문과 숫자를 섞어 8~20자까지 가능합니다.</b> </label>
									</section>

									<section>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="engineerPasswdConfirm" placeholder="비밀번호 확인" id="engineerPasswdConfirm">
											<b class="tooltip tooltip-bottom-right">비밀번호를 한번 더 입력해주세요.</b> </label>
									</section>

									<section>
										<label class="input"> <i class="icon-append fa fa-envelope"></i>
											<input type="email" name="engineerEmail" id="engineerEmail" placeholder="이메일">
											<b class="tooltip tooltip-bottom-right">이메일은 계정을 인증할 때 필요합니다.</b> </label>
									</section>
									
									<section>
										<label class="select">
											<select name="engineerDept">
												<option value="default" selected="" disabled="">부서를 선택해 주세요.</option>
												<option value="제품지원부">제품지원부</option>
												<option value="플랫폼연구부">플랫폼연구부</option>
												<option value="기술연구부">기술연구부</option>
												<option value="사업수행부">사업수행부</option>
											</select> 
											<i></i> 
										</label>
									</section>
									
									<section>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="engineerName" placeholder="이름">
											<b class="tooltip tooltip-bottom-right">이름을 입력해 주세요.</b> </label>
									</section>
									
									<section>
										<label class="input"> <i class="icon-append fa fa-mobile" style="font-size:25px;"></i>
											<input type="text" name="engineerPhone" placeholder="전화번호">
											<b class="tooltip tooltip-bottom-right">통화 가능한 번호(- 제외)를 입력해 주세요.</b> </label>
									</section>
								</fieldset>
								<footer>
									<button id="cancel" class="btn btn-default">
										취소
									</button>
									<button type="submit" class="btn btn-primary">
										확인
									</button>
								</footer>

								<div class="message">
									<i class="fa fa-check"></i>
									<p>
										Thank you for your registration!
									</p>
								</div>
							</form>

						</div>
						<p class="note text-center">Copyright 2016. MetelSOS all rights reserved.</p>
					</div>
				</div>
			</div>

		</div>
		
		<div id="idValidateOK" title="idValidateOK">
			<p>
				사용하실 수 있는 아이디입니다.
			</p>
		</div>
		
		<div id="idValidateNO" title="idValidateNO">
			<p>
				사용하실 수 없는 아이디입니다.
			</p>
		</div>
		
		<div id="needIdValidate" title="needIdValidate">
			<p>
				아이디 중복확인을 해주세요.
			</p>
		</div>
		
		<div id="welcomeMsg" title="welcomeMsg">
			<p>
				회원가입을 축하합니다.<br />
				로그인 페이지로 이동합니다.
			</p>
		</div>
		
		<div id="failedRegister" title="failedRegister">
			<p>
				회원가입에 실패했습니다. <br />
				자세한 사항은 관리자에게 문의하세요.
			</p>
		</div>

		<!-- Modal -->
		<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">Terms & Conditions</h4>
					</div>
					<div class="modal-body custom-scroll terms-body">
			<br><br>

            <p><strong>By using this  WEBSITE TERMS AND CONDITIONS template document, you agree to the 
	 <a href="#">terms and conditions</a> set out on 
	 <a href="#">SmartAdmin.com</a>.  You must retain the credit 
	 set out in the section headed "ABOUT THESE WEBSITE TERMS AND CONDITIONS".  Subject to the licensing restrictions, you should 
	 edit the document, adapting it to the requirements of your jurisdiction, your business and your 
	 website.  If you are not a lawyer, we recommend that you take professional legal advice in relation to the editing and 
	 use of the template.</strong></p>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							Cancel
						</button>
						<button type="button" class="btn btn-primary" id="i-agree">
							<i class="fa fa-check"></i> I Agree
						</button>
						
						<button type="button" class="btn btn-danger pull-left" id="print">
							<i class="fa fa-print"></i> Print
						</button>
					</div>
				</div>/.modal-content
			</div>/.modal-dialog
		</div>/.modal -->

		<!--================================================== -->	

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
		<script src="/metelSOS/resources/js/plugin/pace/pace.min.js"></script>

	    <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script> if (!window.jQuery) { document.write('<script src="/metelSOS/resources/js/libs/jquery-2.1.1.min.js"><\/script>');} </script>

	    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script> if (!window.jQuery.ui) { document.write('<script src="/metelSOS/resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>

		<!-- IMPORTANT: APP CONFIG -->
		<script src="/metelSOS/resources/js/app.config.js"></script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->		
		<script src="/metelSOS/resources/js/bootstrap/bootstrap.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="/metelSOS/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
		<!-- JQUERY MASKED INPUT -->
		<script src="/metelSOS/resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
		
		<!-- JQUERY FORM -->
		<script src="/metelSOS/resources/js/plugin/jquery-form/jquery-form.min.js"></script>
		
		<!--[if IE 8]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->

		<!-- MAIN APP JS FILE -->
		<script src="/metelSOS/resources/js/app.min.js"></script>

		<script type="text/javascript">
			runAllForms();
			
			$(function() {
				//isValidateCheck 값 false로 설정
				$("#isValidateCheck").val(false);
				
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
				
				//중복확인 성공 다이얼로그 정의
				 $('#idValidateOK').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-check'></i>&nbsp;아이디 중복 확인</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$("#isValidateCheck").val(true);
								$('#idValidateOK').dialog("close");
								$("#engineerPasswd").focus();
								return false;
							}
						}]
				});
				 
				//중복확인 실패 다이얼로그 정의
				 $('#idValidateNO').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;아이디 중복 확인</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#idValidateNO').dialog("close");
								$("#engineerId").focus();
								return false;
							}
						}]
				});
				
				// register 폼 제출 시 중복확인 안했을 때 띄우는 경고 메시지 다이얼로그 정의
				 $('#needIdValidate').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;회원 가입</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#needIdValidate').dialog("close");
								$("#engineerId").focus();
								return false;
							}
						}]
				});
				
				//register 성공 후 웰컴메시지 다이얼로그 정의
				$('#welcomeMsg').dialog({
						autoOpen : false,
						width: 300,
						height:300,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-check'></i>&nbsp;회원 가입</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#welcomeMsg').dialog("close");
								//로그인 페이지로 이동
								document.location.href="/metelSOS/login.jsp";
							}
						}]
				});
				
				//register 실패 시 실패메시지 다이얼로그 정의
				 $('#failedRegister').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;회원 가입</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#failedRegister').dialog("close");
								return false;
							}
						}]
				});
				
				$("input[name=engineerId]").focus();
				
				//고객사 콤보박스 required validate
				$.validator.addMethod("valueNotEquals", function(value, element, arg){
					return value != null;
				}, "Value must not equal arg.");
				
				$("#idValidate").click(function(){
					var engineerId = $("#engineerId").val();
					
					$.ajax({
						url:'validateEngineerId.do',
						type:'post',
						dataType:"json",
						data:'engineerId='+engineerId,
						success:function(msg){
							if(msg.resultMsg == 'FAILED'){
								//아이디가 존재하면 
								$('#idValidateNO').dialog("open");
							}else{
								//아이디가 존재하지 않으면
								$('#idValidateOK').dialog("open");
							}
						}
					});
					
					return false;
				});
				
				// Validation
				$("#smart-form-register").validate({

					// Rules for form validation
					rules : {
						engineerId : {
							required : true
						},
						engineerEmail : {
							required : true,
							email : true
						},
						engineerPasswd : {
							required : true,
							minlength : 8,
							maxlength : 20
						},
						engineerPasswdConfirm : {
							required : true,
							minlength : 8,
							maxlength : 20,
							equalTo : '#engineerPasswd'
						},
						engineerName : {
							required : true
						},
						engineerDept : {
							valueNotEquals : 'default'
						},
						engineerPhone : {
							required : true,
							digits : true
						}
						
					},

					// Messages for form validation
					messages : {
						login : {
							required : 'Please enter your login'
						},
						engineerEmail : {
							required : '이메일 주소를 입력해주세요.',
							email : '이메일 형식을 지켜서 작성해주세요.(###@###.###)'
						},
						engineerPasswd : {
							required : '비밀번호를 입력해 주세요.'
						},
						engineerPasswdConfirm : {
							required : '비밀번호를 한번 더 입력해주세요.',
							equalTo : '비밀번호와 입력한 값이 틀립니다.'
						},
						engineerDept : {
							valueNotEquals : '부서를 선택해주세요.'
						},
						engineerPhone : {
							digits : '숫자만 입력해주세요.'
						}
					},
				});
				
				// register form submit
				$("#smart-form-register").submit(function(event){
					event.preventDefault();
					var postData = $(this).serializeArray();
					var formUrl = $(this).attr('action');
					var isValidateCheck = $("#isValidateCheck").val();
					
					if(isValidateCheck == 'false'){
						$('#needIdValidate').dialog("open");
						return false;
					}else{
						$.ajax({
							url:formUrl,
							type:'POST',
							dataType:"json",
							data: postData,
							success:function(msg){
								if(msg.resultMsg == 'SUCCESS'){
									$('#welcomeMsg').dialog("open");
								}else{
									$('#failedRegister').dialog("open");
								}
							}
						});
					}
					
				});
			});
			
			/* registerFormSubmit = function(){
				var isValidateCheck = $("#isValidateCheck").val();
				
				if(isValidateCheck == 'false'){
					$('#needIdValidate').dialog("open");
					return false;
				}else{
					return true;
				}
				//console.log(isValidateCheck);
				/* if(isValidateCheck){
					return true;
				} */
			//} */
		</script>

	</body>
</html>