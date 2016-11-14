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
				 $('#accept_check_dialog').dialog({
						autoOpen : false,
						width: 300,
						height:200,
						resizable: false,
						modal : true,
						title:"<div class='widget-header'><h4><i class='fa fa-wrench'></i>&nbsp;지원 접수</h4></div>",
						buttons:[{
							html:"<i class='fa fa-check'></i>&nbsp; 확인",
							"class": "btn btn-default",
							click:function(){
								$('#accept_check_dialog').dialog("close");
								/* var comSubmit = new ComSubmit();
							    comSubmit.setUrl("/metelSOS/acceptSupport.do");
							    comSubmit.addParam("userId", '${userId}');
							    comSubmit.addParam("supportNum", '${supportVo.support_num }');
							    comSubmit.postSubmit(); */
							    $.ajax({
									url:"/metelSOS/acceptSupport.do",
									type:'POST',
									dataType:'json',
									data:{ "supportNum" : '${supportVo.support_num }', "userId" : '${userId}'},
									success:function(msg){
										if(msg.resultMsg == 'SUCCESS'){
											document.location.href="/metelSOS/leftMenuPageMove.do?userType=engineer&menuTitle=UnSupportList&menuIcon=fa fa-lg fa-fw fa-wrench";
										}
									},
									error:function(request,status,error){
								        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
								    }
								});
							}
						}]
					});
				});
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/engineer/EngineerLeftPanel.jsp" flush="false"/>
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
											<fieldset>
												<section>
													<label class="label"><strong>제목</strong></label>
													<label class="input">
														<input type="text" name="support_title" value="${supportVo.support_title }" readonly="readonly">
													</label>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>고객사</strong></label>
													<label class="input">
														<input type="text" name="company_name" value="${supportVo.company_name }" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>담당자</strong></label>
													<label class="input">
														<input type="text" name="customer_name" value="${supportVo.customer_name } ${supportVo.customer_position}" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>담당자 연락처</strong></label>
													<label class="input">
														<input type="text" name="customer_phone" value="${supportVo.customer_phone }" readonly="readonly">
													</label>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<div class="col-sm-2">
													<label class="label"><strong>희망 지원일</strong></label>
													<label class="input">
														<input type="text" name="hope_support_date" data-dateformat="yy/mm/dd" value="${supportVo.hope_support_date }" readonly="readonly">
													</label>
													</div>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>요청사항</strong></label>
													<label class="textarea textarea-resizable"> 										
														<textarea rows="10" name="support_request" readonly="readonly"></textarea>
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
															<td>${status.count }</td>
															<td>${item.original_file_name }</td>
															<td>${item.file_size }kb</td>
															<td>${item.crea_dtm }</td>
															<td style="text-align:center;"><a href="javascript:downloadFile('${item.file_num }')" class="supportFile"><i class="fa fa-file fa-lg" aria-hidden="true"></i></a></td>
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
					<button id="accept" class="btn btn-success">
						접수하기
					</button>
					<button id="cancel" class="btn btn-primary">
						목록으로
					</button>
				</section>
			</div>	
		</div>
		
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		<%@ include file="/common/include/include-body.jsp" %>
		<div id="accept_check_dialog" title="accept check dialog">
			<p>
				<span>접수하시겠습니까?</span>
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
		  		$('#left-panel nav ul li:nth-child(2) .collapse-sign').trigger('click');
		  		$("#left-panel nav ul li:nth-child(2)").addClass("active");
		  		var content = "${supportVo.support_request }";
		  		//로딩 후 textarea에 notice_content 값 세팅
		  		$("textarea[name=support_request]").val(content.replace(/<br\s?\/?>/g,"\n"));
		 	 });
		  	
	  		function downloadFile(file_num){
		       var comSubmit = new ComSubmit();
		       comSubmit.setUrl("/metelSOS/downloadSupportFile.do");
		       comSubmit.addParam("file_num", file_num);
		       comSubmit.postSubmit();
		  	}
	  		
	  		//목록으로 버튼 클릭 이벤트
	  		$("#cancel").click(function(e){
	  			document.location.href="/metelSOS/leftMenuPageMove.do?userType=engineer&menuTitle=UnSupportList&menuIcon=fa fa-lg fa-fw fa-wrench";
	  		});
	  		
	  		//접수하기 버튼 클릭 이벤트
	  		$("#accept").click(function(e){
	  			$('#accept_check_dialog').dialog("open");
	  		});
		
		</script>

	</body>
</html>