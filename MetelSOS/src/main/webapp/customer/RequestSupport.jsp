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
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget jarviswidget-color-darken" id="SupportList" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>지원 사항 목록</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div>
											<form id="search-title-form" class="smart-form client-form" action="/metelSOS/searchSupportTitle.do" method="post">
												<fieldset style="padding:0;">
													<section>
														<label class="label" style="display:inline-block;">제목으로 검색</label>&nbsp;&nbsp;
															<div style="display:inline-block; border:1px solid #d4d2d2;">
																<input type="text" name="support_title" id="support_title" class="input-xs" style="width:200px; color:#000000; border:0;">
																<button type="submit" id="searchTitle" class="input-xs" style="color:#000000; border:0;">
    																<i class="fa fa-lg fa-search" aria-hidden="true"></i>
																</button>
															</div>
													</section>
												</fieldset>
											</form>
										</div>
										<table id="dt_basic" class="table table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th>번호</th>
													<th>제목</th>
													<th>고객사</th>
													<th><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>담당자</th>
													<th><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>담당 엔지니어</th>
													<th><i class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>접수일</th>
													<th>상태</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
									<button id="newSupport" class="btn btn-success" style="float:left; margin-top:20px;">
										새 지원 요청
									</button>
									<div id="PAGE_NAVI" class="text-right" style="float:right;">
									</div>
									<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX"/>
									<input type="hidden" id="searchValue" name="searchValue"/>
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
		  		$("#left-panel nav ul li:nth-child(2)").addClass("active");
		  		selectSupportList(1);
		 	 });
		  	
		  	function selectSupportList(pageNo, searchValue){
		  		var comAjax = new ComAjax();
	            comAjax.setUrl("/metelSOS/getAllSupportList.do");
	            comAjax.setCallback("selectBoardListCallback");
	            comAjax.addParam("PAGE_INDEX",pageNo);
	            comAjax.addParam("PAGE_ROW", 10);
	            comAjax.addParam("userId",'${userId}');
	            comAjax.addParam("searchValue", encodeURI(searchValue));
	            comAjax.syncAjax();
		  	};
		  	
		  	function selectBoardListCallback(data){
		  		var total = data.TOTAL;
	            var body = $("table>tbody");
	            body.empty();
	            if(total == 0){
	                var str = "<tr>" + 
	                                "<td colspan='6'>조회된 결과가 없습니다.</td>" + 
	                            "</tr>";
	                body.append(str);
	            }
	            else{
	                var params = {
	                    divId : "PAGE_NAVI",
	                    pageIndex : "PAGE_INDEX",
	                    totalCount : total,
	                    searchValueId : "searchValue",
	                    searchValue : data.searchValue,
	                    eventName : "selectSupportList",
	                    recordCount : 10
	                };
	                gfn_renderPaging(params);
	                 
	                var str = "";
	                $.each(data.allSupportList, function(key, value){
	                	
	                	var hrefTag = '<a href="javascript:moveNoticeDetailPage('+value.SUPPORT_NUM+');" name='+'title>' + value.SUPPORT_TITLE + '</a>';
	                	
	                    str += "<tr>" + 
	                                "<td>" + value.RNUM + "</td>" + 
	                                "<td class='title'>" +
	                                hrefTag  +
	                                "</td>" +
	                                "<td>" + value.COMPANY_NAME + "</td>" + 
	                                "<td>" + value.CUSTOMER_NAME + "</td>" + 
	                                "<td>" + value.SUPPORT_ENGINEER + "</td>" + 
	                                "<td>" + value.SUPPORT_ACCEPT_DATE + "</td>" + 
	                                "<td>" + value.SUPPORT_STATE + "</td>" + 
	                            "</tr>";
	                });
	                
	                body.append(str);
	            }
		  	}
		  	
		  	$("#search-title-form").submit(function(e){
		  		e.preventDefault();
		  		selectSupportList(1, $("#support_title").val());
		  	});
		  	
		  	$("#newSupport").click(function(e){
		  		e.preventDefault();
		  		var comSubmit = new ComSubmit();
	  			comSubmit.setUrl("/metelSOS/moveNewSupportReqPage.do");
	  			comSubmit.addParam('userId', '${userId}');
	  			comSubmit.addParam('userType', 'customer');
	  			comSubmit.addParam('menuTitle', encodeURI('지원 요청하기'));
	  			comSubmit.addParam('menuIcon', 'fa fa-lg fa-fw fa-pencil-square-o');
	  			comSubmit.getSubmit();
		  	});
		</script>

	</body>
</html>