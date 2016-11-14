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
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;지원 요청 수정 실패</h4></div>",
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
			});
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/customer/CustomerLeftPanel.jsp" flush="false"/>
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
									<h2>지원 요청 보기</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<form id="supportReqForm" class="smart-form">
											<input type="hidden" name="userId" value="${userId }">
											<input type="hidden" name="supportNum" value="${supportNum}">
											<fieldset>
												<section>
													<label class="label"><strong>제목</strong></label>
													<label class="input">
														<input type="text" name="support_title" value="${supportVo.support_title }">
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
														<input type="text" name="company_name" value="${supportVo.company_name }" readonly="readonly">
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
														<input type="text" name="customer_phone" value="${supportVo.customer_phone }">
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
														<input type="text" name="hope_support_date" data-dateformat="yy/mm/dd" class="form-control datepicker" value="${supportVo.hope_support_date }">
													</label>
													</div>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>요청사항</strong></label>
													<label class="textarea textarea-resizable"><i class="icon-append fa fa-question-circle"></i> 										
														<textarea rows="10" name="support_request"></textarea>
														<b class="tooltip tooltip-top-right"> 
															<i class="fa fa-warning txt-color-teal"></i> 
															구체적으로 입력해주세요.</b> 
													</label>
												</section>
											</fieldset>
										</form>
									</div>
									<div class="widget-body">
										<p>첨부파일</p>
										
										<div class="table-responsive">
											<table id="fileTable" class="table table-bordered">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:25%;">파일명</th>
														<th style="width:20%;">파일 크기</th>
														<th style="width:25%;">업로드 날짜</th>
														<th style="width:20%; text-align:center;">삭제</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${fileList}" varStatus="status">
														<tr class="item${status.count}">
															<td style="display:none;">
																<input type="hidden" id="file_num" value="${item.file_num }">
															</td>
															<td>${status.count }</td>
															<td>${item.original_file_name }</td>
															<td>${item.file_size }kb</td>
															<td>${item.crea_dtm }</td>
															<td style="text-align:center;"><a href="#" class="deleteFile"><i class="fa fa-ban fa-lg" aria-hidden="true"></i></a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
				<section id="widget-grid" class="">
					<div class="row">
						<article class="col-sm-12">
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
					<button id="ok" class="btn btn-success">
						확인
					</button>
					<button id="cancel" class="btn btn-danger">
						취소
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
		  		pageSetUp();
		  		
		  		$("#left-panel nav ul li:nth-child(2)").addClass("active");
		  		
		  		var content = "${supportVo.support_request }";
		  		//로딩 후 textarea에 notice_content 값 세팅
		  		$("textarea[name=support_request]").val(content.replace(/<br\s?\/?>/g,"\n"));
		  		
		  		Dropzone.autoDiscover = false;
		  		$(".dropzone").each(function(){
		  			var id = $(this).attr('id');
		  			var url = $(this).data('action');
		  			
		  			$("#mydropzone").dropzone({
		  				url: url,
						addRemoveLinks : false,
						maxFilesize: 256,
						maxFiles: 5,
						autoProcessQueue:true,
						acceptedFiles: ".jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF",
						dictDefaultMessage: '<span class="text-center"><span class="font-lg visible-xs-block visible-sm-block visible-lg-block"><span class="font-lg"><i class="fa fa-caret-right text-danger"></i> <span class="font-xs"> 첨부 파일을 가져다 놓으세요.</span></span>',
						dictResponseError: 'Error uploading file!',
						init : function(){
							var myDropzone = this;
							
							this.on('sending', function(file, xhr, formData){
								formData.append('supportNum', '${supportNum}');
					            formData.append('userId', '${userId }');
					        });
							
							this.on('complete', function(file, xhr, formData){
								//file 정보 이용해서 테이블 row 추가 로직 구현 
								var jsonObj = JSON.parse(file.xhr.responseText);
								var lastItemNo = parseInt($("#fileTable tr:last").attr("class").replace("item", "")) + 1;
								var fileSize = Math.round(file.size/1024);
								
								$("#fileTable tr:last").after('<tr class="item'+lastItemNo+'"><td style="display:none;"><input type="hidden" id="file_num" value="'+jsonObj.file_num+'"></td><td>'+lastItemNo+'</td><td>'+file.name+'</td><td>'+fileSize+'kb</td><td>'+new Date().yyyymmdd()+'</td><td style="text-align:center;"><a href="#" class="deleteFile"><i class="fa fa-ban fa-lg" aria-hidden="true"></i></a></td></tr>');
							});
						}
						
		  			});
		  			
		  		});
		  		
		  		Date.prototype.yyyymmdd = function(){
		  		    var yyyy = this.getFullYear().toString();
		  		    var mm = (this.getMonth() + 1).toString();
		  		    var dd = this.getDate().toString();
		  		    return yyyy + '-' + (mm[1] ? mm : '0'+mm[0]) + '-' + (dd[1] ? dd : '0'+dd[0]);
		  		}
		 	 });
		  	
		  	$(".deleteFile").click(function(e){
	  			e.preventDefault();
	  			
	  			var file_num = $(this).parent().parent()[0].children[0].children[0].defaultValue;
	  			
	  			$.ajax({
					url:"/metelSOS/deleteSupportFile.do",
					type:'POST',
					dataType:'json',
					data:{ "file_num" : file_num },
					success:function(msg){
						if(msg.resultMsg == 'SUCCESS'){
							for(var i=0;i<$("#fileTable tr").length;i++){
				  				if($("#fileTable tr")[i].children[0].children[0] != undefined && $("#fileTable tr")[i].children[0].children[0].defaultValue == file_num){
				  					$("#fileTable tr")[i].remove();
				  				}
				  			}
						}
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
	  		});
		  	
		  	//수정 확인 버튼 클릭 이벤트
		  	$("#ok").click(function(e){
		  		e.preventDefault();
		  		
		  		if($("input[name=support_title]").val() == '' || $("input[name=customer_phone]").val() == '' || $("input[name=hope_support_date]").val() == '' || $("textarea[name=support_request]").val() == ''){
					$('#empty_dialog').dialog("open");
					return false;
				}
		  		
		  		 $.ajax({
					url:"/metelSOS/updateSupportRequest.do",
					type:'POST',
					dataType:'json',
					data:$(".smart-form").serialize(),
					success:function(msg){
						if(msg.resultMsg == 'SUCCESS'){
							document.location.href="/metelSOS/leftMenuPageMove.do?userType=customer&menuTitle=SupportHistory&menuIcon=fa fa-lg fa-fw fa-history";
						}
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
		  	});
		  	
		  	$("#cancel").click(function(e){
		  		e.preventDefault();
		  		document.location.href="/metelSOS/leftMenuPageMove.do?userType=customer&menuTitle=SupportHistory&menuIcon=fa fa-lg fa-fw fa-history";
		  	});
		
		</script>

	</body>
</html>