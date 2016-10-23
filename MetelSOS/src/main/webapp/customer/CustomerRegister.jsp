<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
		<title>+++ 고객 회원가입 +++</title>
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
		<script> if (!window.jQuery.ui) { document.write('<script src="/metelSOS/resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>
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
								$("#customerPasswd").focus();
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
								$("#customerId").focus();
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
								$("#customerId").focus();
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
				
				//500에러 메시지 다이얼로그 정의
				$('#customer_err_500_dialog').dialog({
					autoOpen:false,
					width:500,
					height:700,
					resizable:false,
					modal:true,
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;회원 가입</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#customer_err_500_dialog').dialog("close");
							$("#customerId").focus();
							return false;
						}
					}]
				});
			});
		
		</script>
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

							<form action="/metelSOS/customerRegister.do" id="smart-form-register" class="smart-form client-form"><!-- action="/metelSOS/register.do" -->
								<header>
									고객사 고객 회원가입
								</header>
									<input type="hidden" id="isValidateCheck" />
								<fieldset>
									<div class="row">
										<section class="col col-9">
											<label class="input"> <i class="icon-append fa fa-user"></i>
												<input type="text" name="customerId" id="customerId" placeholder="아이디">
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
											<input type="password" name="customerPasswd" placeholder="비밀번호" id="customerPasswd">
											<b class="tooltip tooltip-bottom-right">비밀번호는 영문과 숫자를 섞어 8~20자까지 가능합니다.</b> </label>
									</section>

									<section>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="customerPasswdConfirm" placeholder="비밀번호 확인" id="customerPasswdConfirm">
											<b class="tooltip tooltip-bottom-right">비밀번호를 한번 더 입력해주세요.</b> </label>
									</section>

									<section>
										<label class="input"> <i class="icon-append fa fa-envelope"></i>
											<input type="email" name="customerEmail" id="customerEmail" placeholder="이메일">
											<b class="tooltip tooltip-bottom-right">이메일은 계정을 인증할 때 필요합니다.</b> </label>
									</section>
									
									<section>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="customerName" placeholder="이름">
											<b class="tooltip tooltip-bottom-right">이름을 입력해 주세요.</b> </label>
									</section>
									
									<section>
										<label class="input"> <i class="icon-append fa fa-mobile" style="font-size:25px;"></i>
											<input type="text" name="customerPhone" placeholder="전화번호">
											<b class="tooltip tooltip-bottom-right">통화 가능한 번호(- 제외)를 입력해 주세요.</b> </label>
									</section>
									
									<section>
										<label class="select">
											<select name="companyName">
												<option value="default" selected="" disabled="">소속 고객사를 선택해 주세요.</option>
												<c:forEach var="item" items="${customerCompanyList }" varStatus="status">
													<option value="${item.company_name}">${item.company_name}</option>
												</c:forEach>
											</select> 
											<i></i> 
										</label>
									</section>
									
									<section>
										<label class="select">
											<select name="customerPosition">
												<option value="default" selected="" disabled="">직급을 선택해 주세요.</option>
												<option value="연구원">연구원</option>
												<option value="주임연구원">주임연구원</option>
												<option value="전임연구원">전임연구원</option>
												<option value="선임연구원">선임연구원</option>
												<option value="책임연구원">책임연구원</option>
												<option value="수석연구원">수석연구원</option>
												<option value="실장">실장</option>
											</select>
											<i></i>
										</label>
									</section>
								</fieldset>
								<footer>
									<span style="float:right;">
										<button type="submit" class="btn btn-primary" value="confirm">
											확인
										</button>
										<button type="reset" id="cancel" class="btn btn-default" value="cancel">
											취소
										</button>
									</span>
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
				<span>사용하실 수 있는 아이디입니다.</span>
			</p>
		</div>
		
		<div id="idValidateNO" title="idValidateNO">
			<p>
				<span>사용하실 수 없는 아이디입니다.</span>
			</p>
		</div>
		
		<div id="needIdValidate" title="needIdValidate">
			<p>
				<span>아이디 중복확인을 해주세요.</span>
			</p>
		</div>
		
		<div id="welcomeMsg" title="welcomeMsg">
			<p>
				<span>회원가입을 축하합니다.<br />
				로그인 페이지로 이동합니다.</span>
			</p>
		</div>
		
		<div id="failedRegister" title="failedRegister">
			<p>
				<span>회원가입에 실패했습니다. <br />
				자세한 사항은 관리자에게 문의하세요.</span>
			</p>
		</div>
		
		<div id="customer_err_500_dialog" title="customer err 500 dialog">
			<p>
				<span id="customer_err-dialog-text"></span>
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
				$("input[name=customerId]").focus();
				
				//취소 버튼 누르면 로그인 화면으로 이동
				$("#cancel").click(function(){
					document.location.href="/metelSOS/login.jsp";
				});
				
				//고객사 콤보박스 required validate
				$.validator.addMethod("valueNotEquals", function(value, element, arg){
					return value != null;
				}, "Value must not equal arg.");
				
				$("#idValidate").click(function(){
					var customerId = $("#customerId").val();
					
					$.ajax({
						url:'customerValidateId.do',
						type:'post',
						dataType:"json",
						data:'customerId='+customerId,
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
						customerId : {
							required : true
						},
						customerEmail : {
							required : true,
							email : true
						},
						customerPasswd : {
							required : true,
							minlength : 8,
							maxlength : 20
						},
						customerPasswdConfirm : {
							required : true,
							minlength : 8,
							maxlength : 20,
							equalTo : '#customerPasswd'
						},
						customerName : {
							required : true
						},
						companyName : {
							valueNotEquals : 'default'
						},
						customerPosition : {
							valueNotEquals : 'default'
						},
						customerPhone : {
							required : true,
							digits : true
						}
						
					},

					// Messages for form validation
					messages : {
						login : {
							required : 'Please enter your login'
						},
						customerEmail : {
							required : '이메일 주소를 입력해주세요.',
							email : '이메일 형식을 지켜서 작성해주세요.(###@###.###)'
						},
						customerPasswd : {
							required : '비밀번호를 입력해 주세요.'
						},
						customerPasswdConfirm : {
							required : '비밀번호를 한번 더 입력해주세요.',
							equalTo : '비밀번호와 입력한 값이 틀립니다.'
						},
						companyName : {
							valueNotEquals : '고객사를 선택해주세요.'
						},
						customerPosition : {
							valueNotEquals : '직급을 선택해 주세요.'
						},
						customerPhone : {
							digits : '숫자만 입력해주세요.'
						}
					},
					
					invalidHandler: function(form, validator) {
			             return false;
			        },
			        
			        submitHandler: function (form) {
			        	var isValidateCheck = $("#isValidateCheck").val();
						
						if(isValidateCheck == 'false'){
							$('#needIdValidate').dialog("open");
							return false;
						}else{
							$.ajax({
								url:'/metelSOS/customerRegister.do',
								type:'POST',
								dataType:"json",
								data: $(form).serializeArray(),
								success:function(msg){
									if(msg.resultMsg == 'SUCCESS'){
										$('#welcomeMsg').dialog("open");
									}else{
										$('#failedRegister').dialog("open");
									}
								},
								error:function(request,status,error){
									var errMsg = request.status+"\n"+request.responseText+"\n"+error;
									$('#customer_err-dialog-text').html(errMsg);
									$('#customer_err_500_dialog').dialog("open");
							    }
							});
						}
			            
			        }
				});
			});
		</script>

	</body>
</html>