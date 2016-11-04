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
				
				 $('#delete_check_dialog').dialog({
						autoOpen : false,
						width: 300,
						height:350,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;공지사항 삭제</h4></div>",
						buttons:[{
							html:"<i class='fa fa-warning'></i>&nbsp; 삭제",
							"class": "btn btn-danger",
							click:function(e){
								e.preventDefault();
								$('#delete_check_dialog').dialog("close");
								$.ajax({
									url:"/metelSOS/deleteNotice.do",
									type:'POST',
									dataType:'json',
									data:{ "boardNum" : "${noticeVo.notice_num }" },
									success:function(msg){
										//삭제 성공 시 공지사항 목록으로 이동
										var comSubmit = new ComSubmit();
								        comSubmit.setUrl("/metelSOS/noticePageMove.do");
								        comSubmit.addParam("userType", "engineer");
								        comSubmit.addParam("menuTitle", encodeURI("공지사항 관리"));
								        comSubmit.addParam("menuIcon", "fa fa-lg fa-fw fa-bell");
								        comSubmit.getSubmit();
									},
									error:function(request,status,error){
								        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
								    }
								});
							}
						} ,{
							html:"<i class='fa fa-check'></i>&nbsp; 취소",
							"class": "btn btn-default",
							click:function(e){
								e.preventDefault();
								$('#delete_check_dialog').dialog("close");
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
														<input type="text" id="noticeTitle" name="noticeTitle" maxlength="20" readonly="readonly" value="${noticeVo.notice_title }">
													</label>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label">내용</label>
													<label class="textarea textarea-resizable"> 										
														<textarea rows="10" id="noticeContent" name="noticeContent" class="custom-scroll" readonly="readonly"></textarea> 
													</label>
												</section>
											</fieldset>
										</form>
									</div>
									<div class="widget-body">
										<p>첨부파일</p>
										
										<div class="table-responsive">
										
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:25%;">파일명</th>
														<th style="width:20%;">파일 크기</th>
														<th style="width:25%;">업로드 날짜</th>
														<th style="width:20%; text-align:center;">다운로드</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${fileList}" varStatus="status">
														<tr>
															<td style="display:none;">
																<input type="hidden" id="file_num" value="${item.file_num }">
															</td>
															<td>${status.count }</td>
															<td>${item.original_file_name }</td>
															<td>${item.file_size }kb</td>
															<td>${item.crea_dtm }</td>
															<td style="text-align:center;"><a href="#" class="noticeFile"><i class="fa fa-file fa-lg" aria-hidden="true"></i></a></td>
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
					<c:if test="${userId == 'admin' }">
						<button id="modify" class="btn btn-success">
							수정하기
						</button>
						<button id="delete" class="btn btn-danger">
							삭제하기
						</button>
					</c:if>
					<button id="cancel" class="btn btn-primary" onclick="window.history.back();">
						목록으로
					</button>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		
		<div id="delete_check_dialog" title="delete check dialog">
			<p>
				<span>정말 삭제하시겠습니까?<br />
							삭제한 게시글은 복구할 수 없습니다.</span>
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
		  		
		  		//첨부파일 다운로드 클릭 이벤트
		  		$(".noticeFile").click(function(e){
		  			e.preventDefault();
		  			downloadFile($(this));
		  		});
		  		
		 	 });
		  //삭제 버튼 클릭 이벤트 
	  		$("#delete").click(function(e){
	  			e.preventDefault();
	  			$('#delete_check_dialog').dialog("open");
	  		});
		  
		  //수정 버튼 클릭 이벤트
		  $("#modify").click(function(e){
			  e.preventDefault();
			 /*  paramMap.put("userType", "engineer");
				paramMap.put("menuTitle", "공지사항");
				paramMap.put("menuIcon", "fa fa-lg fa-fw fa-bell"); */
			  var comSubmit = new ComSubmit();
		      comSubmit.setUrl("/metelSOS/moveNoticeUpdatePage.do");
		      comSubmit.addParam("notice_num", "${noticeVo.notice_num }");
		      comSubmit.addParam("userType", "engineer");
		      comSubmit.addParam("menuTitle", encodeURI("공지사항"));
		      comSubmit.addParam("menuIcon", "fa fa-lg fa-fw fa-bell");
		      comSubmit.getSubmit();
		  });
		  	
		  	function downloadFile(obj){
		  		var file_num = $("#file_num").val();
		       var comSubmit = new ComSubmit();
		        comSubmit.setUrl("/metelSOS/downloadNoticeFile.do");
		        comSubmit.addParam("file_num", file_num);
		        comSubmit.postSubmit();
		  	}
		
		</script>

	</body>
</html>