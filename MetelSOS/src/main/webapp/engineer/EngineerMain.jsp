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
			//뒤로가기 막기
			history.pushState(null, null, location.href); 
			window.onpopstate = function(event) { 
				history.go(1); 
			}
			
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
		<form name="noticePageParamForm">
			<input type="hidden" name="userType">
			<input type="hidden" name="menuTitle">
			<input type="hidden" name="menuIcon">
		</form>
		<!-- #MAIN PANEL -->
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
						<article class="col-sm-12">
							<div class="jarviswidget jarviswidget-color-darken" id="notice" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>공지사항</h2>
									<c:if test="${userId == 'admin' }">
										&nbsp;&nbsp;<a class="btn btn-success btn-xs" href="javascript:moveNoticePage();" style="margin-top:5px;"><i class="fa fa-gear"></i> &nbsp;&nbsp;관리</a>
									</c:if>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div class="table-responsive">
											<table class="table table-bordered table-striped">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:25%;">제목</th>
														<th style="width:5%; text-align:center;">파일</th>
														<th style="width:25%;">작성자</th>
														<th style="width:25%;">작성일</th>
														<th style="width:10%;">조회수</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
										<div id="PAGE_NAVI" class="text-center"></div>
										<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX"/>
									</div>
								</div>
							</div>
							<div class="jarviswidget jarviswidget-color-darken" id="month_support" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
									<h2>월별 유지보수 지원사항 통계</h2>
								</header>
								<div class="no-padding">
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
											<div id="normal-bar-graph" class="chart no-padding"></div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 show-stats">
											<div class="row">
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12" style="margin-top:25px; margin-bottom:35px;">
													<strong>${currMonth }월 유지보수 지원 현황</strong>
												</div>
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12"> <span class="text"> <strong>접수량</strong> <span class="pull-right"><strong>${curr_support_total_count }/100</strong></span> </span>
													<div class="progress">
														<div class="progress-bar bg-color-blueDark" style="width: ${percent_curr_support_total_count}%;"></div>
													</div> 
												</div>
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12"> <span class="text"> <strong>지원량</strong> <span class="pull-right"><strong>${curr_support_complete_count }/100</strong></span> </span>
													<div class="progress">
														<div class="progress-bar bg-color-blue" style="width: ${percent_curr_support_complete_count}%;"></div>
													</div> 
												</div>
												<div class="col-xs-6 col-sm-6 col-md-12 col-lg-12"> <span class="text"> <strong>미지원량</strong> <span class="pull-right"><strong>${curr_support_not_complete_count }/100</strong></span> </span>
													<div class="progress">
														<div class="progress-bar bg-color-greenLight" style="width: ${percent_curr_support_not_complete_count}%;"></div>
													</div> 
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</article>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="well well-light">
								<h1>${currMonth }월 우수사원, <small>${exclntStfCount }명</small>
									<c:if test="${userId == 'admin' }">
										<a class="btn btn-success" href="javascript:moveManageExclntStfPage();"><i class="fa fa-gear"></i> &nbsp;&nbsp;관리</a>
									</c:if>
								</h1>
								<div class="row">
								<c:forEach var="item" items="${currExclntStfList}" varStatus="status">
									<div class="col-xs-12 col-sm-6 col-md-3">
						            <div class="panel panel-success pricing-big">
						            	
						                <div class="panel-heading">
						                    <h3 class="panel-title">
						                    	${item.engineer_dept }
						                    </h3>
						                </div>
						                <div class="panel-body no-padding text-align-center">
						                    <div class="the-price">
						                        <h1>
						                            <strong>${item.engineer_name } ${item.engineer_position}</strong></h1>
						                    </div>
											<div class="price-features" style="display: table; margin-left: auto; margin-right: auto;">
												<c:choose>
													<c:when test="${item.engineer_image ne null}">
														<img alt="" class="img-responsive" src="/metelSOS/getExclntStfImage.do?engineer_dept=${item.engineer_dept }&engineer_name=${item.engineer_name}" height="270" />
													</c:when>
													<c:otherwise>
														<i class="fa fa-user" style="font-size:190px; height:270px;"></i>
													</c:otherwise>
												</c:choose>
												
											</div>
						                </div>
						                <div class="panel-footer text-align-center">
						                         ${item.impression_speech }
						                </div>
						            </div>
						        </div>
								</c:forEach>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="well well-light">
								<h1>${currMonth }월 신입사원, <small>${newEmplydListCount }명</small>
									<c:if test="${userId == 'admin' }">
										<a class="btn btn-success" href="javascript:moveManageNewEmplydPage();"><i class="fa fa-gear"></i> &nbsp;&nbsp;관리</a>
									</c:if>
								</h1>
								<div class="row">
									<c:forEach var="item" items="${newEmplydList}" varStatus="status">
											<div class="col-xs-12 col-sm-6 col-md-3">
							            <div class="panel panel-success pricing-big">
							            	
							                <div class="panel-heading">
							                    <h3 class="panel-title">
							                    	${item.engineer_dept }
							                    </h3>
							                </div>
							                <div class="panel-body no-padding text-align-center">
							                    <div class="the-price">
							                        <h1>
							                            <strong>${item.engineer_name } ${item.engineer_position}</strong></h1>
							                    </div>
												<div class="price-features" style="display: table; margin-left: auto; margin-right: auto;">
													<c:choose>
														<c:when test="${item.engineer_image ne null}">
															<img alt="" class="img-responsive" src="/metelSOS/getNewEmplydImage.do?engineer_dept=${item.engineer_dept }&engineer_name=${item.engineer_name}" height="270" />
														</c:when>
														<c:otherwise>
															<i class="fa fa-user" style="font-size:190px; height:270px;"></i>
														</c:otherwise>
													</c:choose>
												</div>
							                </div>
							                <div class="panel-footer text-align-center">
							                   	 ${item.impression_speech }
							                </div>
							            </div>
							        	</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->

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
		  
		  	function moveNoticePage(){
		  		var form = document.noticePageParamForm;
		  		form.userType.value = "engineer";
		  		form.menuTitle.value = encodeURI("공지사항 관리");
		  		form.menuIcon.value = "fa fa-lg fa-fw fa-bell";
		  		form.action = "/metelSOS/noticePageMove.do";
		  		form.method = "get";
		  		form.submit();
		  	};
		  	
		  	function moveManageExclntStfPage(){
		  		var form = document.noticePageParamForm;
		  		form.userType.value = "engineer";
		  		form.menuTitle.value = encodeURI("우수사원 관리");
		  		form.menuIcon.value = "fa fa-lg fa-fw fa-thumbs-up";
		  		form.action = "/metelSOS/moveManageExclntStfPage.do";
		  		form.method = "get";
		  		form.submit();
		  	};
		  	
		  	function moveManageNewEmplydPage(){
		  		var form = document.noticePageParamForm;
		  		form.userType.value = "engineer";
		  		form.menuTitle.value = encodeURI("신입사원 관리");
		  		form.menuIcon.value = "fa fa-lg fa-fw fa-male";
		  		form.action = "/metelSOS/moveManageNewEmplydPage.do";
		  		form.method = "get";
		  		form.submit();
		  	};
		  	
		  	$(document).ready(function(){
		  		$(document).keydown(function(e){   
	    			if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
	        			if(e.keyCode === 8){   
	        				return false;
	        			}
	    			}
				});
		  		
		  		//공지사항 로드
		  		selectNoticeList(1);
		  		
		  		$("#left-panel nav ul li").first().addClass("active");
		  		
		  		pageSetUp();
		  		
		  		if ($('#normal-bar-graph').length) {

					Morris.Bar({
						element : 'normal-bar-graph',
						data : [
							<c:forEach var="item" items="${prevSupportCountList}" varStatus="status">
								{
									period: '${item.yearMonth}',
									접수량 : ${item.support_total_count},
									지원량 : ${item.support_complete_count},
									미지원량 : ${item.support_not_complete_count}
								}<c:if test="${!status.last}">,</c:if>
							</c:forEach>],
						xkey : 'period',
						ykeys : ['접수량', '지원량', '미지원량'],
						labels : ['접수량', '지원량', '미지원량'],
						hideHover : 'auto'
					});

				}
		 	 });
		  	
		  	function selectNoticeList(pageNo){
		  		var comAjax = new ComAjax();
	            comAjax.setUrl("/metelSOS/selectNoticeList.do");
	            comAjax.setCallback("selectBoardListCallback");
	            comAjax.addParam("PAGE_INDEX",pageNo);
	            comAjax.addParam("PAGE_ROW", 5);
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
	                    eventName : "selectNoticeList"
	                };
	                gfn_renderPaging(params);
	                 
	                var str = "";
	                $.each(data.noticeList, function(key, value){
	                	var tempTag;
	                	
	                	if(value.HAS_FILE == 'Y'){
	                		tempTag = "<td style='text-align:center;'><i class='fa fa-file fa-lg' aria-hidden='true'></i></td>";
	                	}else{
	                		tempTag = "<td style='text-align:center;'></td>";
	                	}
	                	
	                	var hrefTag = '<a href="javascript:moveNoticeDetailPage('+value.NOTICE_NUM+');" name='+'title>' + value.NOTICE_TITLE + '</a>';
	                	
	                    str += "<tr>" + 
	                                "<td>" + value.RNUM + "</td>" + 
	                                "<td class='title'>" +
	                                hrefTag  +
	                                "</td>" +
	                                tempTag + 
	                                "<td>" + value.NOTICE_AUTHOR + "</td>" + 
	                                "<td>" + value.NOTICE_DATE + "</td>" + 
	                                "<td>" + value.NOTICE_HIT + "</td>" + 
	                            "</tr>";
	                });
	                
	                body.append(str);
	            }
		  	}
		  	
		  	function moveNoticeDetailPage(notice_num){
		  		document.location.href="/metelSOS/moveNoticeDetailPage.do?notice_num="+notice_num+"&userId="+"${userId}";
		  	};
		
		</script>

	</body>
</html>