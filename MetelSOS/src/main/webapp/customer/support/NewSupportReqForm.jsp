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
			//세션없으면 로그인 페이지로 이동
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId == 'null'){
				document.location.href="/metelSOS/login.jsp";
			}
			
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
			
			$(document).ready(function(){
				$('#empty_dialog').dialog({
					autoOpen : false,
					width: 300,
					height:200,
					resizable: false,
					modal : true,
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;새 지원 요청 등록 실패</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#empty_dialog').dialog("close");
							if($("input[name=support_title]").val() == ''){
								$("input[name=support_title]").focus();
							}else if($("input[name=customer_phone]").val() == ''){
								$("input[name=customer_phone]").focus();
							}else if($("input[name=hope_support_date]").val() == ''){
								$("input[name=hope_support_date]").focus();
							}else if($("textarea[name=support_request]").val() == ''){
								$("textarea[name=support_request]").focus();
							}
							
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
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;새 지원 요청 등록 실패</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#err_500_dialog').dialog("close");
							$("input[name=support_title]").focus();
							return false;
						}
					}]
				});
			});
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/customer/CustomerLeftPanel.jsp" flush="false"/>
		<form name="tempForm">
			<input type="hidden" id="supportNum" name="supportNum">
		</form>
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
						<article class="col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget" id="supportReqWidget" data-widget-editbutton="false" data-widget-custombutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>지원 요청 양식</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<form id="supportReqForm" class="smart-form">
											<input type="hidden" name="userId" value="${userId }">
											<fieldset>
												<section>
													<label class="label"><strong>제목</strong></label>
													<label class="input">
														<input type="text" name="support_title">
													</label>
													<div class="note">
														<strong>참고:</strong>프로젝트 명 및 제목을 입력해주세요.
													</div>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>고객사</strong></label>
													<label class="input">
														<input type="text" name="company_name" value="${customerInfo.COMPANY_NAME }" readonly="readonly">
													</label>
													<div class="note">
														<strong>참고:</strong>고객사 명으로 고정값입니다.
													</div>
												</section>
												<section>
													<label class="label"><strong>담당자</strong></label>
													<label class="input">
														<input type="text" name="customer_name" value="${customerInfo.CUSTOMER_NAME } ${customerInfo.CUSTOMER_POSITION}" readonly="readonly">
													</label>
													<div class="note">
														<strong>참고:</strong>담당자 명으로, 고정값입니다.
													</div>
												</section>
												<section>
													<label class="label"><strong>담당자 연락처</strong></label>
													<label class="input">
														<input type="text" name="customer_phone" value="${customerInfo.CUSTOMER_PHONE }">
													</label>
													<div class="note">
														<strong>참고:</strong>위에 입력된 번호로 연락이 불가능한 경우, 다른 연락 가능한 번호로 변경해주세요.
													</div>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<div class="col-sm-2">
													<label class="label"><strong>희망 지원일</strong></label>
													<label class="input">
														<input type="text" name="hope_support_date" placeholder="날짜를 선택해주세요." class="form-control datepicker" data-dateformat="yy/mm/dd">
													</label>
													<div class="note">
														<strong>참고:</strong>지원을 희망하는 날짜를 선택해주세요.
													</div>
													</div>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>요청사항</strong></label>
													<label class="textarea textarea-resizable"> <i class="icon-append fa fa-question-circle"></i> 										
														<textarea rows="10" name="support_request"></textarea>
														<b class="tooltip tooltip-top-right"> 
															<i class="fa fa-warning txt-color-teal"></i> 
															구체적으로 입력해주세요.</b> 
													</label>
												</section>
											</fieldset>
										</form>
									</div>
								</div>
							</div>
							<div class="jarviswidget jarviswidget-color-blueLight" id="fileUpload" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-cloud"></i> </span>
									<h2>첨부파일</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<form action="/metelSOS/uploadSupportFile.do" class="dropzone" id="mydropzone">
										</form>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
				<section id="widget-grid" class="">
					<button id="enroll" class="btn btn-success">
						등록하기
					</button>
					<button id="moveSupportList" class="btn btn-danger">
						목록으로
					</button>
				</section>
			</div>	
		</div>
		
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		<%@ include file="/common/include/include-body.jsp" %>
		<div id="empty_dialog" title="empty dialog title">
			<p>
				<span>빈 칸을 입력해주세요.</span>
			</p>
		</div>
		<div id="err_500_dialog" title="err 500 dialog">
			<p>
				<span id="err-dialog-text"></span>
			</p>
		</div>
		<script type="text/javascript">
			var file_count = 1;	
		
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
		  		$("#left-panel nav ul li:nth-child(2)").addClass("active");
		  		
		  		Dropzone.autoDiscover = false;
		  		$(".dropzone").each(function(){
		  			var id = $(this).attr('id');
		  			var url = $(this).data('action');
		  			
		  			$("#mydropzone").dropzone({
		  				url: url,
						addRemoveLinks : true,
						maxFilesize: 256,
						parallelUploads: 5,
						maxFiles: 5,
						autoProcessQueue:false,
						acceptedFiles: ".jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF",
						dictDefaultMessage: '<span class="text-center"><span class="font-lg visible-xs-block visible-sm-block visible-lg-block"><span class="font-lg"><i class="fa fa-caret-right text-danger"></i> <span class="font-xs"> 첨부 파일을 가져다 놓으세요.</span></span>',
						dictResponseError: 'Error uploading file!',
						init : function(){
							var myDropzone = this;
							$("#enroll").click(function(){
								if($("input[name=support_title]").val() == ''){
									$('#empty_dialog').dialog("open");
									return false;
								}
								
								if($("input[name=customer_phone]").val() == ''){
									$('#empty_dialog').dialog("open");
									return false;
								}
								
								if($("input[name=hope_support_date]").val() == ''){
									$('#empty_dialog').dialog("open");
									return false;
								}
								
								if($("textarea[name=support_request]").val() == ''){
									$('#empty_dialog').dialog("open");
									return false;
								}
								
								$.ajax({
					        		url:'/metelSOS/insertSupportRequest.do',
					        		type:'POST',
					        		dataType:'json',
					        		data:$("#supportReqForm").serializeArray(),
					        		success:function(msg){
					        			if(msg.resultMsg == 'SUCCESS'){
					        				$("#supportNum").val(msg.supportNum);
					        				myDropzone.processQueue();
					        				
					        				var comSubmit = new ComSubmit();
					        				comSubmit.addParam('userType', "customer");
					    		  			comSubmit.addParam('menuTitle', "RequestSupport");
					    		  			comSubmit.addParam('menuIcon', "fa fa-lg fa-fw fa-info-circle");
					    		  			comSubmit.setUrl("/metelSOS/leftMenuPageMove.do");
					    		  			comSubmit.getSubmit();
					        			}
					        		},
					        		error:function(request,status,error){
										var errMsg = request.status+"\n"+error;
										$('#err-dialog-text').html(errMsg);
										$('#err_500_dialog').dialog("open");
								    }
					        	});
							});
							
							this.on('sending', function(file, xhr, formData){
					            formData.append('supportNum', $("#supportNum").val());
					            formData.append('userId', '${userId }');
					        });
						}
						
		  			});
		  			
		  		});
		  		
		  		/* $("#supportReqForm").validate({

					rules : {
						support_title : {
							required : true
						},
						
						customer_phone : {
							required : true
						},
						
						hope_support_date : {
							required : true
						},
						
						support_request : {
							required : true
						}
						
					},

					messages : {
						support_title : {
							required : '제목을 입력해주세요.',
						},
						
						customer_phone : {
							required : '담당자 번호를 입력해주세요.',
						},
						
						hope_support_date : {
							required : '희망 지원일자를 선택해주세요.',
						},
						
						support_request : {
							required : '요청사항을 입력해주세요.',
						}
					},
					
					invalidHandler: function(form, validator) {
			             return false;
			        },
			        
			        submitHandler: function (form) {
			        	/* var formData = new FormData();
			        	var specifyDate = '${currYear}-${selectMonth}-01';
			        	formData.append("exclnt_year_month", specifyDate);
			        	formData.append("engineer_dept", $("#deptSelect").val());
			        	formData.append("engineer_name", $("#nameSelect").val());
			        	formData.append("engineer_image", $("input[name=engineer_image]")[0].files[0]);
			        	formData.append("impression_speech", $("#impression_speech").val());
			        	$.ajax({
							url:'/metelSOS/enrollExclntStf.do',
							type:'POST',
							processData: false,
			        	    contentType: false,
							data: formData,
							success:function(msg){
								var obj = eval("("+msg+")");
								if(obj.resultMsg == 'SUCCESS'){
									var comSubmit = new ComSubmit();
						  			comSubmit.setUrl("/metelSOS/moveExclntStfListPage.do");
						  			comSubmit.addParam('currYear', '${currYear}');
						  			comSubmit.addParam('selectMonth', '${selectMonth}');
						  			comSubmit.addParam('userType', 'engineer');
						  			comSubmit.addParam('menuTitle', encodeURI("${currYear}년 ${selectMonth}월 우수사원 "));
						  			comSubmit.addParam('menuIcon', 'fa fa-lg fa-fw fa-thumbs-up');
						  			comSubmit.getSubmit();
								}
							},
							error:function(request,status,error){
								alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						    }
						}); 
			            
			        }
				});*/
		 	 });
		  	
		
		</script>

	</body>
</html>