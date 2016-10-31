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
				
				 $('#curr_new_equal_dialog').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;개인정보 수정 실패</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#curr_new_equal_dialog').dialog("close");
								$("#currPasswd").focus();
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
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;개인정보 수정 실패</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#err_500_dialog').dialog("close");
								$("#currPasswd").focus();
								return false;
							}
						}]
					});
				 
				 $('#failed_update_info').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;개인정보 수정 실패</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#failed_update_info').dialog("close");
								location.reload();
							}
						}]
					});
				 
				 $('#success_update_info').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-check'></i>&nbsp;개인정보 수정 실패</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#success_update_info').dialog("close");
								$('#myProfileParamForm').attr({action:'/metelSOS/profilePageMove.do', method:'get'}).submit();
							}
						}]
					});
			});
		</script>
</head>
<body class="">
		<form id="myProfileParamForm" name="myProfileParamForm">
			<input type="hidden" id="userType" name="userType" />
			<input type="hidden" id="userId" name="userId" />
			<input type="hidden" id="menuTitle" name="menuTitle" />
			<input type="hidden" id="menuIcon" name="menuIcon" />
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
						<article class="col-sm-12 col-md-12 col-lg-6">
							<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0">
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>개인정보 </h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
									</div>
									
									<div class="widget-body no-padding">
										<form id="smart-form" class="smart-form">
											<input type="hidden" id="engineerId" name="engineerId" value="${engineerVo.engineer_id }">
											<fieldset>
												<section>
													<label class="label">아이디</label>
													<label class="input state-disabled">
														<input type="text" disabled="disabled" value="${engineerVo.engineer_id }">
													</label>
												</section>
												<section>
													<label class="label">비밀번호</label>
													<label class="input state-disabled">
														<input type="password" id="engineerPasswd" name="engineerPasswd" disabled="disabled" value="${engineerVo.engineer_passwd }">
													</label>
												</section>
												<section style="display:none;">
													<label class="label">기존 비밀번호</label>
													<label class="input">
														<input type="password" id="currPasswd" name="currPasswd" >
													</label>
												</section>
												<section style="display:none;">
													<label class="label">새 비밀번호</label>
													<label class="input">
														<input type="password" id="newPasswd" name="newPasswd" >
													</label>
												</section>
												<section style="display:none;">
													<label class="label">새 비밀번호 확인</label>
													<label class="input">
														<input type="password" id="newPasswdConfirm" name="newPasswdConfirm" >
													</label>
												</section>
												<section>
													<label class="label">이름</label>
													<label class="input state_disabled">
														<input type="text" disabled="disabled" value="${engineerVo.engineer_name }">
													</label>
												</section>
												<section>
													<label class="label">부서</label>
													<label class="select state-disabled">
														<select class="input-sm" name="engineerDept" id="engineerDept" disabled="disabled">
															<option value="default" selected="" disabled="">부서를 선택해 주세요.</option>
															<option value="제품지원부">제품지원부</option>
															<option value="플랫폼연구부">플랫폼연구부</option>
															<option value="기술연구부">기술연구부</option>
															<option value="사업수행부">사업수행부</option>
														</select> <i></i> </label>
												</section>
												<section>
													<label class="label">직급</label>
													<label class="select state-disabled">
														<select class="input-sm" name="engineerPosition" id="engineerPosition" disabled="disabled">
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
												<section>
													<label class="label">이메일</label>
													<label class="input state_disabled">
														<input type="text" name="engineerEmail" id="engineerEmail" disabled="disabled" value="${engineerVo.engineer_email }">
													</label>
												</section>
												<section>
													<label class="label">전화번호</label>
													<label class="input state_disabled">
														<input type="text" name="engineerPhone" id="engineerPhone" disabled="disabled" value="${engineerVo.engineer_phone }">
													</label>
												</section>
											</fieldset>
											<footer>
												<span style="float:right;">
												<button type="button" id="update" class="btn btn-primary">
													개인정보 수정
												</button>
												<button type="button" id="leave" class="btn btn-danger">
													회원탈퇴
												</button>
												<button type="submit" id="updateConfirm" class="btn btn-danger" style="display:none;">
													수정
												</button>
												<button type="button" id="cancel" class="btn btn-default" style="display:none;">
													취소
												</button>
												</span>
											</footer>
										</form>
									</div>
								</div>
							</div>
						</article>
					</div>
					<div class="row">
						<article class="col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-1">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>최근 담당 유지보수 지원 사항</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->

									</div>
									<div class="widget-body">
										<p>${currDate} 기준</p>
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:40%;">기술지원 명</th>
														<th style="width:20%;">고객사명</th>
														<th style="width:10%;">담당자</th>
														<th style="width:10%;">담당 엔지니어</th>
														<th style="width:20%;">접수일</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${supportHistoryList }" varStatus="status">
														<tr>
															<td>${item.support_title }</td>
															<td>${item.company_name}</td>
															<td>${item.customer_name }</td>
															<td>${item.support_engineer }</td>
															<td>${item.support_accept_date }</td>
															<td style="display:none;">${item.support_num }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="note" style="margin-top:-10px;">
											최근 5개의 내용까지 표시됩니다.
										</div>
									</div>
								</div>
							</div>
						</article>
					</div>
					<div class="row">
						<article class="col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-1">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>최근 참여한 고객사 회의</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->

									</div>
									<div class="widget-body">
										<p>${currDate} 기준</p>
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:40%;">회의 제목</th>
														<th style="width:20%;">프로젝트 명</th>
														<th style="width:10%;">담당자</th>
														<th style="width:10%;">담당 엔지니어</th>
														<th style="width:20%;">접수일</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${CSTMRMeetingList }" varStatus="status">
														<tr>
															<td>${item.meeting_title }</td>
															<td>${item.project_name}</td>
															<td>${item.customer_name }</td>
															<td>${item.engineer_name }</td>
															<td>${item.meeting_accept_date }</td>
															<td style="display:none;">${item.meeting_num }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="note" style="margin-top:-10px;">
											최근 5개의 내용까지 표시됩니다.
										</div>
									</div>
								</div>
							</div>
						</article>
					</div>
					<div class="row">
						<article class="col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-1">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>최근 참여한 내부 회의</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->

									</div>
									<div class="widget-body">
										<p>${currDate} 기준</p>
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:40%;">회의 제목</th>
														<th style="width:20%;">대상 부서</th>
														<th style="width:10%;">회의 날짜</th>
														<th style="width:10%;">회의 장소</th>
														<th style="width:20%;">작성자</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${INRMeetingList }" varStatus="status">
														<tr>
															<td>${item.meeting_title }</td>
															<td>${item.meeting_dept}</td>
															<td>${item.meeting_date }</td>
															<td>${item.meeting_location }</td>
															<td>${item.writer_name }</td>
															<td style="display:none;">${item.meeting_num }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="note" style="margin-top:-10px;">
											최근 5개의 내용까지 표시됩니다.
										</div>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
			</div>
			<!-- END #MAIN CONTENT -->

		</div>
		<!-- END #MAIN PANEL -->

		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		
		<div id="curr_new_equal_dialog" title="curr new equal dialog">
			<p>
				<span>기존 비밀번호와 새 비밀번호는 <br />같을 수 없습니다.</span>
			</p>
		</div>
		
		<div id="err_500_dialog" title="err 500 dialog">
			<p>
				<span id="err-dialog-text"></span>
			</p>
		</div>
		
		<div id="success_update_info" title="success update info">
			<p>
				<span>개인정보 수정에 성공했습니다.</span>
			</p>
		</div>
		
		<div id="failed_update_info" title="failed update info">
			<p>
				<span id="failed_dialog_text"></span>
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
		  		$.validator.addMethod("valueNotEquals", function(value, element, arg){
					return value != null;
				}, "Value must not equal arg.");
		  		
		  		$(document).keydown(function(e){
	    			if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){
	        			if(e.keyCode === 8){   
	        				return false;
	        			}
	    			}
				});
		  		
		  		//$("#left-panel nav ul li").first().addClass("active");
		  		
		  		//engineer의 dept에 맞는 옵션에 selected 속성 부여
		  		$("#engineerDept").find("option").each(function(){
		  			if(this.value == '${engineerVo.engineer_dept}'){
		  				$(this).attr("selected", "selected");
		  			}
		  		});
		  		
		  		$("#engineerPosition").find("option").each(function(){
		  			if(this.value == '${engineerVo.engineer_position}'){
		  				$(this).attr("selected", "selected");
		  			}
		  		});
		  		
		  		//개인정보 수정 버튼 누르면 부서, 이메일, 전화번호 disabled 풀고 
		  		//비밀번호 display:none 시키고 기존 비밀번호, 새 비밀번호, 새 비밀번호 확인 display:none 없애기
		  		$("#update").click(function(){
		  			$("#engineerDept").removeAttr("disabled");
		  			$("#engineerDept").parent().attr("class", "select");
		  			$("#engineerDept").parent().parent().css("background-image", "linear-gradient(to right, #b7deed 0%, #FFFFFF 100%)");
		  			
		  			$("#engineerEmail").removeAttr("disabled");
		  			$("#engineerEmail").parent().attr("class", "input");
		  			$("#engineerEmail").parent().parent().css("background-image", "linear-gradient(to right, #b7deed 0%, #FFFFFF 100%)");
		  			
		  			$("#engineerPhone").removeAttr("disabled");
		  			$("#engineerPhone").parent().attr("class", "input");
		  			$("#engineerPhone").parent().parent().css("background-image", "linear-gradient(to right, #b7deed 0%, #FFFFFF 100%)");
		  			
		  			$("#engineerPasswd").parent().parent().css('display','none');
		  			$("#currPasswd").parent().parent().css('display','');
		  			$("#currPasswd").parent().parent().css("background-image", "linear-gradient(to right, #b7deed 0%, #FFFFFF 100%)");
		  			$("#newPasswd").parent().parent().css('display','');
		  			$("#newPasswd").parent().parent().css("background-image", "linear-gradient(to right, #b7deed 0%, #FFFFFF 100%)");
		  			$("#newPasswdConfirm").parent().parent().css('display','');
		  			$("#newPasswdConfirm").parent().parent().css("background-image", "linear-gradient(to right, #b7deed 0%, #FFFFFF 100%)");
		  			
		  			$("#updateConfirm").show();
		  			$("#cancel").show();
		  			$("#update").hide();
		  			$("#leave").hide();
		  			
		  			$("#currPasswd").focus();
		  		});
		 	 });
		  	
		  	//수정 취소 버튼 클릭했을 때 새로고침
		  	$("#cancel").click(function(){
		  		location.reload();
		  	});
		  	
		  	//회원탈퇴 버튼 클릭했을 때 이벤트
		  	$("#leave").click(function(){
		  		$("#userType").attr("value", "engineer");
				$("#userId").attr("value", $("#engineerId").val());
				$("#menuTitle").attr("value", encodeURI("회원 탈퇴"));
				$("#menuIcon").attr("value", "fa fa-exclamation-triangle");
				
				$('#myProfileParamForm').attr({action:'/metelSOS/leavePageMove.do', method:'post'}).submit();
		  	});
		  	
		  	//수정했을 때 폼 validate
		  	$("#smart-form").validate({

				// Rules for form validation
				rules : {
					currPasswd : {
						required : true
					},
					engineerEmail : {
						required : true,
						email : true
					},
					newPasswd : {
						required : true,
						minlength : 8,
						maxlength : 20
					},
					newPasswdConfirm : {
						required : true,
						minlength : 8,
						maxlength : 20,
						equalTo : '#newPasswd'
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
					currPasswd : {
						required : '기존 비밀번호를 입력해 주세요.'
					},
					engineerEmail : {
						required : '이메일 주소를 입력해주세요.',
						email : '이메일 형식을 지켜서 작성해주세요.(###@###.###)'
					},
					newPasswd : {
						required : '비밀번호를 입력해 주세요.'
					},
					newPasswdConfirm : {
						required : '비밀번호를 한번 더 입력해주세요.',
						equalTo : '비밀번호와 입력한 값이 틀립니다.'
					},
					engineerDept : {
						valueNotEquals : '부서를 선택해주세요.'
					},
					engineerPhone : {
						required : '위 칸은 필수값입니다.',
						digits : '숫자만 입력해주세요.'
					}
				},
				
				invalidHandler: function(form, validator) {
		             return false;
		        },
		        
		        submitHandler: function (form) {
		        	var postData = $(form).serializeArray();
		        	
		        	for(var i=0;i<postData.length;i++){
		        		//기존 비밀번호와 새 비밀번호가 같으면 return false
		        		if(postData[i].name == 'newPasswd'){
		        			if(postData[i].value == postData[i-1].value){
		        				$('#curr_new_equal_dialog').dialog("open");
		        				return false;
		        			}
		        		}
		        	}
		        	
		        	$.ajax({
		        		url:'/metelSOS/updateEngineerInfo.do',
		        		type:'POST',
		        		dataType:'json',
		        		data:postData,
		        		success:function(msg){
		        			if(msg.resultMsg == 'SUCCESS'){
		        				//1. hidden input에 userType, userId, menuTitle, menuIcon 값 set
		        				$("#userType").attr("value", msg.userType);
		        				$("#userId").attr("value", msg.userId);
		        				$("#menuTitle").attr("value", encodeURI("마이 프로필"));
		        				$("#menuIcon").attr("value", "fa fa-user");
		        				//2. 성공 다이얼로그 출력
		        				$('#success_update_info').dialog("open");
		        			}else{
		        				$('#failed_dialog_text').html(msg.errMsg);
		        				$('#failed_update_info').dialog("open");
		        			}
		        		},
		        		error:function(request,status,error){
							var errMsg = request.status+"\n"+error;
							$('#err-dialog-text').html(errMsg);
							$('#err_500_dialog').dialog("open");
					    }
		        	});
		        }
			});
		
		</script>

	</body>
</html>