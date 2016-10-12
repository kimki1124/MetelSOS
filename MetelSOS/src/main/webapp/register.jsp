<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

							<form action="/metelSOS/register.do" onsubmit="return registerFormSubmit();" id="smart-form-register" class="smart-form client-form">
								<header>
									회원가입
								</header>
									<input type="hidden" id="isValidateCheck" />
								<fieldset>
									<div class="row">
										<section class="col col-9">
											<label class="input"> <i class="icon-append fa fa-user"></i>
												<input type="text" name="id" id="userId" placeholder="아이디">
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
											<input type="password" name="password" placeholder="비밀번호" id="password">
											<b class="tooltip tooltip-bottom-right">비밀번호는 영문과 숫자를 섞어 8~20자까지 가능합니다.</b> </label>
									</section>

									<section>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="passwordConfirm" placeholder="비밀번호 확인" id="passwordConfirm">
											<b class="tooltip tooltip-bottom-right">비밀번호를 한번 더 입력해주세요.</b> </label>
									</section>

									<section>
										<label class="input"> <i class="icon-append fa fa-envelope"></i>
											<input type="email" name="email" id="userEmail" placeholder="이메일">
											<b class="tooltip tooltip-bottom-right">이메일은 계정을 인증할 때 필요합니다.</b> </label>
									</section>
									
									<section>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="userName" placeholder="이름">
											<b class="tooltip tooltip-bottom-right">이름을 입력해 주세요.</b> </label>
									</section>
									
									<section>
										<label class="input"> <i class="icon-append fa fa-mobile" style="font-size:25px;"></i>
											<input type="text" name="userPhone" placeholder="전화번호">
											<b class="tooltip tooltip-bottom-right">통화 가능한 번호(- 제외)를 입력해 주세요.</b> </label>
									</section>
									
									<section>
										<label class="label">구분</label>
										<div class="inline-group">
											<label class="radio">
												<input type="radio" name="userCode" checked="checked" value="Head">
												<i></i>본사</label>
											<label class="radio">
												<input type="radio" name="userCode" value="Customer">
												<i></i>고객사</label>
										</div>
									</section>
									
									<section id="userDept" hidden>
										<label class="select">
											<select name="userDept">
												<option value="default" selected="" disabled="">고객사를 선택해 주세요.</option>
												<option value="mpss">국민안전처</option>
												<option value="hira">심사평가원</option>
												<option value="ecar">자동차관리시스템</option>
												<option value="songdo">송도 u-city</option>
											</select> 
											<i></i> 
										</label>
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
								$("#password").focus();
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
								$("#userId").focus();
								return false;
							}
						}]
				});
				
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
								$("#userId").focus();
								return false;
							}
						}]
				});
				 
				
				$("input[name=id]").focus();
				
				//고객사 콤보박스 required validate
				$.validator.addMethod("valueNotEquals", function(value, element, arg){
					return value != null;
				}, "Value must not equal arg.");
				
				$("#idValidate").click(function(){
					var userId = $("#userId").val();
					
					$.ajax({
						url:'validateId.do',
						type:'post',
						dataType:"json",
						data:'userId='+userId,
						success:function(msg){
							if(msg.resultMsg == 'SUCCESS'){
								//아이디가 존재하면 
								console.log('중복확인 성공');
								$('#idValidateNO').dialog("open");
							}else{
								//아이디가 존재하지 않으면
								console.log('중복확인 실패');
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
						id : {
							required : true
						},
						email : {
							required : true,
							email : true
						},
						password : {
							required : true,
							minlength : 8,
							maxlength : 20
						},
						passwordConfirm : {
							required : true,
							minlength : 8,
							maxlength : 20,
							equalTo : '#password'
						},
						userName : {
							required : true
						},
						userDept : {
							valueNotEquals : 'default'
						},
						userPhone : {
							required : true,
							digits : true
						}
						
					},

					// Messages for form validation
					messages : {
						login : {
							required : 'Please enter your login'
						},
						email : {
							required : '이메일 주소를 입력해주세요.',
							email : '이메일 형식을 지켜서 작성해주세요.(###@###.###)'
						},
						password : {
							required : '비밀번호를 입력해 주세요.'
						},
						passwordConfirm : {
							required : '비밀번호를 한번 더 입력해주세요.',
							equalTo : '비밀번호와 입력한 값이 틀립니다.'
						},
						userDept : {
							valueNotEquals : '고객사를 선택해주세요.'
						},
						userPhone : {
							digits : '숫자만 입력해주세요.'
						}
						/* firstname : {
							required : 'Please select your first name'
						},
						lastname : {
							required : 'Please select your last name'
						},
						gender : {
							required : 'Please select your gender'
						},
						terms : {
							required : 'You must agree with Terms and Conditions'
						} */
					},

					// Ajax form submition
					//submitHandler : function(form) {
						/* $(form).ajaxSubmit({
							success : function() {
								$("#smart-form-register").addClass('submited');
							}
						});  */
					//},  

					// Do not change code below
					// errorPlacement : function(error, element) {
					//	error.insertAfter(element.parent());
					//} 
				});
				
				//고객사를 선택하면 hidden인 소속 콤보박스가 show
				$("input[name=userCode]").change(function(){
					var radioValue = $(this).val();
					if(radioValue == 'Head'){
						$("#userDept").hide();
					}else{
						$("#userDept").show();
					}
				});
			});
			
			registerFormSubmit = function(){
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
			}
		</script>

	</body>
</html>