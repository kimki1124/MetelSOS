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
							<div class="jarviswidget jarviswidget-color-darken" id="acceptSupportList" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>내가 접수한 지원 요청</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div>
											<form id="search-title-form-1" class="smart-form client-form" action="/metelSOS/searchSupportTitle.do" method="post">
												<fieldset style="padding:0;">
													<section>
														<label class="label" style="display:inline-block;">제목:</label>&nbsp;&nbsp;
															<div style="display:inline-block; border:1px solid #d4d2d2;">
																<input type="text" name="support_title" id="support_title_1" class="input-xs" style="width:200px; color:#000000; border:0;">
																<button type="submit" id="searchTitle_1" class="input-xs" style="color:#000000; border:0;">
    																<i class="fa fa-lg fa-search" aria-hidden="true"></i>
																</button>
															</div>
															<div style="margin-left:5em; display:inline-block; color:#000000;">
																고객사 별로 보기&nbsp;&nbsp;
																<select id="acceptSupportCompany" class="input-sm" style="width:200px;">
																	<option value="전체보기">전체보기</option>
																	<c:forEach var="item" items="${acceptReqCompanyList}" varStatus="status">
																		<option value="${item }">${item }</option>
																	</c:forEach>
																</select>
															</div>
													</section>
												</fieldset>
											</form>
										</div>
										<table class="table table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th style="width:10%;">번호</th>
													<th style="width:20%;">제목</th>
													<th style="width:20%;">고객사</th>
													<th style="width:20%;"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>담당자</th>
													<th style="width:20%;"><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>접수일</th>
													<th style="width:10%;">상태</th>
												</tr>
											</thead>
											<tbody id="acceptSupport">
											</tbody>
										</table>
									</div>
									<div id="PAGE_NAVI_1" class="text-right" style="float:right;">
									</div>
									<input type="hidden" id="PAGE_INDEX_1" name="PAGE_INDEX"/>
									<input type="hidden" id="searchValue_1" name="searchValue"/>
									<input type="hidden" id="companyName_1" name="companyName"/>
								</div>
							</div>
							<div class="jarviswidget jarviswidget-color-darken" id="SupportingList" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>지원 중인 요청 사항</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div>
											<form id="search-title-form-2" class="smart-form client-form" action="/metelSOS/searchSupportTitle.do" method="post">
												<fieldset style="padding:0;">
													<section>
														<label class="label" style="display:inline-block;">제목:</label>&nbsp;&nbsp;
															<div style="display:inline-block; border:1px solid #d4d2d2;">
																<input type="text" name="support_title" id="support_title_2" class="input-xs" style="width:200px; color:#000000; border:0;">
																<button type="submit" id="searchTitle_2" class="input-xs" style="color:#000000; border:0;">
    																<i class="fa fa-lg fa-search" aria-hidden="true"></i>
																</button>
															</div>
															<div style="margin-left:5em; display:inline-block; color:#000000;">
																고객사 별로 보기&nbsp;&nbsp;
																<select id="supportingCompany" class="input-sm" style="width:200px;">
																	<option value="전체보기">전체보기</option>
																	<c:forEach var="item" items="${supportingReqCompanyList}" varStatus="status">
																		<option value="${item }">${item }</option>
																	</c:forEach>
																</select>
															</div>
													</section>
												</fieldset>
											</form>
										</div>
										<table class="table table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th style="width:10%;">번호</th>
													<th style="width:20%;">제목</th>
													<th style="width:20%;">고객사</th>
													<th style="width:20%;"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>담당자</th>
													<th style="width:20%;"><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>접수일</th>
													<th style="width:10%;">상태</th>
												</tr>
											</thead>
											<tbody id="supporting">
											</tbody>
										</table>
									</div>
									<div id="PAGE_NAVI_2" class="text-right" style="float:right;">
									</div>
									<input type="hidden" id="PAGE_INDEX_2" name="PAGE_INDEX"/>
									<input type="hidden" id="searchValue_2" name="searchValue"/>
									<input type="hidden" id="companyName_2" name="companyName"/>
								</div>
							</div>
							<div class="jarviswidget jarviswidget-color-darken" id="completeSupportList" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>지원 완료한 요청 사항</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div>
											<form id="search-title-form-3" class="smart-form client-form" action="/metelSOS/searchSupportTitle.do" method="post">
												<fieldset style="padding:0;">
													<section>
														<label class="label" style="display:inline-block;">제목:</label>&nbsp;&nbsp;
															<div style="display:inline-block; border:1px solid #d4d2d2;">
																<input type="text" name="support_title" id="support_title_3" class="input-xs" style="width:200px; color:#000000; border:0;">
																<button type="submit" id="searchTitle_3" class="input-xs" style="color:#000000; border:0;">
    																<i class="fa fa-lg fa-search" aria-hidden="true"></i>
																</button>
															</div>
															<div style="margin-left:5em; display:inline-block; color:#000000;">
																고객사 별로 보기&nbsp;&nbsp;
																<select id="completeSupportCompany" class="input-sm" style="width:200px;">
																	<option value="전체보기">전체보기</option>
																	<c:forEach var="item" items="${completeReqCompanyList}" varStatus="status">
																		<option value="${item }">${item }</option>
																	</c:forEach>
																</select>
															</div>
													</section>
												</fieldset>
											</form>
										</div>
										<table class="table table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th style="width:10%;">번호</th>
													<th style="width:20%;">제목</th>
													<th style="width:20%;">고객사</th>
													<th style="width:20%;"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>담당자</th>
													<th style="width:20%;"><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>접수일</th>
													<th style="width:10%;">상태</th>
												</tr>
											</thead>
											<tbody id="completeSupport">
											</tbody>
										</table>
									</div>
									<div id="PAGE_NAVI_3" class="text-right" style="float:right;">
									</div>
									<input type="hidden" id="PAGE_INDEX_3" name="PAGE_INDEX"/>
									<input type="hidden" id="searchValue_3" name="searchValue"/>
									<input type="hidden" id="companyName_3" name="companyName"/>
								</div>
							</div>
						</article>
					</div>
				</section>
			</div>	
		</div>
		
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
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
		  		
		  		selectAcceptSupportList(1);
		  		selectSupportingList(1);
		  		selectCompleteSupportList(1);
		 	 });
		  	
		  	function selectAcceptSupportList(pageNo, searchValue, companyName){
		  		var comAjax = new ComAjax();
	            comAjax.setUrl("/metelSOS/getAcceptSupportList.do");
	            comAjax.setCallback("selectBoardListCallback_1");
	            comAjax.addParam("PAGE_INDEX",pageNo);
	            comAjax.addParam("PAGE_ROW", 10);
	            comAjax.addParam("searchValue", encodeURI(searchValue));
	            comAjax.addParam("companyName", encodeURI(companyName));
	            comAjax.syncAjax();
		  	};
		  	
		  	function selectBoardListCallback_1(data){
		  		var total = data.TOTAL;
	            var body = $("#acceptSupport");
	            body.empty();
	            if(total == 0){
	                var str = "<tr>" + 
	                                "<td colspan='6'>조회된 결과가 없습니다.</td>" + 
	                            "</tr>";
	                body.append(str);
	            }
	            else{
	                var params = {
	                    divId : "PAGE_NAVI_1",
	                    pageIndex : "PAGE_INDEX_1",
	                    totalCount : total,
	                    searchValueId : "searchValue_1",
	                    searchValue : data.searchValue,
	                    companyNameId : "companyName_1",
	                    companyName : data.companyName,
	                    eventName : "selectAcceptSupportList",
	                    recordCount : 10
	                };
	                gfn_renderPaging(params);
	                 
	                var str = "";
	                $.each(data.acceptSupportList, function(key, value){
	                	
	                	var hrefTag = '<a href="javascript:moveSupportDetailPage('+value.SUPPORT_NUM+');" name='+'title>' + value.SUPPORT_TITLE + '</a>';
	                	
	                    str += "<tr>" + 
	                                "<td>" + value.RNUM + "</td>" + 
	                                "<td class='title'>" +
	                                hrefTag  +
	                                "</td>" +
	                                "<td>" + value.COMPANY_NAME + "</td>" + 
	                                "<td>" + value.CUSTOMER_NAME + "</td>" + 
	                                "<td>" + value.SUPPORT_ACCEPT_DATE + "</td>" + 
	                                "<td>" + value.SUPPORT_STATE + "</td>" + 
	                            "</tr>";
	                });
	                
	                body.append(str);
	            }
		  	}
		  	
		  	$("#search-title-form-1").submit(function(e){
		  		e.preventDefault();
		  		selectAcceptSupportList(1, $("#support_title_1").val());
		  	});
		  	
		  	function selectSupportingList(pageNo, searchValue, companyName){
		  		var comAjax = new ComAjax();
	            comAjax.setUrl("/metelSOS/getSupportingList.do");
	            comAjax.setCallback("selectBoardListCallback_2");
	            comAjax.addParam("PAGE_INDEX",pageNo);
	            comAjax.addParam("PAGE_ROW", 10);
	            comAjax.addParam("searchValue", encodeURI(searchValue));
	            comAjax.addParam("companyName", encodeURI(companyName));
	            comAjax.syncAjax();
		  	};
		  	
		  	function selectBoardListCallback_2(data){
		  		var total = data.TOTAL;
	            var body = $("#supporting");
	            body.empty();
	            if(total == 0){
	                var str = "<tr>" + 
	                                "<td colspan='6'>조회된 결과가 없습니다.</td>" + 
	                            "</tr>";
	                body.append(str);
	            }
	            else{
	                var params = {
	                    divId : "PAGE_NAVI_2",
	                    pageIndex : "PAGE_INDEX_2",
	                    totalCount : total,
	                    searchValueId : "searchValue_2",
	                    searchValue : data.searchValue,
	                    companyNameId : "companyName_2",
	                    companyName : data.companyName,
	                    eventName : "selectSupportingList",
	                    recordCount : 10
	                };
	                gfn_renderPaging(params);
	                 
	                var str = "";
	                $.each(data.supportingList, function(key, value){
	                	
	                	var hrefTag = '<a href="javascript:moveSupportDetailPage('+value.SUPPORT_NUM+');" name='+'title>' + value.SUPPORT_TITLE + '</a>';
	                	
	                    str += "<tr>" + 
	                                "<td>" + value.RNUM + "</td>" + 
	                                "<td class='title'>" +
	                                hrefTag  +
	                                "</td>" +
	                                "<td>" + value.COMPANY_NAME + "</td>" + 
	                                "<td>" + value.CUSTOMER_NAME + "</td>" + 
	                                "<td>" + value.SUPPORT_ACCEPT_DATE + "</td>" + 
	                                "<td>" + value.SUPPORT_STATE + "</td>" + 
	                            "</tr>";
	                });
	                
	                body.append(str);
	            }
		  	}
		  	
		  	$("#search-title-form-2").submit(function(e){
		  		e.preventDefault();
		  		selectSupportingList(1, $("#support_title_2").val());
		  	});
		  	
		  	function selectCompleteSupportList(pageNo, searchValue, companyName){
		  		var comAjax = new ComAjax();
	            comAjax.setUrl("/metelSOS/getCompleteSupportList.do");
	            comAjax.setCallback("selectBoardListCallback_3");
	            comAjax.addParam("PAGE_INDEX",pageNo);
	            comAjax.addParam("PAGE_ROW", 10);
	            comAjax.addParam("searchValue", encodeURI(searchValue));
	            comAjax.addParam("companyName", encodeURI(companyName));
	            comAjax.syncAjax();
		  	};
		  	
		  	function selectBoardListCallback_3(data){
		  		var total = data.TOTAL;
	            var body = $("#completeSupport");
	            body.empty();
	            if(total == 0){
	                var str = "<tr>" + 
	                                "<td colspan='6'>조회된 결과가 없습니다.</td>" + 
	                            "</tr>";
	                body.append(str);
	            }
	            else{
	                var params = {
	                    divId : "PAGE_NAVI_3",
	                    pageIndex : "PAGE_INDEX_3",
	                    totalCount : total,
	                    searchValueId : "searchValue_3",
	                    searchValue : data.searchValue,
	                    companyNameId : "companyName_3",
	                    companyName : data.companyName,
	                    eventName : "selectCompleteSupportList",
	                    recordCount : 10
	                };
	                gfn_renderPaging(params);
	                 
	                var str = "";
	                $.each(data.completeSupportList, function(key, value){
	                	
	                	var hrefTag = '<a href="javascript:moveSupportDetailPage('+value.SUPPORT_NUM+');" name='+'title>' + value.SUPPORT_TITLE + '</a>';
	                	
	                    str += "<tr>" + 
	                                "<td>" + value.RNUM + "</td>" + 
	                                "<td class='title'>" +
	                                hrefTag  +
	                                "</td>" +
	                                "<td>" + value.COMPANY_NAME + "</td>" + 
	                                "<td>" + value.CUSTOMER_NAME + "</td>" + 
	                                "<td>" + value.SUPPORT_ACCEPT_DATE + "</td>" + 
	                                "<td>" + value.SUPPORT_STATE + "</td>" + 
	                            "</tr>";
	                });
	                
	                body.append(str);
	            }
		  	}
		  	
		  	$("#search-title-form-3").submit(function(e){
		  		e.preventDefault();
		  		selectCompleteSupportList(1, $("#support_title_3").val());
		  	});
		  	
		  	$("#acceptSupportCompany").change(function(){
		  		selectAcceptSupportList(1, $("#support_title_1").val(), $("#acceptSupportCompany").val());
		  	});
		  	
		  	$("#supportingCompany").change(function(){
		  		selectSupportingList(1, $("#support_title_2").val(), $("#supportingCompany").val());
		  	});
		  	
		  	$("#completeSupportCompany").change(function(){
		  		selectCompleteSupportList(1, $("#support_title_3").val(), $("#completeSupportCompany").val());
		  	});
		  	
		
		</script>

	</body>
</html>