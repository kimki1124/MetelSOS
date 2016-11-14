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
							<i class="fa fa-lg fa-fw fa-wrench"></i> 
								${menuTitle} 
						</h3>
					</div>
				</div>
				<section id="widget-grid" class="">
					<div class="row">
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<table id="acceptSupportJqgrid"></table>
							<div id="acceptSupportpJqgrid"></div>
						</article>
					</div>
					<div class="row">
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<table id="supportingJqgrid"></table>
							<div id="supportingpJqgrid"></div>
						</article>
					</div>
					<div class="row">
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<table id="completeSupportJqgrid"></table>
							<div id="completeSupportpJqgrid"></div>
						</article>
					</div>
				</section>
			</div>	
		</div>
		
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		<div class="modal modal-center fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-80size">
				<div class="modal-content modal-80size">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel"></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
									<div>
										<div class="widget-body">
											<form id="supportInfoForm" class="smart-form">
												<section>
													<label class="label"><strong>제목</strong></label>
													<label class="input">
													<input type="text" name="support_title" id="support_title" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>고객사</strong></label>
													<label class="input">
													<input type="text" name="company_name" id="company_name" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>담당자</strong></label>
													<label class="input">
													<input type="text" name="customer_name" id="customer_name" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>담당 엔지니어</strong></label>
													<label class="input">
													<input type="text" name="support_engineer" id="support_engineer" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>접수일시</strong></label>
													<label class="input">
													<input type="text" name="support_accept_date" id="support_accept_date" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>지원방식</strong></label>
													<label class="input">
													<input type="text" name="support_way" id="support_way" readonly="readonly">
													</label>
												</section>
												<section id="purpose_of_visit_section" style="display:none;">
													<label class="label"><strong>방문목적</strong></label>
													<label class="input">
														<label class="textarea textarea-resizable">
														<textarea rows="10" name="purpose_of_visit"  id="purpose_of_visit"readonly="readonly"></textarea>
														</label>
													</label>
												</section>
												<section>
													<label class="label"><strong>증상 및 요청사항</strong></label>
													<label class="input">
														<label class="textarea textarea-resizable">
														<textarea rows="10" name="support_request"  id="support_request"readonly="readonly"></textarea>
														</label>
													</label>
												</section>
												<section>
													<label class="label"><strong>작업내용 및 조치사항</strong></label>
													<label class="input">
														<label class="textarea textarea-resizable">
														<textarea rows="10" name="support_response"  id="support_response"readonly="readonly"></textarea>
														</label>
													</label>
												</section>
												<section>
													<label class="label"><strong>Engineer Comment</strong></label>
													<label class="input">
														<label class="textarea textarea-resizable">
														<textarea rows="10" name="support_engineer_comment"  id="support_engineer_comment"readonly="readonly"></textarea>
														</label>
													</label>
												</section>
											</form>
										</div>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							닫기
						</button>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/common/include/include-body.jsp" %>
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
		  		$("#left-panel nav ul li:nth-child(2) ul li:nth-child(1)").addClass("active");
		  		
		  		var acceptSupportJqgrid_data;
		  		var supportingJqgrid_data;
		  		var completeSupportJqgrid_data;
		  		
		  	//접수 완료된 목록
				 $.ajax({
					url:'/metelSOS/getAcceptSupportList.do',
					type:'POST',
					dataType:'json',
					data:{"userId" : '${userId}'},
					async:false,
					success:function(msg){
						acceptSupportJqgrid_data = JSON.parse(msg.jsonList);
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				}); 
				 
				 jQuery("#acceptSupportJqgrid").jqGrid({
						data : acceptSupportJqgrid_data,
						datatype : "local",
						height : 'auto',
						colNames : ['제목', '고객사', '담당자', '접수일', '상태', 'SUPPORT_NUM'],
						colModel : [{
							name : 'SUPPORT_TITLE',
							index : 'SUPPORT_TITLE',
							sortable:false
						}, {
							name : 'COMPANY_NAME',
							index : 'COMPANY_NAME',
							sortable:false
						}, {
							name : 'CUSTOMER_NAME',
							index : 'CUSTOMER_NAME',
							sortable : false
						},{
							name : 'SUPPORT_ACCEPT_DATE',
							index : 'SUPPORT_ACCEPT_DATE',
							sortable:false
						}, {
							name : 'SUPPORT_STATE',
							index : 'SUPPORT_STATE',
							sortable:false
						},{
							name : 'SUPPORT_NUM',
							index : 'SUPPORT_NUM',
							hidden: true
						}],
						rowNum : 10,
						rowList : [10, 20, 30],
						pager : '#acceptSupportpJqgrid',
						sortname : 'SUPPORT_ACCEPT_DATE',
						toolbarfilter : true,
						viewrecords : true,
						sortorder : "desc",
						caption : "<i class='fa fa-table' aria-hidden='true'></i>&nbsp;&nbsp;내가 접수한 지원 목록",
						autowidth : true,
						
						ondblClickRow: function(rowid){
							var supportNum = $(this).jqGrid('getCell', rowid, 'SUPPORT_NUM');
							document.location.href="/metelSOS/mvEngAcptSprtDtlPage.do?supportNum="+supportNum+"&userId=${userId}";
						}
					});
				 
				 jQuery("#acceptSupportJqgrid").jqGrid('navGrid', "#acceptSupportpJqgrid", {
						edit : false,
						add : false,
						del : false
				});
				 
				//지원 중인 요청 목록
				 $.ajax({
					url:'/metelSOS/getSupportingList.do',
					type:'POST',
					dataType:'json',
					data:{"userId" : '${userId}'},
					async:false,
					success:function(msg){
						supportingJqgrid_data = JSON.parse(msg.jsonList);
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				}); 
				 
				 jQuery("#supportingJqgrid").jqGrid({
						data : supportingJqgrid_data,
						datatype : "local",
						height : 'auto',
						colNames : ['제목', '고객사', '담당자', '접수일', '상태', 'SUPPORT_NUM'],
						colModel : [{
							name : 'SUPPORT_TITLE',
							index : 'SUPPORT_TITLE',
							sortable:false
						}, {
							name : 'COMPANY_NAME',
							index : 'COMPANY_NAME',
							sortable:false
						}, {
							name : 'CUSTOMER_NAME',
							index : 'CUSTOMER_NAME',
							sortable : false
						},{
							name : 'SUPPORT_ACCEPT_DATE',
							index : 'SUPPORT_ACCEPT_DATE',
							sortable:false
						}, {
							name : 'SUPPORT_STATE',
							index : 'SUPPORT_STATE',
							sortable:false
						},{
							name : 'SUPPORT_NUM',
							index : 'SUPPORT_NUM',
							hidden: true
						}],
						rowNum : 10,
						rowList : [10, 20, 30],
						pager : '#supportingpJqgrid',
						sortname : 'SUPPORT_ACCEPT_DATE',
						toolbarfilter : true,
						viewrecords : true,
						sortorder : "desc",
						caption : "<i class='fa fa-table' aria-hidden='true'></i>&nbsp;&nbsp;지원 중인 요청 목록",
						autowidth : true,
						
						ondblClickRow: function(rowid){
							var supportNum = $(this).jqGrid('getCell', rowid, 'SUPPORT_NUM');
							document.location.href="/metelSOS/mvEngSprtngDtlPage.do?supportNum="+supportNum+"&userId=${userId}";
						}
					});
				 
				 jQuery("#supportingJqgrid").jqGrid('navGrid', "#supportingpJqgrid", {
						edit : false,
						add : false,
						del : false
				});
				 
				//지원 완료 목록
				 $.ajax({
					url:'/metelSOS/getCompleteSupportList.do',
					type:'POST',
					dataType:'json',
					data:{"userId" : '${userId}'},
					async:false,
					success:function(msg){
						completeSupportJqgrid_data = JSON.parse(msg.jsonList);
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
				 
				 jQuery("#completeSupportJqgrid").jqGrid({
						data : completeSupportJqgrid_data,
						datatype : "local",
						height : 'auto',
						colNames : ['제목', '고객사', '담당자', '접수일', '상태', 'SUPPORT_NUM'],
						colModel : [{
							name : 'SUPPORT_TITLE',
							index : 'SUPPORT_TITLE',
							sortable:false
						}, {
							name : 'COMPANY_NAME',
							index : 'COMPANY_NAME',
							sortable:false
						}, {
							name : 'CUSTOMER_NAME',
							index : 'CUSTOMER_NAME',
							sortable : false
						},{
							name : 'SUPPORT_ACCEPT_DATE',
							index : 'SUPPORT_ACCEPT_DATE',
							sortable:false
						}, {
							name : 'SUPPORT_STATE',
							index : 'SUPPORT_STATE',
							sortable:false
						},{
							name : 'SUPPORT_NUM',
							index : 'SUPPORT_NUM',
							hidden: true
						}],
						rowNum : 10,
						rowList : [10, 20, 30],
						pager : '#completeSupportpJqgrid',
						sortname : 'SUPPORT_ACCEPT_DATE',
						toolbarfilter : true,
						viewrecords : true,
						sortorder : "desc",
						caption : "<i class='fa fa-table' aria-hidden='true'></i>&nbsp;&nbsp;지원 완료된 요청 목록",
						autowidth : true,
						
						ondblClickRow: function(rowid){
							var supportNum = $(this).jqGrid('getCell', rowid, 'SUPPORT_NUM');
							$.ajax({
								url:'/metelSOS/getCompleteSupDetail.do',
								type:'POST',
								dataType:'json',
								data:{"supportNum" : supportNum},
								async:false,
								success:function(msg){
									console.log(msg);
									var supportInfo = msg.supportInfo;
									$("#myModalLabel").text("기술지원 #"+supportNum);
									$("#support_title").val(supportInfo.support_title);
									$("#company_name").val(supportInfo.company_name);
									$("#customer_name").val(supportInfo.customer_name+" "+supportInfo.customer_position);
									$("#support_engineer").val(supportInfo.support_engineer+" "+supportInfo.engineer_position);
									$("#support_accept_date").val(supportInfo.support_accept_date);
									$("#support_way").val(supportInfo.support_way);
									
									if(supportInfo.purpose_of_visit != ''){
										var purpose_of_visit = supportInfo.purpose_of_visit;
										$("#purpose_of_visit_section").css("display", "");
										$("#purpose_of_visit").val(purpose_of_visit.replace(/<br\s?\/?>/g,"\n"));
									}else{
										$("#purpose_of_visit_section").css("display", "none");
									}
									
									$("#support_request").val(supportInfo.support_request.replace(/<br\s?\/?>/g,"\n"));
									$("#support_response").val(supportInfo.support_response.replace(/<br\s?\/?>/g,"\n"));
									$("#support_engineer_comment").val(supportInfo.support_engineer_comment.replace(/<br\s?\/?>/g,"\n"));
									
									$("#myModal").modal('show');
								},
								error:function(request,status,error){
							        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							    }
							});
							
						}
					});
				 
				 jQuery("#completeSupportJqgrid").jqGrid('navGrid', "#completeSupportpJqgrid", {
						edit : false,
						add : false,
						del : false
				});
				 
				// remove classes
					$(".ui-jqgrid").removeClass("ui-widget ui-widget-content");
					$(".ui-jqgrid-view").children().removeClass("ui-widget-header ui-state-default");
					$(".ui-jqgrid-labels, .ui-search-toolbar").children().removeClass("ui-state-default ui-th-column ui-th-ltr");
					$(".ui-jqgrid-pager").removeClass("ui-state-default");
					$(".ui-jqgrid").removeClass("ui-widget-content");

					// add classes
					$(".ui-jqgrid-htable").addClass("table table-bordered table-hover");
					$(".ui-jqgrid-btable").addClass("table table-bordered table-striped");

					$(".ui-pg-div").removeClass().addClass("btn btn-sm btn-primary");
					$(".ui-icon.ui-icon-plus").removeClass().addClass("fa fa-plus");
					$(".ui-icon.ui-icon-pencil").removeClass().addClass("fa fa-pencil");
					$(".ui-icon.ui-icon-trash").removeClass().addClass("fa fa-trash-o");
					$(".ui-icon.ui-icon-search").removeClass().addClass("fa fa-search");
					$(".ui-icon.ui-icon-refresh").removeClass().addClass("fa fa-refresh");
					$(".ui-icon.ui-icon-disk").removeClass().addClass("fa fa-save").parent(".btn-primary").removeClass("btn-primary").addClass("btn-success");
					$(".ui-icon.ui-icon-cancel").removeClass().addClass("fa fa-times").parent(".btn-primary").removeClass("btn-primary").addClass("btn-danger");

					$(".ui-icon.ui-icon-seek-prev").wrap("<div class='btn btn-sm btn-default'></div>");
					$(".ui-icon.ui-icon-seek-prev").removeClass().addClass("fa fa-backward");

					$(".ui-icon.ui-icon-seek-first").wrap("<div class='btn btn-sm btn-default'></div>");
					$(".ui-icon.ui-icon-seek-first").removeClass().addClass("fa fa-fast-backward");

					$(".ui-icon.ui-icon-seek-next").wrap("<div class='btn btn-sm btn-default'></div>");
					$(".ui-icon.ui-icon-seek-next").removeClass().addClass("fa fa-forward");

					$(".ui-icon.ui-icon-seek-end").wrap("<div class='btn btn-sm btn-default'></div>");
					$(".ui-icon.ui-icon-seek-end").removeClass().addClass("fa fa-fast-forward");
					
					$(window).on('resize.jqGrid', function() {
						$("#acceptSupportJqgrid").jqGrid('setGridWidth', $("#content").width());
					});
		 	 });
		
		</script>

	</body>
</html>