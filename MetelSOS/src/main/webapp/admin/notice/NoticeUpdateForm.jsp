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
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;공지사항 수정 실패</h4></div>",
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
							<div class="jarviswidget" id="wid-id-1" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>공지사항</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
				
										<form class="smart-form" name="writeNoticeForm">
											<input type="hidden" name="noticeNum" value="${noticeVo.notice_num}">
											<fieldset>
												<section>
													<label class="label">작성자</label>
													<label class="input">
														<input type="text" id="noticeAuthor" name="noticeAuthor" maxlength="20" readonly="readonly" value="${noticeVo.notice_author }">
													</label>
												</section>
												<section>
													<label class="label">제목</label>
													<label class="input">
														<input type="text" id="noticeTitle" name="noticeTitle" maxlength="20" value="${noticeVo.notice_title }">
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
									<div class="widget-body">
										<p>첨부파일</p>
										<div class="alert alert-info no-margin fade in">
											<button class="close" data-dismiss="alert">
												×
											</button>
											<i class="fa-fw fa fa-info"></i>
											첨부파일을 삭제하시면 복구할 수 없습니다.
										</div>
										<div class="table-responsive">
										
											<table id="fileTable" class="table table-bordered">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:25%;">파일명</th>
														<th style="width:15%;">파일 크기</th>
														<th style="width:20%;">업로드 날짜</th>
														<th style="width:20%; text-align:center;">다운로드</th>
														<th style="width:10%; text-align:center;">삭제</th>
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
															<td style="text-align:center;"><i class="fa fa-file fa-lg" aria-hidden="true"></i></td>
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
					<button id="modify" class="btn btn-success">
						완료
					</button>
					<button id="cancel" class="btn btn-primary" onclick="window.history.back();">
						목록으로
					</button>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		<div id="empty_dialog" title="empty dialog title">
			<p>
				<span>빈 칸을 입력해주세요.</span>
			</p>
		</div>
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
		  		
		  		//로딩 후 textarea에 notice_content 값 세팅
		  		$("#noticeContent").val('${noticeVo.notice_content }');
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
					            formData.append('boardNum', '${noticeVo.notice_num }');
					            formData.append('userName', $("#noticeAuthor").val());
					        });
							
							this.on('complete', function(file, xhr, formData){
								//file 정보 이용해서 테이블 row 추가 로직 구현 
								var jsonObj = JSON.parse(file.xhr.responseText);
								var lastItemNo = parseInt($("#fileTable tr:last").attr("class").replace("item", "")) + 1;
								var fileSize = Math.round(file.size/1024);
								
								$("#fileTable tr:last").after('<tr class="item'+lastItemNo+'"><td style="display:none;"><input type="hidden" id="file_num" value="'+jsonObj.file_num+'"></td><td>'+lastItemNo+'</td><td>'+file.name+'</td><td>'+fileSize+'kb</td><td>'+new Date().yyyymmdd()+'</td><td style="text-align:center;"><i class="fa fa-file fa-lg" aria-hidden="true"></i></td><td style="text-align:center;"><a href="#" class="deleteFile"><i class="fa fa-ban fa-lg" aria-hidden="true"></i></a></td></tr>');
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
					url:"/metelSOS/deleteNoticeFile.do",
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
		  	
		  	$("#modify").click(function(e){
		  		e.preventDefault();
		  		
		  		if($("#noticeTitle").val() == '' || $("#noticeContent").val() == ''){
		  			$('#empty_dialog').dialog("open");
		  			return false;
				}
		  		
		  		 $.ajax({
					url:"/metelSOS/updateNotice.do",
					type:'POST',
					dataType:'json',
					data:$(".smart-form").serialize(),
					success:function(msg){
						if(msg.resultMsg == 'SUCCESS'){
							var comSubmit = new ComSubmit();
	        				comSubmit.addParam('userType', "engineer");
	    		  			comSubmit.addParam('menuTitle', encodeURI("공지사항 관리"));
	    		  			comSubmit.addParam('menuIcon', "fa fa-lg fa-fw fa-bell");
	    		  			comSubmit.setUrl("/metelSOS/noticePageMove.do");
	    		  			comSubmit.getSubmit();
						}
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
		  	});
		
		</script>

	</body>
</html>