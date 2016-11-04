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
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;공지사항 등록 실패</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#empty_dialog').dialog("close");
							if($("#noticeTitle").val() == ''){
								$("#noticeTitle").focus();
							}else if($("#noticeContent").val() == ''){
								$("#noticeContent").focus();
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
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;공지사항 등록 실패</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#err_500_dialog').dialog("close");
							$("#noticeTitle").focus();
							return false;
						}
					}]
				});
			});
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/engineer/EngineerLeftPanel.jsp" flush="false"/>
		<form name="tempForm">
			<input type="hidden" id="boardNum" name="boardNum">
		</form>
		
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
							<div class="jarviswidget" id="wid-id-1" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>공지사항 작성 </h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<form class="smart-form" name="writeNoticeForm">
											<fieldset>
												<section>
													<label class="label">작성자</label>
													<label class="input">
														<input type="text" id="noticeAuthor" name="noticeAuthor" maxlength="20" readonly="readonly" value="${userName }">
													</label>
												</section>
												<section>
													<label class="label">제목</label>
													<label class="input">
														<input type="text" id="noticeTitle" name="noticeTitle" maxlength="20">
													</label>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label">내용</label>
													<label class="textarea textarea-resizable"> 										
														<textarea rows="10" id="noticeContent" name="noticeContent" class="custom-scroll"></textarea> 
													</label>
												</section>
											</fieldset>
										</form>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
				<section id="widget-grid" class="">
					<div class="row">
						<article class="col-sm-12">
							<div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-0" data-widget-editbutton="false">
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<form action="/metelSOS/uploadNoticeFile.do" class="dropzone" id="mydropzone">
										</form>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
				<section id="widget-grid" class="">
					<button id="enroll" class="btn btn-primary">
						등록하기
					</button>
					<button id="cancel" class="btn btn-default" onclick="window.history.back();">
						목록으로
					</button>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->
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
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		<%@ include file="/common/include/include-body.jsp" %>
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
		  		pageSetUp();
		  		$("#noticeTitle").focus();
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
								if($("#noticeTitle").val() == ''){
									$('#empty_dialog').dialog("open");
									return false;
								}
								
								if($("#noticeContent").val() == ''){
									$('#empty_dialog').dialog("open");
									return false;
								}
								
								$.ajax({
					        		url:'/metelSOS/insertNotice.do',
					        		type:'POST',
					        		dataType:'json',
					        		data:$(".smart-form").serializeArray(),
					        		success:function(msg){
					        			if(msg.resultMsg == 'SUCCESS'){
					        				$("#boardNum").val(msg.boardNum);
					        				myDropzone.processQueue();
					        				
					        				var comSubmit = new ComSubmit();
					        				comSubmit.addParam('userType', "engineer");
					    		  			comSubmit.addParam('menuTitle', encodeURI("공지사항 관리"));
					    		  			comSubmit.addParam('menuIcon', "fa fa-lg fa-fw fa-bell");
					    		  			comSubmit.setUrl("/metelSOS/noticePageMove.do");
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
					            formData.append('boardNum', $("#boardNum").val());
					            formData.append('userName', $("#noticeAuthor").val());
					        });
						}
						
		  			});
		  			
		  		});
		  		
		  		$("#cancel").click(function(e){
		  			e.preventDefault();
		  			window.history.back();
		  		});
		 	 });
		
		</script>

	</body>
</html>